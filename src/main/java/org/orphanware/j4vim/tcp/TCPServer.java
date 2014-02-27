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
import org.orphanware.j4vim.MethodComplete;

/**
 *
 * @author asharif
 */
public class TCPServer {
    
    private int port;
    private MethodComplete mc;
    
    public TCPServer(int port) throws Exception {
        this.port = port;
        
    }
    
    public void startServer() throws IOException, Exception {
        
        System.out.println("starting j4vim server...");
        
        ServerSocket welcomeSocket = new ServerSocket(this.port);
        
        
        
        System.out.println("j4vim server started!");
        
        while(true)
         {

             Socket connectionSocket = welcomeSocket.accept();
             BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            
            String inString = inFromClient.readLine();

			if(inString.equals("kill"))
				break;
           
            String[] inputArr = inString.split("\\|");
            
            if(inputArr.length != 2) {
                continue;
            }

            String outString ="";

			if(inputArr[0].equals("code")) {

				 this.mc = new MethodComplete(inputArr[1]);

			}else if(inputArr[0].equals("prefix")) {
   

				 String[] varPrefixArr = inputArr[1].split("\\.");
				 String var = varPrefixArr[0];
				 String prefix = "";

				 if(varPrefixArr.length == 2) {

					 prefix = varPrefixArr[1];
				 }

				 if( mc != null)
					 outString = mc.getMethodsForVarByPrefix(var, prefix);
                 
              
            } else {
                continue;
            }
            
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            
			System.out.println(outString);

            outToClient.writeBytes(outString + '\n');
         }


        System.out.println("j4vim server was killed explicidly");
    }
    
}
