package com.example.tictactoeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridLayout gl;
    private Button resetBtn;
    private TextView fpScoreField;
    private TextView spScoreField;

    private static ClickedPosHandler cp = new ClickedPosHandler();
    private Button[] buttons = new Button[9];

    private int PlayerOneScore = 0;
    private int PlayerTwoScore = 0;

    private int currentPlayer = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable;
        colorDrawable = new ColorDrawable(Color.parseColor("#FF018786"));

        actionBar.setBackgroundDrawable(colorDrawable);

        fpScoreField = findViewById(R.id.score_p1);
        spScoreField = findViewById(R.id.score_p2);

        resetBtn = findViewById(R.id.reset_button);
        gl = findViewById(R.id.grid_layout);

        setSingleClickListener(gl);

        resetBtn.setOnClickListener(v -> {
            resetFields();
            fpScoreField.setText(": 0");
            spScoreField.setText(": 0");

            PlayerOneScore = 0;
            PlayerTwoScore = 0;
        });
    }

    private void setSingleClickListener(GridLayout gl) {
        int glLength = gl.getChildCount();

        for(int i =0; i < glLength ; i++) {
            int index = i;
            Button btn = (Button) gl.getChildAt(index);
            buttons[index] = btn;

            btn.setOnClickListener(v -> {
                boolean isPosAv = checkPosAvailable(index);
                if(isPosAv) {
                  if(currentPlayer == 2) {
                      btn.setText("X");
                      currentPlayer = 1;
                      cp.addFpPos(index);
                  } else {
                      btn.setText("O");
                      currentPlayer = 2;
                      cp.addSpPos(index);
                  }
                    checkForWin();
                    checkForDraw();
                }
            });
        }
    }

    public void checkForWin() {
        if(cp.checkRightSequence(cp.getFpPos())) {
            PlayerOneScore += 1;
            fpScoreField.setText(String.format(": %d",PlayerOneScore));
            resetFields();
        }
        if (cp.checkRightSequence(cp.getSpPos())) {
            PlayerTwoScore += 1;
            spScoreField.setText(String.format(": %d",PlayerTwoScore));
            resetFields();
        }
    }

    public void checkForDraw() {
        if(cp.getClickedPositions().size() == 9) {
            resetFields();
        }
    }

    private boolean checkPosAvailable(int i) {
        ArrayList clickedPos = cp.getClickedPositions();

        for(int g=0; g < clickedPos.size(); g++) {
            int b = (int) clickedPos.get(g);
            if( b == i) return false;
        }
        return true;
    }

    private void resetFields() {
        for(int i =0; i < buttons.length; i++) {
            buttons[i].setText("");
        }
        cp.clearClickedPos();
        currentPlayer = 2;
    }
}