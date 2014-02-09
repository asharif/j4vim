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
    
    private Set<String> classes = new HashSet<String>();
    private Trie trie = new Trie();

    
    public ClassComplete(String jars) throws IOException, Exception {
        
        getJDKJars();
        if(jars != null) {
            getJarsClasses(jars);
        }
 
        
        trie.buildTrieFromArray(buildNodeArray());
    }
    
    private void getJDKJars() {
        
        Reflections reflections = new Reflections(
                ClasspathHelper.forClass(Object.class),
                new SubTypesScanner(false));
        Set<String> types = reflections.getStore().getSubTypesOf(Object.class.getName());


        for (String c : types) {

            if (c.indexOf("$") == -1) {
                classes.add(c);
            }
        }
       
    }
    
    
    
    
    
    private void getJarsClasses(String jars) throws IOException {

        String[] classPathArr = jars.split(":");
        
        for(int i=0; i < classPathArr.length; ++i) {
            
            getJarClasses(jars);
        }
        

    }

    private void getJarClasses(String jar) throws FileNotFoundException, IOException {

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
                
                classes.add(className.toString());
            }
        }
    }
    
    
    private Node[] buildNodeArray() {

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

    
    public String getClassesByPrefix(String prefix, boolean fullPackage) {
        
        List<Node> nodes = trie.getNodesByPrefix(prefix);
        StringBuilder classesSB = new StringBuilder();
        
        for(Node node : nodes) {
            
            if(fullPackage) {
               classesSB.append(node.getVal()).append(","); 
            } else {
                classesSB.append(node.getKey()).append(",");
            }
        }
        
        String commaClasses = classesSB.toString();
        int lastComma = commaClasses.lastIndexOf(",");
        
        if(lastComma > -1) {
            commaClasses = commaClasses.substring(0, lastComma);
        }
        return commaClasses;
        
    }
    
    public String getClassTrieJson() throws Exception {
        

        return trie.toJson();
    }
}
