package com.example.tictactoeapp;


public class ClickedPosHandler {

    // player 1: 0
    // player 2: 1
    // empty: 2
    private int[] clickedPositions = new int[] {2,2,2,2,2,2,2,2,2};

    private int[][] winningPositions = new int[][] {
            {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},
            {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}, {0,1,2}
    };

    public boolean checkRightSequence(int playerId) {
        boolean test = false;

        for(int i = 0; i < winningPositions.length; i++) {
            if(
                clickedPositions[winningPositions[i][0]] == playerId &&
                clickedPositions[winningPositions[i][1]] == playerId &&
                clickedPositions[winningPositions[i][2]] == playerId
            ) {
                test = true;
                break;
            } else {
                test = false;
            }
        }

        return test;
    }

    public void setClickedPos(int playerId, int index) {
        clickedPositions[index] = playerId;
    }

    public int[] getClickedPositions() {
        return clickedPositions;
    }

    public void clearClickedPos() {
        for(int i = 0; i < clickedPositions.length; i++) {
            clickedPositions[i] = 2;
        }
    }
}
