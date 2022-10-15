package com.jose.networking;

public class ProxyObject {
    
    String address;
    String country;
    String company;

    public ProxyObject(String address, String country, String company){
        
        this.address = address;
        this.country = country;
        this.company = company;
    }

    public String getAddress(){
        return address;
    } 

    public String getCountry(){
        return country;
    }

    public String getCompany(){
        return company;
    }
}
