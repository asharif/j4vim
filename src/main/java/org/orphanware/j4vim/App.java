package org.orphanware.j4vim;

import java.io.IOException;
import org.orphanware.j4vim.tcp.TCPClient;
import org.orphanware.j4vim.tcp.TCPServer;



public class App {
    
    

    public static void main(String[] args) throws IOException, Exception {
        
        boolean isServer = false;
        int port = 9999;
        String prefix = null;
		String code = null;
        String cp = null;
        
        for(int i=0; i < args.length; ++i) {
        
            if(args[i].equals("-h")) {
                String msg = "j4vim v1.0.0\n";
                msg += "author: Arash Sharif\n";
                msg += "license: MIT\n\n";
                msg += "this is the plugin for java autocomplete in vim.  ";
                msg += "It has a server mode that indexes and a client mode that makes requests for autocompletion\n";
                msg += "example use case:\n";
                msg += "1.  start the server with java -jar j4vim.jar -s -p 9999\n";
                msg += "2.  open vim and edit";
                msg += "3.  to get autocomplete on class path use :set blah\n\n";
                msg += "flags:\n\n";
                msg += "\t-s\tstart in server mode\n";
                msg += "\t-p\tuse this port\n";
                msg += "\t-prefix\tget classes with this prefix\n";
                msg += "\t-cp\tset local project classpath";
                System.out.println(msg);
                System.exit(0);
                        
            }
            
            if(args[i].equals("-s")) {
                isServer = true;
            
            }
            
            if(args[i].equals("-p")) {
                
                if(args.length < i+1) {
                    System.out.println("-p flag with no port.  use -p 9999 not just -p");
                    System.exit(1);
                } else {
                    port = Integer.parseInt(args[i+1]);
                }
            }
            
            if(args[i].equals("-prefix")) {
                
                if(args.length < i+1) {
					prefix = "";
                } else {
                    prefix = args[i+1];
                }
                
            }
            
            if(args[i].equals("-cp")) {
                
                if(args.length < i+1) {
                    System.out.println("-cp flag with no classpath");
                    System.exit(1);
                } else {
                    cp = args[i+1];
                }
            }

			if(args[i].equals("-code")) {
				
				if(args.length < i+1) {
					
					System.out.println("-code flag with no code");
					System.exit(1);

				} else {
					code = args[i+1];
				}
				

			}
            
        }
        
        if(isServer) {
            TCPServer server = new TCPServer(port);
            
            server.startServer();
        } else {
            TCPClient client = new TCPClient(port);
            if(cp != null) {
                client.setClassPath(cp);
            }
			if(code != null) {
				client.setCode(code);
			}
            if(prefix != null) {
                System.out.println(client.getMethodsByPrefix(prefix));
            }
            
        }

    }

    
}
