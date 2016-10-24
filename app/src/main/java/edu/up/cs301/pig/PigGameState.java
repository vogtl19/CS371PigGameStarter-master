package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by vogtl19 on 10/12/2016.
 */
public class PigGameState extends GameState {
    public int turn;

    public int Player1Score;
    public int Player2Score;

    public int Player1Running;
    public int Player2Running;

    public int currentValue;

    public PigGameState() {
        turn = 0; //Player 1

        Player1Score = 0;
        Player2Score = 0;

        Player1Running = 0;
        Player2Running = 0;

        currentValue = 0;
    }

    public PigGameState(PigGameState orig) {
        this.turn = orig.turn;
        this.Player1Score = orig.Player1Score;
        this.Player2Score = orig.Player2Score;
        this.Player1Running = orig.Player1Running;
        this.Player2Running = orig.Player2Running;
        this.currentValue = orig.currentValue;
    }

    public int getTurn() {
        return turn;
    }

    public int getPlayer1Score() {
        return Player1Score;
    }

    public int getPlayer2Score() {
        return Player2Score;
    }

    public int getPlayer1Running() {
        return Player1Running;
    }

    public int getPlayer2Running() {
        return Player2Running;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setTurn(int initTurn) {
        turn = initTurn;
    }

    public void setPlayer1Score(int initPlayerScore) {
        Player1Score = initPlayerScore;
    }

    public void setPlayer2Score(int initPlayerScore) {
        Player2Score = initPlayerScore;
    }

    public void setPlayer1Running(int initPlayer1Running) {
        Player1Running = initPlayer1Running;
    }

    public void setPlayer2Running(int initPlayer2Running) {
        Player2Running = initPlayer2Running;
    }

    public void setCurrentValue(int initCurrentValue) {
        currentValue = initCurrentValue;
    }
}
