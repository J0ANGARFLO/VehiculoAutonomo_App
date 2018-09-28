package com.nube.joang.vehiculoautonomo;

public final class ConstantesRestApi {
    public static final String ROOT_URL = "http://13.85.14.231:3002/";
    public static final String REST_API = "api/";
//    public static final String KEY_GET_STARTENGINE = "startEngine/?startEngine=true";
    public static final String KEY_GET_STARTENGINE = "startEngine/?startEngine={action_SE}";
    public static final String URL_GET_STARTENGINE = ROOT_URL + REST_API + KEY_GET_STARTENGINE;
}
