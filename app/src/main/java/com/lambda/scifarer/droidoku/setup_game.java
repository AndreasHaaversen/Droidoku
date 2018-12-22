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
    private int size = 2, difficulty = 1;
    private TextView SizeText, DifficultyText;
    private List<Integer> validSizes = Arrays.asList(2, 4, 9, 16);
    private List<String> difficulties = Arrays.asList("Easy", "Medium", "Hard");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_game);

        SizeBar = findViewById(R.id.SizeSeekBar);
        SizeBar.setMax(validSizes.size() - 1);
        SizeBar.setProgress(size);
        SizeText = findViewById(R.id.SizeText);
        SizeText.setText("Size: " + validSizes.get(size));
        SizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SizeText.setText("Size: " + validSizes.get(i));
                size = validSizes.get(i);
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
        DifficultyBar.setMax(difficulties.size() - 1);
        DifficultyText = findViewById(R.id.DifficultyText);
        DifficultyText.setText("Difficulty: " + difficulties.get(1));
        DifficultyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String text = "Difficulty: " + difficulties.get(i);
                DifficultyText.setText(text);
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
