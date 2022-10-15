package com.jose.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CredentialReader {
    
    public static String[] readCredentials() throws IOException{
        
        String creds[] = new String[2];

        File f = new File(".\\src\\res\\secret_files\\credentials.txt");
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(f));
            creds[0] = reader.readLine();
            creds[1] = reader.readLine();
            
        } catch (Exception e) {
            throw new FileNotFoundException("Credential file not found");
        }
        finally{
            if(reader != null)
                reader.close();
        }
        
        
        return creds;

    }

}
