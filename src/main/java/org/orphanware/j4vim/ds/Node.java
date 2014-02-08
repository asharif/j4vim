/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.ds;

import java.io.Serializable;

/**
 *
 * @author asharif
 */
public class Node implements Comparable<Node>, Jsonable, Serializable{
    
    private String key;
    private String val;
    private Node next;
    private Node previous;
    
    //trie children
    private LinkedList children = new LinkedList();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node back) {
        this.previous = back;
    }

    public LinkedList getChildren() {
        return children;
    }

    public void setChildren(LinkedList children) {
        this.children = children;
    }
    
    public void addChild(Node node) {
        this.children.add(node);
    }

    public int compareTo(Node o) {
    
        int localKeyLength = this.key.length();
        int oKeyLength = o.getKey().length();
        
        if( localKeyLength > oKeyLength ) {
            return 1;
        } else if( localKeyLength < oKeyLength) {
            return -1;
        } 
        
        return 0;
    }
    
    @Override
    public String toJson() {
        
        String json;
        json = "{ \"key\": \"" + getKey() + "\",";
        json += "\"value\": \"" + getVal() + "\",";
        json += "\"children\" : " + children.toJson();
        json += "}";
        
        return json;
    }

    
}
