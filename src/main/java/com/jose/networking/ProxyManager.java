package com.jose.networking;



public class ProxyManager {
    
    ProxyObject proxyList[] = {

        new ProxyObject("160.16.209.7:3128", "PY", null),
        new ProxyObject("130.41.47.235:8080", "US", null),
        new ProxyObject("91.216.117.193:80", "PT", "Vodafone")

    };
    ProxyObject currentProxy = null;

 

    public String useProxy(){
        currentProxy = proxyList[2];
        return currentProxy.address;
    }
 
}
