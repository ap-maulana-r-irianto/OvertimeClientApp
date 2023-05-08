package com.metrodata.clientapp.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.charset.Charset;

public class BasicHeader {

    public static String createBasicToken(String username, String password){
        String auth = username + ":" + password;
        byte[] encodeAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return new String(encodeAuth);
    }

    public static HttpHeaders createBasicHeader(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new HttpHeaders(){{
            set("Authorization","Basic " + createBasicToken(
                    authentication.getName(),
                    authentication.getCredentials().toString()
            ));
        }};
    }
}
