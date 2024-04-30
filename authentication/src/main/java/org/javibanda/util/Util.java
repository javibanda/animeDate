package org.javibanda.util;

public class Util {

    public static boolean requestIsMobile(String userAgent){
        return userAgent.contains("apiRest/1 CFNetwork/");
    }
}
