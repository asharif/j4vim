/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.orphanware.j4vim.ds.Node;
import org.orphanware.j4vim.ds.Trie;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;

/**
 *
 * @author asharif
 */
public final class ClassComplete {
    
    
    private Trie jdk_trie = new Trie();
    
    private Trie full_trie = new Trie();

    
    public ClassComplete() throws IOException, Exception {
        
        buildJDKTrie();
        full_trie = jdk_trie.getClone();
 
    }
    
    private void buildJDKTrie() throws Exception {
        
        Set<String> jdk_classes = new HashSet<String>();

        Reflections reflections = new Reflections(
                ClasspathHelper.forClass(Object.class),
                new SubTypesScanner(false));
        Set<String> types = reflections.getStore().getSubTypesOf(Object.class.getName());

        System.out.println(types.size());

        for (String c : types) {

            if (c.indexOf("$") == -1) {
                jdk_classes.add(c);
            }
        }
        
        jdk_trie.buildTrieFromArray(buildNodeArray(jdk_classes));

       
    }
    
    private Node[] buildNodeArray(Set<String> classes) {

        Node[] nodes = new Node[classes.size()];
        int count = 0;
        for(String fullClass : classes) {

            String[] fullClassArr = fullClass.split("\\.");
            String className = fullClassArr[fullClassArr.length-1];
            Node tmp = new Node();
            tmp.setKey(className);
            tmp.setVal(fullClass);
            nodes[count++] = tmp;
            
        }
        
        
        return nodes;
       
    }
    
    public void addUserClassPath(String jars) throws IOException, ClassNotFoundException, Exception {
        
        Set<String> user_classes = new HashSet<String>();
        getJarsClasses(jars, user_classes);
        
        Node[] userNodes = buildNodeArray(user_classes);
        
        full_trie = jdk_trie.getClone();
        
        for(int i=0; i < userNodes.length; ++i) {
            full_trie.add(userNodes[i]);
                    
        }
    }

    
    
    private void getJarsClasses(String jars, Set<String> user_classes) throws IOException {

        String[] classPathArr = jars.split(":");
        
        for(int i=0; i < classPathArr.length; ++i) {
            
            getJarClasses(jars, user_classes);
        }
        

    }

    private void getJarClasses(String jar, Set<String> user_classes) throws FileNotFoundException, IOException {

        ZipInputStream zip = new ZipInputStream(new FileInputStream(jar));

        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (entry.getName().endsWith(".class") && !entry.isDirectory()) {

                StringBuilder className = new StringBuilder();
                for (String part : entry.getName().split("/")) {
                    if (className.length() != 0) {
                        className.append(".");
                    }
                    className.append(part);
                    if (part.endsWith(".class")) {
                        className.setLength(className.length() - ".class".length());
                    }
                }
                
                user_classes.add(className.toString());
            }
        }
    }

    
    public String getClassesByPrefix(String prefix) {
        
        List<Node> nodes = full_trie.getNodesByPrefix(prefix);
        StringBuilder classesSB = new StringBuilder();
        
        for(Node node : nodes) {
            
            classesSB.append(node.getKey()).append(",");
        }
        
        String commaClasses = classesSB.toString();
        int lastComma = commaClasses.lastIndexOf(",");
        
        if(lastComma > -1) {
            commaClasses = commaClasses.substring(0, lastComma);
        }
        return commaClasses;
        
    }
    
    public String getClassTrieJson() throws Exception {
        

        return full_trie.toJson();
    }
}
