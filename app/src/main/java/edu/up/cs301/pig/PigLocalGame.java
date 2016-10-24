package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.os.Bundle;
import android.util.Log;

import java.util.Random;

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    PigGameState state;
    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        state = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {

        for (int i = 0; i < players.length; i++) {
            if (getPlayerIdx(players[i]) == playerIdx) {
                state.setTurn(playerIdx);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {

        if (action instanceof PigHoldAction){
            if (state.turn == 0 ) {

                state.Player1Score = state.Player1Running+state.Player1Score;
                state.Player1Running = 0;

                state.turn = 1; //changes the turn to the other player's

                return true;
            }
            else if (state.turn == 1) {
                state.Player2Score = state.Player2Running+state.Player2Score;
                state.Player2Running = 0;

                state.turn = 0; //changes the turn to the other player's

                return true;
            }
        }
        else if (action instanceof PigRollAction) {
            Random ran = new Random();
            int die = (ran.nextInt(6-1)+1);

            state.setCurrentValue(die);

            if (state.turn == 0) {
                if (die == 1) {
                    state.Player1Running = 0;

                    state.turn = 1;
                }
                else {
                    state.Player1Running = state.Player1Running+die;
                }
                return true;
            }
            else if (state.turn == 1) {
                if (die == 1) {
                    state.Player2Running = 0;
                    state.turn = 0;
                }
                else {
                    state.Player2Running = state.Player2Running+die;
                }
                return true;
            }
        }
        return false;

    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        PigGameState stateForPlayer = new PigGameState(state);

        p.sendInfo(stateForPlayer);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        if (state.Player1Score >= 50) {
            return "Player 1 has won with a score of: " + state.Player1Score;
        }
        else if (state.Player2Score >= 50) {
            return "Player 2 has won with a score of: " + state.Player2Score;
        }
        return null;
    }

}// class PigLocalGame
