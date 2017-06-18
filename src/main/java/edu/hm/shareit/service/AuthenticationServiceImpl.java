package edu.hm.shareit.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/18/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */

public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String authBaseUrl= "https://gmymauth.herokuapp.com/";
    private static final String tokenValidationEndpoint = "services/oauth2/token";
    //logout
    private static final String logoutEndpoint = "services/oauth2/logout";

    @Override
    public boolean validateToken(String token) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        boolean authorized = false;
        String url =authBaseUrl +tokenValidationEndpoint;

        if(token == null || token.isEmpty())
            return authorized;

        URL obj = null;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Authorization", token);

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);

            if(responseCode == 200){
                authorized = true;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return authorized;
    }

    @Override
    public void removeToken(String token) {

        String url =authBaseUrl +tokenValidationEndpoint;
        URL obj = null;
        try {

            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("Authorization", token);

            con.getResponseCode();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
