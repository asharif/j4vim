/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.algorithms;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.orphanware.j4vim.ds.LinkedListTest;
import org.orphanware.j4vim.ds.Node;

/**
 *
 * @author asharif
 */
public class QuickSortTest extends TestCase {
    
    public QuickSortTest(String testName) {
        
        super(testName);
    }
    
    public static Test suite() {
        
        return new TestSuite(QuickSortTest.class);
    }
    
    public void testSort() {
        
        
        Node[] nodes = new Node[10];
        
        for(int i=0; i < 10; ++i){
            
            nodes[i] = new Node();
            
            String key="a";
            for(int j =0; j < Math.round(Math.random()*10); ++j) {
                key+="a";
            }
            nodes[i].setKey(key);
        }
        
        for(int i=0; i < 10; ++i) {
            
            System.out.println(nodes[i].getKey());
        }
        
        System.out.printf("\n");
        
        QuickSort qs = new QuickSort();
        qs.sort(nodes);

        
        for(int i=0; i < 10; ++i) {
            
            System.out.println(nodes[i].getKey());
        }
               
    }
              
    
    
}
