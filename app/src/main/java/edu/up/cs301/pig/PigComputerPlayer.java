package edu.up.cs301.pig;

import android.util.Log;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    PigGameState state;
    PigHoldAction actionHold;
    PigRollAction actionRoll;
    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof PigGameState){
            state =(PigGameState)info;
        }

        if (!(state.turn == playerNum)){
            return;
        }
        else if (state.turn == playerNum) {

            Random ran = new Random();
            int fiftyfifty = (ran.nextInt(2) + 1);
            actionHold = new PigHoldAction(this);
            actionRoll = new PigRollAction(this);

                if (fiftyfifty == 1) {
                    game.sendAction(actionHold);
                    Log.i("held", "");
                } else if (fiftyfifty == 2) {
                    game.sendAction(actionRoll);
                    Log.i("roll", "");
                }
        }


    }//receiveInfo

}
