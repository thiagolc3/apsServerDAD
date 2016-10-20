/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aps;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author lsfo
 */
public class MainServer {

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

            Servidor s = new Servidor(clientSocket);
            s.start();
        }
    }
}
