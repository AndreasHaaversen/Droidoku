package com.lambda.scifarer.droidoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class setup_game extends AppCompatActivity {

    private SeekBar SizeBar, DifficultyBar;
    private int size = 9, difficulty = 25;
    private TextView SizeText, DifficultyText;
    private List<Integer> validValues = Arrays.asList(2, 4, 9, 12, 16);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        SizeBar = findViewById(R.id.SizeSeekBar);
        SizeBar.setProgress(size);
        SizeText = findViewById(R.id.SizeText);
        SizeText.setText("Size: " + size);
        SizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (validValues.contains(i)) {
                    SizeText.setText("Size: " + i);
                    size = i;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        DifficultyBar = findViewById(R.id.DifficultySeekBar);
        DifficultyBar.setProgress(difficulty);
        DifficultyText = findViewById(R.id.DifficultyText);
        DifficultyText.setText("Difficulty: " + difficulty);
        DifficultyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                DifficultyText.setText("Difficulty: " + i);
                difficulty = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void startNewGame(View view) {
        Intent intent = new Intent(this, stdGame.class);
        intent.putExtra("BOARDSIZE", size);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
    }
}
