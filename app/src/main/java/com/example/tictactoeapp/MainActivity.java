package com.example.tictactoeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static ClickedPosHandler cp = new ClickedPosHandler();

    private GridLayout gl;
    private Button resetBtn;
    private TextView fpScoreField;
    private TextView spScoreField;

    private Button[] buttons = new Button[9];

    private int PlayerOneScore = 0;
    private int PlayerTwoScore = 0;
    private int currentPlayer = 1;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNewActionbarBackground();

        fpScoreField = findViewById(R.id.score_p1);
        spScoreField = findViewById(R.id.score_p2);
        resetBtn = findViewById(R.id.reset_button);
        gl = findViewById(R.id.grid_layout);


        int glLength = gl.getChildCount();
        for(int i =0; i < glLength ; i++) {
            int btnIndex = i;
            Button btn = (Button) gl.getChildAt(i);
            buttons[i] = btn;

            btn.setOnClickListener(v -> {
                boolean isPosAv = checkForPosAvailable(btnIndex);

                if(isPosAv) {
                    if(currentPlayer == 1) {
                        btn.setText("X");
                        currentPlayer = 0;
                    } else {
                        btn.setText("O");
                        currentPlayer = 1;
                    }
                    count++;
                    cp.setClickedPos(currentPlayer, btnIndex);
                    checkForWin();
                    checkForDraw();
                }
            });
        }

        resetBtn.setOnClickListener(v -> {
            resetFields();
            fpScoreField.setText(": 0");
            spScoreField.setText(": 0");

            PlayerOneScore = 0;
            PlayerTwoScore = 0;
        });
    }

    private void setNewActionbarBackground() {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable;
        colorDrawable = new ColorDrawable(Color.parseColor("#FF018786"));
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    public void checkForWin() {
        if(cp.checkRightSequence(0)) {
            PlayerOneScore += 1;
            fpScoreField.setText(String.format(": %d",PlayerOneScore));
            resetFields();
        }
        if (cp.checkRightSequence(1)) {
            PlayerTwoScore += 1;
            spScoreField.setText(String.format(": %d",PlayerTwoScore));
            resetFields();
        }
    }

    public void checkForDraw() {
        if(count == 9) {
            resetFields();
        }
    }

    private boolean checkForPosAvailable(int index) {
        int[] clickedPos = cp.getClickedPositions();

        if( clickedPos[index] == 2) return true;
        return false;
    }

    private void resetFields() {
        for(int i =0; i < buttons.length; i++) {
            buttons[i].setText("");
        }
        cp.clearClickedPos();
        currentPlayer = 1;
    }

}