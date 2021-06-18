package com.example.tictactoeapp;

import java.util.ArrayList;

public class ClickedPosHandler {

    private ArrayList fpPos;
    private ArrayList spPos;

    private int[][] winningPositions;

    ClickedPosHandler() {
        this.fpPos = new ArrayList();
        this.spPos = new ArrayList();

        this.winningPositions = new int[][] {
                {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},
                {1,4,7}, {2,5,8}, {0,4,8},{2,4,6}
        };
    }

    public boolean checkRightSequence(ArrayList playerClickedPos) {
        boolean test = false;

        for(int i = 0; i < winningPositions.length; i++) {
            if(
                playerClickedPos.contains(winningPositions[i][0]) &&
                playerClickedPos.contains(winningPositions[i][1]) &&
                playerClickedPos.contains(winningPositions[i][2])
            ) {
                test = true;
                break;
            } else {
                test = false;
            }
        }

        return test;
    }

    public ArrayList getFpPos() {
        return fpPos;
    }

    public ArrayList getSpPos() {
        return spPos;
    }

    public ArrayList getClickedPositions() {
        ArrayList arrayList = new ArrayList();

        arrayList.addAll(fpPos);
        arrayList.addAll(spPos);
        return arrayList;
    }



    public void addFpPos(int i) {
         fpPos.add(i);
    }

    public void addSpPos(int i) {
        spPos.add(i);
    }

    public void clearClickedPos() {
        fpPos.clear();
        spPos.clear();
    }
}
