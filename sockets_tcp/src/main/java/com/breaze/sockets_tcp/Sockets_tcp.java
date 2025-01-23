/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.breaze.sockets_tcp;

import com.breaze.sockets_tcp.network_layer.TCPClient;
import java.util.Scanner;

/**
 *
 * @author breaze
 */
public class Sockets_tcp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese su nombre");
        String name = in.nextLine();
        System.out.println("Ingrese su apellido");
        String lastName = in.nextLine();
        System.out.println(name+" "+lastName);
        
        TCPClient client = new TCPClient();
        client.setServerIP("");
        client.setPort(0);
        String msg = name+"|"+lastName;
        String res = client.connect(msg);
        
    }
}
