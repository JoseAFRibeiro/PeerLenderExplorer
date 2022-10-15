package com.jose.UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandLineManager {
    
    public String code2FA(){

        String input;

        System.out.println("This website requires two factor authentication! Please enter the code from your authenticator:");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            input = reader.readLine();    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return input;
    }
}
