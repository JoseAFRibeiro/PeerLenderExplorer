package com.jose;

import com.jose.selenium.mintos.Mintos;
import com.jose.selenium.peerberry.PeerBerry;
import com.jose.utils.CredentialReader;

/**
 * Hello world!
 *
 */
public class App 
{   
    public static void main( String[] args )
    {   
        String creds[];
        try {
         creds = CredentialReader.readCredentials();

         /*Mintos mintos = new Mintos();
         mintos.login(creds[0], creds[1]);
         mintos.getData();
         mintos.driver.quit();*/
 
         PeerBerry p = new PeerBerry();
         p.login(creds[0], creds[1]);
         p.driver.quit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
