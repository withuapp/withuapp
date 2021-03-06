package com.rla.withu_thesarthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class path extends AppCompatActivity {

    TextToSpeech toSpeech;
    TextView whereto;
    TextView from;
    Button endButton;
    RelativeLayout pathLayout;

    String Initial = "";
    String Final ="";
    String strJson="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        whereto = (TextView) findViewById(R.id.whereTo);
        from = (TextView) findViewById(R.id.from);
        endButton = (Button) findViewById(R.id.endButton);
        pathLayout = (RelativeLayout) findViewById(R.id.pathLayout);
        Intent intent = getIntent();
        Final = intent.getStringExtra("final");
        whereto.setText(Final);
        Initial = intent.getStringExtra("initial");
        from.setText(Initial);

        strJson="{ \"Directions\" :[{\"Initial\":\"Entrance\",\"Final\":\"Principal Office\",\"Instructions\":[" +
                "\"Move Forward 20 steps\",\"Turn right and move forward 10 steps\",\"You are at stairs You are at Stairs There are 10 stairs\",\"You reached Principal Office\"]," +
                "\"Stations\":[\"Front Building\",\"Stage Stairs\",\"Principal Office\"]}] }";

        toSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    toSpeech.setLanguage(Locale.UK);
                }
            }
        });


        // voice instruction
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent);
                toSpeech.speak("Directions ended for " + Final, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
    }
}