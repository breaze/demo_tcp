/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.breaze.socketstcpserver.business;

/**
 *
 * @author breaze
 */
public class NamesManager {
    
    private String name;
    private String lastName;

    public NamesManager(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
    
    public String returnInfo(){
        return "Name: "+name + ", last name: "+lastName;
    }
    
}
