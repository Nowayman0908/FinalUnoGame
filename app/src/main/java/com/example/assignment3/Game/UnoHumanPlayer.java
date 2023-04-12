package com.example.assignment3.Game;

import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import com.example.assignment3.R;
import com.example.assignment3.Uno.UnoCard.UnoCard;
import com.example.assignment3.Uno.UnoCard.UnoDrawCardAction;
import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoCard.UnoPlayCardAction;
import com.example.assignment3.Uno.UnoCard.UnoSpecialCard;
import com.example.assignment3.Uno.UnoGameState;
import com.google.android.material.textfield.TextInputEditText;

import GameFramework.GameMainActivity;
import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameHumanPlayer;

public class UnoHumanPlayer extends GameHumanPlayer implements OnClickListener {
    private UnoGameState firstInstance;
    private ImageButton cardSlotOne = null;
    private ImageButton cardSlotTwo = null;
    private ImageButton cardSlotThree = null;
    private ImageButton cardSlotFour = null;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public UnoHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if (info instanceof UnoGameState) {
            firstInstance = (UnoGameState) info;
            setCardView();
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);
        this.cardSlotOne = activity.findViewById(R.id.cardSlot1);
        cardSlotOne.setOnClickListener(this);
        this.cardSlotTwo = activity.findViewById(R.id.cardSlot2);
        cardSlotTwo.setOnClickListener(this);
        this.cardSlotThree = activity.findViewById(R.id.cardSlot3);
        cardSlotThree.setOnClickListener(this);
        this.cardSlotFour = activity.findViewById(R.id.cardSlot4);
        cardSlotFour.setOnClickListener(this);
        ImageButton deckSlot = activity.findViewById(R.id.deckSlot);
        deckSlot.setOnClickListener(this);

    }

    public void setImage(ImageButton cardSlot, int color, int num, int ability) {
        if (color == UnoCard.RED && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.red_0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.red_1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.red_2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.red_3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.red_4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.red_5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.red_6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.red_7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.red_8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.red_9);
                    break;
            }
        } else if (color == UnoCard.RED && ability != -1) {
            switch (ability) {
                case UnoSpecialCard.SKIP:
                    cardSlot.setImageResource(R.drawable.red_skip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.red_drawtwo);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.red_reverse);
                    break;
            }
        } else if (color == UnoCard.GREEN && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.green_0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.green_1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.green_2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.green_3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.green_4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.green_5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.green_6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.green_7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.green_8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.green_9);
                    break;
            }
        } else if (color == UnoCard.GREEN && ability != -1) {
            switch (ability) {
                case UnoSpecialCard.SKIP:
                    cardSlot.setImageResource(R.drawable.green_skip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.green_drawtwo);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.green_reverse);
                    break;
            }
        } else if (color == UnoCard.BLUE && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.blue_0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.blue_1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.blue_2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.blue_3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.blue_4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.blue_5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.blue_6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.blue_7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.blue_8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.blue_9);
                    break;
            }
        } else if (color == UnoCard.BLUE && ability != -1) {
            switch (ability) {
                case UnoSpecialCard.SKIP:
                    cardSlot.setImageResource(R.drawable.blue_skip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.blue_drawtwo);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.blue_reverse);
                    break;
            }
        } else if (color == UnoCard.YELLOW && ability == -1) {
            switch (num) {
                case 0:
                    cardSlot.setImageResource(R.drawable.yellow_0);
                    break;
                case 1:
                    cardSlot.setImageResource(R.drawable.yellow_1);
                    break;
                case 2:
                    cardSlot.setImageResource(R.drawable.yellow_2);
                    break;
                case 3:
                    cardSlot.setImageResource(R.drawable.yellow_3);
                    break;
                case 4:
                    cardSlot.setImageResource(R.drawable.yellow_4);
                    break;
                case 5:
                    cardSlot.setImageResource(R.drawable.yellow_5);
                    break;
                case 6:
                    cardSlot.setImageResource(R.drawable.yellow_6);
                    break;
                case 7:
                    cardSlot.setImageResource(R.drawable.yellow_7);
                    break;
                case 8:
                    cardSlot.setImageResource(R.drawable.yellow_8);
                    break;
                case 9:
                    cardSlot.setImageResource(R.drawable.yellow_9);
                    break;
            }
        } else if (color == UnoCard.YELLOW && ability != -1) {
            switch (ability) {
                case UnoSpecialCard.SKIP:
                    cardSlot.setImageResource(R.drawable.yellow_skip);
                    break;
                case UnoSpecialCard.DRAWTWO:
                    cardSlot.setImageResource(R.drawable.yellow_drawtwo);
                    break;
                case UnoSpecialCard.REVERSE:
                    cardSlot.setImageResource(R.drawable.yellow_reverse);
                    break;
            }
        } else if (color == UnoCard.COLORLESS) {
            switch (ability) {
                case UnoSpecialCard.WILD:
                    cardSlot.setImageResource(R.drawable.wild);
                    break;
                case UnoSpecialCard.DRAWFOUR:
                    cardSlot.setImageResource(R.drawable.drawfour);
                    break;
            }
        }
    }

    public void setCardView(){
        ArrayList<UnoCard> playerHand = firstInstance.getHandArray().get(firstInstance.getPlayerID());
        for (int i = 0; i < 4; i++) {
            int cardColor = playerHand.get(i).getColor();
            int cardNum = -1;
            int ability = -1;
            if (playerHand.get(i) instanceof UnoNumberCard) {
                cardNum = ((UnoNumberCard) playerHand.get(i)).getNum();
            } else if (playerHand.get(i) instanceof UnoSpecialCard) {
                ability = ((UnoSpecialCard) playerHand.get(i)).getAbility();
            }
            switch (i) {
                case 0:
                    setImage(cardSlotOne, cardColor, cardNum, ability);
                    break;
                case 1:
                    setImage(cardSlotTwo, cardColor, cardNum, ability);
                    break;
                case 2:
                    setImage(cardSlotThree, cardColor, cardNum, ability);
                    break;
                case 3:
                    setImage(cardSlotFour, cardColor, cardNum, ability);
                    break;
            }


        }
    }

    @Override
    public void onClick(View v) {

        ArrayList<UnoCard> hand = (ArrayList<UnoCard>) firstInstance.getHandArray().get(firstInstance.getPlayerID());

        //If the Draw Card button is clicked.
        // the local game automatically tells the player if it's a valid/invalid move
        if (v.equals(myActivity.findViewById(R.id.deckSlot))) {
            UnoDrawCardAction draw = new UnoDrawCardAction(this);
            game.sendAction(draw);
        }
        //If the Play Card button is clicked.
        else if (v.equals(myActivity.findViewById(R.id.cardSlot1)) || v.equals(myActivity.findViewById(R.id.cardSlot2)) || v.equals(myActivity.findViewById(R.id.cardSlot3)) || v.equals(myActivity.findViewById(R.id.cardSlot4))) {

            setCardView();
               /*for (int j = 0; j < hand.size() - 1; j++) {
                   if (hand.get(j) instanceof UnoNumberCard) {
                       if (((UnoNumberCard) hand.get(j)).getNum() == cardNumber && hand.get(j).getColor() == cardColor) {
                           UnoPlayCardAction play = new UnoPlayCardAction(this, j);
                           game.sendAction(play);
                           //This will be checking if the Uno Button has been selected.
                           if(hand.size() == 1){
                               UnoDrawCardAction draw = new UnoDrawCardAction(this);
                               game.sendAction(draw);
                               game.sendAction(draw);
                           }
                           break;
                       }
                   } else if (hand.get(j) instanceof UnoSpecialCard) {
                       if (((UnoSpecialCard) hand.get(j)).getAbility() == cardNumber && hand.get(j).getColor() == cardColor) {
                           UnoPlayCardAction play = new UnoPlayCardAction(this, j);
                           game.sendAction(play);
                           break;
                       }
                   }
               }*/


        }
        //If the Uno Button is clicked.
        else if (v.equals(myActivity.findViewById(R.id.unoButton))) {

        }

    }
}
