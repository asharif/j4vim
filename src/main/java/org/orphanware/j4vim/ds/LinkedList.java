/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.ds;

/**
 *
 * @author asharif
 */
public class LinkedList {
    
    private Node head;
    private Node tail;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
    
    
    
    
    public void add(Node node) {
        
        if( head == null) {
            
            head = tail = node;
            return;
        }
        
        node.setPrevious(head);
        head.setNext(node);
        head = node;
        
        
    }
    
    public Node findByVal(String val) {
        
        Node curr = tail;
        
        while(curr != null) {

            if(curr.getVal().equals(val)) {
                return curr;
            }
            
            curr = curr.getNext();
            
        }
        
        return null;
    }
    
    public void remove(Node node) {
        
        Node curr = tail;
        
        while(curr != null) {
            
            if(curr == node) {
                
                if(curr.getPrevious() == null && curr.getNext() == null) {
                    head = tail = null;
                
                }else if ( curr.getPrevious() == null && curr.getNext() != null) {
                    tail = curr.getNext();
                    curr.getNext().setPrevious(null);
                    
                }else if ( curr.getPrevious() != null && curr.getNext() == null) {
                    
                    head = curr.getPrevious();
                    curr.getPrevious().setNext(null);
                }else {
                    
                    curr.getPrevious().setNext(curr.getNext());
                }
                return;
            }
  
            curr = curr.getNext();
        }
        
    }
    
    public int getSize() {
        
        
        Node curr = tail;
        
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.getNext();
        }
        
        return count;
        
    }
    
    public Node[] toArray() {
        
        
        Node[] llArr = new Node[getSize()];
        
        Node curr = tail;
        
        int count = 0;
        while (curr != null) {
            
            llArr[count] = curr;
            count++;
            curr = curr.getNext();
        }
        
        return llArr;
        
    }
    
    
}
