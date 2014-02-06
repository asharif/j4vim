/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.algorithms;

import org.orphanware.j4vim.ds.Node;

/**
 *
 * @author asharif
 */
public class QuickSort {
    
    private Node[] nodes;
    
    public QuickSort() {
        
        
    }
    
    public void sort(Node[] nodes) {
        
        
        this.nodes = nodes;
        
        qsortRecursive(0, nodes.length-1);
        
    }
    
    
    private void qsortRecursive(int start, int end) {
        
        
        if(start >= end) {
            return;
        }
        
        Node pivot = this.nodes[end];
        
        int left_p = start-1;
        int right_p = end;
        
        
        while(true) {
            
            //find left bigger than pivot
            while( ++left_p < end && nodes[left_p].compareTo(pivot) < 0);
            
            
            //find right smaller than pivot
            while( --right_p > start  && nodes[right_p].compareTo(pivot) > 0);
           
            
            if(left_p >= right_p) {
                break;
            }
            
            Node tmp = nodes[left_p];
            nodes[left_p] = nodes[right_p];
            nodes[right_p] = tmp;
            
            
        }
        
        Node tmp2 = nodes[left_p];
        nodes[left_p] = pivot;
        pivot = tmp2;
        
        qsortRecursive(start, left_p-1);
        qsortRecursive(left_p+1, end);
    }
    
    
    
    
    
    
    
}
