/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.ds;

import org.orphanware.j4vim.ds.LinkedList;
import org.orphanware.j4vim.ds.Node; 
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author asharif
 */
public class LinkedListTest extends TestCase{
    
    
    public LinkedListTest(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        
        return new TestSuite(LinkedListTest.class);
    }
    
    public void testAdd() {
        
        
        LinkedList ll = new LinkedList();
        
        for(int i=0; i < 10; ++i) {
            
            Node tmp = new Node();
            tmp.setVal(String.valueOf(i));
            ll.add(tmp);
        }
        
        assertEquals(ll.getSize(), 10);
        
        assertEquals(ll.getHead().getVal(), "9");
        assertEquals(ll.getTail().getVal(), "0");
    }
    
    public void testFind() {
        
        LinkedList ll = new LinkedList();
        
        for(int i=0; i < 10; ++i) {
            
            Node tmp = new Node();
            tmp.setVal(String.valueOf(i));
            ll.add(tmp);
        }
        
        Node node = ll.findByVal("6");
        
        assertEquals(node.getVal(), "6");
    }
    
    public void testRemove() {
        
        LinkedList ll = new LinkedList();
        
        for(int i=0; i < 10; ++i) {
            
            Node tmp = new Node();
            tmp.setVal(String.valueOf(i));
            ll.add(tmp);
        }
        
        Node node = ll.findByVal("6");
        ll.remove(node);
        
        assertEquals(9, ll.getSize());
        
        node = ll.findByVal("9");
        ll.remove(node);
        
        assertEquals(ll.getHead().getVal(), "8");
        
        node = ll.findByVal("0");
        ll.remove(node);
        
        assertEquals(ll.getTail().getVal(), "1");
    }
    
    public void testToArray() {
        
        LinkedList ll = new LinkedList();
        
        for(int i=0; i < 10; ++i) {
            
            Node tmp = new Node();
            tmp.setVal(String.valueOf(i));
            ll.add(tmp);
        }
        
        Node[] nodes = ll.toArray();
        
        for(int i=0; i < 10; ++i) {
            
            assertTrue(nodes[i].getVal().equals(String.valueOf(i)));
        }
    }
    
    
}
