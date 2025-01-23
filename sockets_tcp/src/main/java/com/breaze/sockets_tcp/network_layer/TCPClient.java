/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.breaze.sockets_tcp.network_layer;

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
    private String serverIP;
    private int port;
    private SSLSocket server;
    private DataInputStream input;
    private DataOutputStream output;
    
    /*public TCPClient(String server, int port){
        this.server = server;
        this.port = port;
    }*/
    
    public String connect(String msg){
        String res = null;
        try {
            //this.client = new Socket(this.server, this.port);
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
            System.out.println("sdfads: "+serverIP);
            this.server = (SSLSocket)sslSocketFactory.createSocket(this.serverIP, this.port);

            this.input = new DataInputStream(this.server.getInputStream());
            this.output = new DataOutputStream(this.server.getOutputStream());
            this.output.writeUTF(msg);
            res = this.input.readUTF();
            //String update = this.input.readUTF();
            //System.out.println("changes"+update);
            this.server.close();
        } catch (IOException e) {
            res = e.getMessage();
        }
        return res;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    
}