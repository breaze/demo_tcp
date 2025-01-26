/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.breaze.socketstcp.networklayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author breaze
 */
public class TCPClient {
    private String serverAddress;
    private int serverPort;
    private SSLSocket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        // Establish connection
        //clientSocket = new Socket(serverAddress, serverPort);
        SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        clientSocket = (SSLSocket)socketFactory.createSocket(serverAddress, serverPort);
        System.out.println("Connection established.");
        
        // Define input and output
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    public String sendMessage(String firstName, String lastName) {
        String response = "error";
        try {
            // connect to server
            connect();

            // Send message to server
            String message = firstName + ":" + lastName;
            System.out.println("Sending: " + message);
            outputStream.writeUTF(message);

            // Read server response
            response = inputStream.readUTF();
            System.out.println("Server response " + response);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Close connection
            closeConnection();
        }
        return response;
    }

    public void closeConnection() {
        try {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (clientSocket != null) clientSocket.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        }
    }
}