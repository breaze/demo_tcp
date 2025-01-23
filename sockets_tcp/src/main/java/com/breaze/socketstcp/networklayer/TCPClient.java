/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.breaze.socketstcp.networklayer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author breaze
 */
public class TCPClient {
    private String serverAddress;
    private int serverPort;
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        // Establecer conexión con el servidor
        clientSocket = new Socket(serverAddress, serverPort);
        System.out.println("Conexión establecida con el servidor.");
        
        // Inicializar los streams
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    public String sendMessage(String firstName, String lastName) {
        String response = "error";
        try {
            // Conectar al servidor
            connect();

            // Enviar datos al servidor
            String message = firstName + "|" + lastName;
            System.out.println("Enviando: " + message);
            outputStream.writeUTF(message);

            // Leer respuesta del servidor
            response = inputStream.readUTF();
            System.out.println("Respuesta del servidor: " + response);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Cerrar los recursos
            closeConnection();
        }
        return response;
    }

    public void closeConnection() {
        try {
            if (inputStream != null) inputStream.close();
            if (outputStream != null) outputStream.close();
            if (clientSocket != null) clientSocket.close();
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}