/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.ds;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author asharif
 */
public class NodeTest extends TestCase {
    
    
    public NodeTest(String testName) {
        
        super(testName);
    }
    
    public static Test suite() {
        
        return new TestSuite(NodeTest.class);
    }
    
    public void testCompareLessThan() {
        
        
        Node n1 = new Node();
        n1.setKey("a");
        
        Node n2 = new Node();
        n2.setKey("aa");
        
        assertTrue(n1.compareTo(n2) < 0);
        
    }
    
    public void testCompareGreaterThan() {
        
        
        Node n1 = new Node();
        n1.setKey("a");
        
        Node n2 = new Node();
        n2.setKey("aa");
        
        assertTrue(n2.compareTo(n1) > 0);
        
    }
    
    public void testCompareEqual() {
        
        
        Node n1 = new Node();
        n1.setKey("a");
        
        Node n2 = new Node();
        n2.setKey("a");
        
        assertTrue(n2.compareTo(n1) == 0);
        
    }
    
    public void testAddChild() {
        
        Node n1 = new Node();
        n1.setKey("a");
        Node n1a = new Node();
        n1a.setKey("a1");
        n1.addChild(n1a);
        
        
        Node n2 = new Node();
        n2.setKey("b");
        
        assertEquals(1, n1.getChildren().getSize());
        assertEquals(0, n2.getChildren().getSize());
        
    }
}
