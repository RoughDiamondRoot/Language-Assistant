package com.example.quicksotfware.languageassistant;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;

//40.1
//com.example.janser.ejemplo

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private static final int RECONOCEDOR_VOZ = 7;
    private TextView escuchando;
    private TextView respuesta;
    private ArrayList<Respuesta> respuest;
    private TextToSpeech leer;
    private CameraManager Camara;
    private String idCamara;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Camara = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            idCamara = Camara.getCameraIdList()[0];
        }catch (Exception e){
            e.printStackTrace();
        }
        inicializar();


    }

    @Override
    // resivir resultado
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //el intent nos esta pasando  requestCode, resultCode, data

        //evaluar si el resultado es correcto -> RESULT_OK  y  proviene de RECONOCEDOR_VOZ
        if(resultCode == RESULT_OK && requestCode == RECONOCEDOR_VOZ){
            // lo almacenamos en un arraylist de string que nos esta trayendo la data del intent
            ArrayList<String> reconocido = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String escuchado = reconocido.get(0);
            escuchando.setText(escuchado);
            prepararRespuesta(escuchado);
        }

    }

    private void prepararRespuesta(String escuchado) {
        String normalizar = Normalizer.normalize(escuchado, Normalizer.Form.NFD);
        String sintilde = normalizar.replaceAll("[^\\p{ASCII}]", "");

        int resultado;
        String respuesta = respuest.get(0).getRespuesta();


        for(int i = 0; i  < respuest.size(); i++){
           resultado = sintilde.toLowerCase().indexOf(respuest.get(i).getCuestion());
           if(resultado != -1){
              respuesta = respuest.get(i).getRespuesta();
           }
        }

        ////--------codigo para encender y apagar el flash
        try {
            if (respuesta == "flash activate") {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Camara.setTorchMode(idCamara, true);
                }
                //System.exit(0);
            }else if (respuesta == "flash deactivate"){

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Camara.setTorchMode(idCamara, false);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        responder(respuesta);

    }


    public  void  responder(String respuestita){
        respuesta.setText(respuestita);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null, null);
        }
        else{
            leer.speak(respuestita, TextToSpeech.QUEUE_FLUSH, null);
        }
    }



    public void inicializar(){

        escuchando = (TextView)findViewById(R.id.textoCaja1);
        respuesta = (TextView)findViewById(R.id.textoCaja2);
        respuest = proveerDatos();
        leer = new TextToSpeech(this, this);
    }


    public ArrayList<Respuesta> proveerDatos(){  //Alt+0191	¿
        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();

        respuestas.add(new Respuesta("","not understood"));
        respuestas.add(new Respuesta("how are you","Fine thanks and you"));
        respuestas.add(new Respuesta("you can do other functions","at this moment I can not do other functions"));
        respuestas.add(new Respuesta("you like your job","If I love my job"));
        respuestas.add(new Respuesta("one","She has a new hat"));
        respuestas.add(new Respuesta("two","Your son is an able man"));
        respuestas.add(new Respuesta("three","This is a book about the history of chile"));
        respuestas.add(new Respuesta("four","Send him the account every month"));
        respuestas.add(new Respuesta("five","This fruit has an acid taste"));
        respuestas.add(new Respuesta("six","We will go across these fields"));
        respuestas.add(new Respuesta("seven","Do a kind act every day"));
        respuestas.add(new Respuesta("eight","This addition is wrong"));
        respuestas.add(new Respuesta("nine","Make an adjustment to this account"));
        respuestas.add(new Respuesta("ten","I have put an advertisement in the paper"));
        respuestas.add(new Respuesta("eleven","We will go after dinner"));
        respuestas.add(new Respuesta("twelve","I will come again tomorrow"));
        respuestas.add(new Respuesta("thirteen","He did that against my desire"));
        respuestas.add(new Respuesta("fourteen","We have reached an agreement"));
        respuestas.add(new Respuesta("fifteen","This morning the air was very cold"));
        respuestas.add(new Respuesta("sixteen","I have done all my work"));
        respuestas.add(new Respuesta("seventeen","He gave me almost all his money"));
        respuestas.add(new Respuesta("eihgteen","My house is among those"));
        respuestas.add(new Respuesta("nineteen","¿What is the amount of his debt?"));
        respuestas.add(new Respuesta("twenty","This is a great amusement for me"));
        respuestas.add(new Respuesta("twenty one","We have an automobile"));
        respuestas.add(new Respuesta("twenty two","He is old and ill"));
        respuestas.add(new Respuesta("twenty three","A right angle has ninety degrees"));
        respuestas.add(new Respuesta("twenty four","I am angry with your brother"));
        respuestas.add(new Respuesta("twenty five","He has all his animals in his farm"));
        respuestas.add(new Respuesta("twenty six","The airplane landed at another airport"));
        respuestas.add(new Respuesta("twenty seven","¿What was his answer to that question?"));
        respuestas.add(new Respuesta("twenty eight","There are ants in your garden"));
        respuestas.add(new Respuesta("twenty nine","¿Have you any news from your father?"));
        respuestas.add(new Respuesta("thirty","This new apparatus is a great invention"));
        respuestas.add(new Respuesta("thirty one","There are six apples on that tree"));
        respuestas.add(new Respuesta("thirty two","He gave his approval to my idea"));
        respuestas.add(new Respuesta("thirty three","There is only one arch in that bridge"));
        respuestas.add(new Respuesta("thirty four","He had an argument with his brother"));
        respuestas.add(new Respuesta("thirty five","His arms are very strong"));
        respuestas.add(new Respuesta("thirty six","Tomorrow the army will be in this town"));
        respuestas.add(new Respuesta("thirty seven","There is art in this picture"));
        respuestas.add(new Respuesta("thirty eight","I am as old as you"));
        respuestas.add(new Respuesta("thirty nine","I will be at my office tomorrow"));
        respuestas.add(new Respuesta("forty","We saw the attack on T.V."));
        respuestas.add(new Respuesta("forty one","I will make a new attempt tomorrow"));
        respuestas.add(new Respuesta("forty two","Pay more attention to your work"));
        respuestas.add(new Respuesta("forty three","There is attraction in a beautiful face"));
        respuestas.add(new Respuesta("forty four","He is a great authority on state government"));
        respuestas.add(new Respuesta("forty five","This machine has an automatic brake"));
        respuestas.add(new Respuesta("forty six","I was awake all night"));
        respuestas.add(new Respuesta("forty seven","Your baby seems ill"));
        respuestas.add(new Respuesta("forty eight","That is the back of the house"));
        respuestas.add(new Respuesta("forty nine","I have bad memory"));
        respuestas.add(new Respuesta("fifty","This bag is full of money"));
        respuestas.add(new Respuesta("fifty one","We had a favorable balance sheet this year"));
        respuestas.add(new Respuesta("fifty two","Give this ball to your son"));


        respuestas.add(new Respuesta("on flash","flash activate"));
        respuestas.add(new Respuesta("deactivate flash", "flash deactivate"));






       // respuestas.add(new Respuesta("hello","hello how are you"));

        return respuestas;
    }





    public void hablar(View v){

        Intent hablar = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);//inten al reconocedor de voz
        hablar.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-MX");// en ingles
        //hablar.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");// pasandole modelo de lenguage al reconocedor de voz
        startActivityForResult(hablar, RECONOCEDOR_VOZ); //lanzar inten y resivir resultado

    }


    @Override
    public void onInit(int i) {

    }



}

