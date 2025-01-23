/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.breaze.socketstcpserver;

import com.breaze.socketstcpserver.networklayer.TCPServer;

/**
 *
 * @author breaze
 */
public class SocketsTCPServer {

    public static void main(String[] args) {
        int port = 9090;

        TCPServer server = new TCPServer(port);
        server.start();
    }
}
