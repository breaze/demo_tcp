/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.breaze.socketstcpserver.networklayer;

import business.NamesManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 *
 * @author breaze
 */
public class TCPServer {
    private int port;

    public TCPServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            //ServerSocket serverSocket = new ServerSocket(port);
            SSLServerSocketFactory socketFactroy = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
            SSLServerSocket serverSocket = (SSLServerSocket)socketFactroy.createServerSocket(port);
            System.out.println("Server listening on port: " + port);

            while (true) {
                // Accept connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("connected from: " + clientSocket.getInetAddress());

                // Create input and output
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                // Read client message
                String clientMessage = inputStream.readUTF();
                clientMessage = clientMessage.trim();
                String[] parts = clientMessage.split(":");
                System.out.println("client message: " + clientMessage);
                NamesManager manager = new NamesManager(parts[0], parts[1]);
                String response = manager.returnInfo();
                System.out.println("Response: "+response);
                
                // Send response
                outputStream.writeUTF(response);

                // Close connection
                clientSocket.close();
                System.out.println("Connection closed.");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
