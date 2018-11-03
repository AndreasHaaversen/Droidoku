package com.lambda.scifarer.droidoku;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class stdGame extends AppCompatActivity {

    private Sudoku game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_game);

        this.game = new Sudoku(9);

        GridView gridView = findViewById((R.id.gridview));
        final TextView cell_content = findViewById((R.id.cell_content));

        final ArrayList<String> contents = game.getGameArray();

        gridView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contents) {
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView textView = (TextView) view;

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                );
                textView.setLayoutParams(lp);

                // Get the TextView LayoutParams
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();

                // Set the width of TextView widget (item of GridView)
                params.width = getPixelsFromDPs(stdGame.this, 168);

                // Set the TextView layout parameters
                textView.setLayoutParams(params);

                // Display TextView text in center position
                textView.setGravity(Gravity.CENTER);

                // Set the TextView text font family and text size
                textView.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

                // Set the TextView text (GridView item text)
                textView.setText(contents.get(position));

                // Set the TextView background color
                //textView.setBackgroundColor(Color.parseColor("#FFFFFF"));

                return textView;
            }
        });
    }

    public static int getPixelsFromDPs(Activity activity, int dps) {
        Resources r = activity.getResources();
        return (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
    }
}
