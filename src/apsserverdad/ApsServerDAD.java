/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsserverdad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author lsfo
 */
public class ApsServerDAD {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int serverPort = 8000;
        ServerSocket listenSocket = new ServerSocket(serverPort);

        while (true) {
            
            Socket clientSocket = listenSocket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            String data = (String) in.readObject();
            System.out.println(data);
            
        }
        
    }

}
