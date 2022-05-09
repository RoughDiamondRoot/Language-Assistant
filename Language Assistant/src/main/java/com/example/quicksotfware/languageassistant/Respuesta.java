package com.example.quicksotfware.languageassistant;

/**
 * Created by janser on 19/02/2018.
 */

public class Respuesta {

    private  String cuestion;
    private  String respuesta;

    public Respuesta(String cuestion, String respuesta){
        this.cuestion = cuestion;
        this.respuesta = respuesta;
    }


    public String getCuestion(){
        return  cuestion;
    }


    public String getRespuesta(){
        return  respuesta;
    }



}
