package edu.up.cs301.pig;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

/**
 * A GUI for a human to play Pig. This default version displays the GUI but is incomplete
 *
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigHumanPlayer extends GameHumanPlayer implements OnClickListener {

    PigGameState state;
    PigHoldAction actionHold;
    PigRollAction actionRoll;
	/* instance variables */

    // These variables will reference widgets that will be modified during play
    private TextView    yourScoreTextView = null;
    private TextView    oppScoreTextView = null;
    private TextView    oppNameTextView = null;
    private TextView    playerScoreTextView = null;
    private TextView    turnTotalTextView   = null;
    private TextView    messageTextView     = null;
    private ImageButton dieImageButton      = null;
    private Button      holdButton          = null;

    // the android activity that we are running
    private GameMainActivity myActivity;

    /**
     * constructor does nothing extra
     */
    public PigHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return
     * 		the top object in the GUI's view heirarchy
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {
        if (info instanceof PigGameState) {
            state = (PigGameState)info;
        }


        //flash if the info object is not a pig object
        else {
            flash(Color.WHITE, 1);
            return;
        }

            String player1Score = ""+state.getPlayer1Score();
            final String player2Score = ""+state.getPlayer2Score();
            String player1Running = ""+state.getPlayer1Running();
            final String player2Running = ""+state.getPlayer2Running();

            playerScoreTextView.setText(player1Score);
//            oppScoreTextView.setText(player2Score);

            //Determine which player to show textview for running score
            if (state.turn == 0) {
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
                        oppNameTextView.setTextColor(Color.BLACK);

                        yourScoreTextView.setTextColor(Color.GREEN);
//                    }
//                }, 2000);
                turnTotalTextView.setText(player1Running);
            }
            if (state.turn == 1) {
                oppScoreTextView.setText(player2Score);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        yourScoreTextView.setTextColor(Color.BLACK);

                        turnTotalTextView.setText(player2Running);

                        oppNameTextView.setTextColor(Color.BLUE);

                        //
                        final Handler handler2 = new Handler();
                        handler2.postDelayed(new Runnable() {
                            public void run() {

                                oppScoreTextView.setText(player2Score);

                                yourScoreTextView.setTextColor(Color.GREEN);

                                oppNameTextView.setTextColor(Color.BLACK);
                                return;
                            }

                        },2500);

                        return;
                    }
                },2000);
            }

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (state.getCurrentValue() == 1) {
                        dieImageButton.setImageResource(R.drawable.face1);
                    }
                    else if (state.getCurrentValue() == 2) {
                        dieImageButton.setImageResource(R.drawable.face2);
                    }
                    else if (state.getCurrentValue() ==  3) {
                        dieImageButton.setImageResource(R.drawable.face3);
                    }
                    else if (state.getCurrentValue() ==  4) {
                        dieImageButton.setImageResource(R.drawable.face4);
                    }
                    else if (state.getCurrentValue() ==  5) {
                        dieImageButton.setImageResource(R.drawable.face5);
                    }
                    else if (state.getCurrentValue() ==  6) {
                        dieImageButton.setImageResource(R.drawable.face6);
                    }
                }
            },600);
            //set the die pictures

            return;

    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {
        if (button == holdButton) {
            actionHold = new PigHoldAction(this);
            game.sendAction(actionHold);
        }
        else if (button == dieImageButton) {
            actionRoll = new PigRollAction(this);
            game.sendAction(actionRoll);
        }
    }// onClick

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity
     * 		the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.pig_layout);

        //Initialize the widget reference member variables
        this.yourScoreTextView = (TextView)activity.findViewById(R.id.yourScoreText);
        this.oppNameTextView = (TextView)activity.findViewById(R.id.oppScoreText);
        this.playerScoreTextView = (TextView)activity.findViewById(R.id.yourScoreValue);
        this.oppScoreTextView    = (TextView)activity.findViewById(R.id.oppScoreValue);
        this.turnTotalTextView   = (TextView)activity.findViewById(R.id.turnTotalValue);
        this.messageTextView     = (TextView)activity.findViewById(R.id.messageTextView);
        this.dieImageButton      = (ImageButton)activity.findViewById(R.id.dieButton);
        this.holdButton          = (Button)activity.findViewById(R.id.holdButton);

        //Listen for button presses
        dieImageButton.setOnClickListener(this);
        holdButton.setOnClickListener(this);

    }//setAsGui

}// class PigHumanPlayer
