/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.breaze.socketstcp;

import com.breaze.socketstcp.networklayer.TCPClient;
import java.util.Scanner;

/**
 *
 * @author breaze
 */
public class SocketsTCPClient {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese su nombre");
        String name = in.nextLine();
        System.out.println("Ingrese su apellido");
        String lastName = in.nextLine();
        System.out.println(name+" "+lastName);
        
        TCPClient client = new TCPClient("", 0);
        String res = client.sendMessage(name, lastName);
        
    }
}
