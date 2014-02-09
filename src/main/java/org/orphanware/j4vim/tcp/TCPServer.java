/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.orphanware.j4vim.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.orphanware.j4vim.ClassComplete;

/**
 *
 * @author asharif
 */
public class TCPServer {
    
    private int port;
    private ClassComplete cc;
    
    public TCPServer(int port) throws Exception {
        this.port = port;
        this.cc = new ClassComplete(null);
        
    }
    
    public void startServer() throws IOException, Exception {
        
        System.out.println("starting j4vim server...");
        
        ServerSocket welcomeSocket = new ServerSocket(this.port);
        
        
        
        System.out.println("j4vim server  started!");
        
        while(true)
         {

             Socket connectionSocket = welcomeSocket.accept();
             BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            
            String inString = inFromClient.readLine();
           
            String[] inputArr = inString.split("\\|");
            
            if(inputArr.length != 2) {
                continue;
            }

            String outString ="";
            if(inputArr[0].equals("cp")) {
                cc = new ClassComplete(inputArr[1]);
             

            } else if(inputArr[0].equals("prefix")) {
   
                 outString = cc.getClassesByPrefix(inputArr[1]);
                 
              
            } else {
                continue;
            }
            
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            outToClient.writeBytes(outString + '\n');
         }
    }
    

    
    
    
}
