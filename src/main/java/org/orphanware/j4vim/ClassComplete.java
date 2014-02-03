/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.orphanware.j4vim.ds.LinkedList;
import org.orphanware.j4vim.ds.Node;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;

/**
 *
 * @author asharif
 */
public final class ClassComplete {
    
    private Set<String> classes = new HashSet<String>();
    

    
    public ClassComplete(String jars) throws IOException {
        
        getJarsClasses(jars);
        getJDKClasses();
        
        
        
    }
    
    private LinkedList buildNodes() {

        LinkedList ll = new LinkedList();
        for(String fullClass : classes) {
            
            String[] fullClassArr = fullClass.split(".");
            String className = fullClassArr[fullClassArr.length-1];
            Node tmp = new Node();
            tmp.setKey(className);
            tmp.setVal(fullClass);
            
        }
        
        return ll;
       
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

    private void getJDKClasses() {

        Reflections reflections = new Reflections(
                ClasspathHelper.forClass(Object.class),
                new SubTypesScanner(false));
        Set<String> types = reflections.getStore().getSubTypesOf(Object.class.getName());

        System.out.println(types.size());

        for (String c : types) {

            if (c.indexOf("$") == -1) {
                classes.add(c);
            }
        }

       
    }
    
}