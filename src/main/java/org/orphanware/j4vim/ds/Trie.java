/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.ds;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import org.orphanware.j4vim.algorithms.QuickSort;

/**
 *
 * @author asharif
 */
public class Trie implements Jsonable, Serializable{
    
    private Node root;
    private QuickSort quickSort;
    
    
    public Node getRoot() {
        return this.root;
    }
    
    public Trie() {
        
        this.quickSort = new QuickSort();
        
        Node emptyNode = new Node();
        emptyNode.setKey("");
        emptyNode.setVal("");

        this.root = emptyNode;
        
    }
    
    public void buildTrieFromArray(Node[] nodes) throws Exception {
        
        
        
        
       for (int i =0; i < nodes.length; ++i) {
           
           add(nodes[i]);
       }
        
    } 
    
    public void add(Node node) throws Exception {
        
        
        if( node == null ) {
            throw new Exception("cannot add 'null' node to trie");
        }
        
        Node curr = this.root;
        
        Node parent = getParentForAdd(curr, node);
        
        parent.addChild(node);
        
    }
    
    
    
    private Node getParentForAdd(Node curr, Node node) {
        
        
        LinkedList children = curr.getChildren();
        
        Node currChild = children.getTail();
        
        while(currChild != null) {
            
            if( isNodePrefixHit(currChild, node)) {
                
                return getParentForAdd(currChild, node);
            }
            
            currChild = currChild.getNext();
        }
        
        
        return curr;
    }
    
    private Boolean isNodePrefixHit(Node curr, Node newNode) {
        
        String currKey = curr.getKey();
        int currKeyLength = currKey.length();
        String newKey = newNode.getKey();
        int newKeyLength  = newKey.length();

        if ( newKeyLength >= currKeyLength) {

            if( newKey.substring(0, currKeyLength).equals(currKey)) {
                //we have hit
                return true;
            }
        }
        
        return false;
    }
    
    public void preorderTraverse(Node curr) {
        
        if( curr == null) {
            return;
        }
        
        System.out.println(curr.getKey());
        
        preorderTraverse(curr.getNext());
        
        LinkedList children = curr.getChildren();
        preorderTraverse(children.getTail());
            
       
    }
    
    
    public int getSize() {
        
        return getSizeRec(this.root);
    }
    
    private int getSizeRec(Node curr) {
        
        if( curr == null) {
            return 0;
        }
 
        LinkedList children = curr.getChildren();
        
        return 1 + getSizeRec(curr.getNext()) + 
                getSizeRec(children.getTail());

    }
    
    public List<Node> getNodesByPrefix(String prefix) {
        
        Node prefixNode = new Node();
        prefixNode.setKey(prefix);
        
        Node parent = getParentForSearch(this.root, prefixNode);
        
        List<Node> nodes = new java.util.LinkedList<Node>();
        
        toList(parent, nodes);
        
        return nodes;
    }
    
    
    
    private Node getParentForSearch(Node curr, Node node) {
        
        
        LinkedList children = curr.getChildren();
        
        Node currChild = children.getTail();
        
        while(currChild != null) {
            
            if( isNodePrefixHit(currChild, node)) {
                
                return currChild;
            }
            
            currChild = currChild.getNext();
        }
        
        
        return null;
    }
    
    private void toList(Node node, List<Node> list) {
        
        if( node == null ) {
            return;
        }
        
        list.add(node);
        
        Node currChild = node.getChildren().getTail();
        
        while(currChild != null) {
            
            toList(currChild, list);
            currChild = currChild.getNext();
        }
        
    }

    @Override
    public String toJson() {
    
        return this.root.toJson();
    }

    
    public Trie getClone() throws IOException, ClassNotFoundException {
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(this);
        
        ByteArrayInputStream bais = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (Trie) ois.readObject();
        
    }
    
    
    
   
}
