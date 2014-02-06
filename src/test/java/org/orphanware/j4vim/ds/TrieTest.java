/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.ds;

import java.io.IOException;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.orphanware.j4vim.algorithms.QuickSort;

/**
 *
 * @author asharif
 */
public class TrieTest extends TestCase{
    
    
    public TrieTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        
        return new TestSuite(TrieTest.class);
    }
    
    public void testBuildTrieFromArray() throws IOException {
        
        Node[] nodes = new Node[6]; 
        
        
        Node n1 = new Node();
        n1.setKey("a");
        nodes[0] = n1;
       
        Node n2 = new Node();
        n2.setKey("ab");
        nodes[1] = n2;
       
        Node n3 = new Node();
        n3.setKey("abc");
        nodes[2] = n3;
        
        Node n4 = new Node();
        n4.setKey("b");
        nodes[3] = n4;
        
        Node n5 = new Node();
        n5.setKey("bc");
        nodes[4] = n5;
        
        Node n6 = new Node();
        n6.setKey("bcd");
        nodes[5] = n6;
        
        Trie trie = new Trie();
        trie.setQuickSort(new QuickSort());
        try {
            
            trie.buildTrieFromArray(nodes);
    
        }catch(Exception e){
            e.printStackTrace();
            assertTrue("Trie.buildTrieFromArray failed miserably.  This is probably cause QuickSort was not set", false);
        }

        assertEquals(7, trie.getSize());
        
        
    }
    
    public void testGetByPrefix() throws IOException {
        
        Node[] nodes = new Node[6]; 
        
        
        Node n1 = new Node();
        n1.setKey("a");
        nodes[0] = n1;
       
        Node n2 = new Node();
        n2.setKey("ab");
        nodes[1] = n2;
       
        Node n3 = new Node();
        n3.setKey("abc");
        nodes[2] = n3;
        
        Node n4 = new Node();
        n4.setKey("b");
        nodes[3] = n4;
        
        Node n5 = new Node();
        n5.setKey("bc");
        nodes[4] = n5;
        
        Node n6 = new Node();
        n6.setKey("bcd");
        nodes[5] = n6;
        
        Trie trie = new Trie();
        trie.setQuickSort(new QuickSort());
        try {
            
            trie.buildTrieFromArray(nodes);
    
        }catch(Exception e){
            e.printStackTrace();
            assertTrue("Trie.buildTrieFromArray failed miserably.  This is probably cause QuickSort was not set", false);
        }
       
        List<Node> prefixNodes = trie.getNodesByPrefix("a");
        assertEquals(3, prefixNodes.size());
    
    }
}
