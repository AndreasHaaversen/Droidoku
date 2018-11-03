package com.lambda.scifarer.droidoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openAbout(View view){
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
    }

    public void openNewGame(View view) {
        Intent intent = new Intent(this, stdGame.class);
        startActivity(intent);
    }
}
