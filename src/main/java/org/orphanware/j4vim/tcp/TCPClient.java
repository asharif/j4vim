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
import java.net.Socket;

/**
 *
 * @author asharif
 */
public class TCPClient {

    private int port;
    public TCPClient(int port) {
        this.port = port;
    }


	public void setCode(String code) throws IOException {

		Socket clientSocket = new Socket("localhost", this.port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
        outToServer.writeBytes("code|" + code + '\n');
        String classes = inFromServer.readLine();
        clientSocket.close();


	}
    
    public String getMethodsByPrefix(String prefix) throws IOException {

        Socket clientSocket = new Socket("localhost", this.port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
        outToServer.writeBytes("prefix|" + prefix  + '\n');
        String classes = inFromServer.readLine();
        clientSocket.close();
        
        return classes;
        
    }
    
    public void setClassPath(String cp) throws IOException {
        

        Socket clientSocket = new Socket("localhost", this.port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
        outToServer.writeBytes("cp|" + cp + '\n');
        String fromServer = inFromServer.readLine();
        clientSocket.close();
  
        
    }
    
}
