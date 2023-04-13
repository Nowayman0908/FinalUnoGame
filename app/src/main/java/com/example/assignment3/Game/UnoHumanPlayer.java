package com.example.assignment3.Game;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.assignment3.R;
import com.example.assignment3.Uno.UnoCard.UnoCard;
import com.example.assignment3.Uno.UnoCard.UnoDrawCardAction;
import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoCard.UnoPlayCardAction;
import com.example.assignment3.Uno.UnoCard.UnoSpecialCard;
import com.example.assignment3.Uno.UnoGameState;

import GameFramework.GameMainActivity;
import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameHumanPlayer;

public class UnoHumanPlayer extends GameHumanPlayer implements OnClickListener {
    private UnoGameState firstInstance;
    private ImageButton cardSlotOne = null;
    private ImageButton cardSlotTwo = null;
    private ImageButton cardSlotThree = null;
    private ImageButton cardSlotFour = null;
    private ImageButton discardPile = null;
    private TextView status = null;
    private Button leftButton = null;
    private Button rightButton = null;
    private Button unoButton = null;

    private int handCounter = 1;
    private int indexOfCard = -1;

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
            setPlayedCard();
            if (((UnoGameState) info).getHandSize() == 2){
                unoButton.setVisibility(View.VISIBLE);
            }
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
        this.leftButton = activity.findViewById(R.id.leftButton);
        leftButton.setOnClickListener(this);
        this.rightButton = activity.findViewById(R.id.rightButton);
        rightButton.setOnClickListener(this);
        this.discardPile = activity.findViewById(R.id.playedCard);
        this.unoButton = activity.findViewById(R.id.unoButton);
        unoButton.setOnClickListener(this);
        this.status = activity.findViewById(R.id.status);
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
        } else if (color == UnoCard.RED) {
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
        } else if (color == UnoCard.GREEN) {
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
        } else if (color == UnoCard.BLUE) {
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
        } else if (color == UnoCard.YELLOW) {
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
        else{
            cardSlot.setImageResource(R.drawable.unocard_grey);
        }
    }

    public void setCardView(){
        ArrayList<UnoCard> playerHand = firstInstance.getHandArray().get(firstInstance.getPlayerID());
        int arrayLength = Math.min(playerHand.size(), 4);
        for (int i = handCounter; i <= arrayLength+(handCounter-1); i++) {
            //The game crashes if there are less than four cards to display.
            if(playerHand.size() <= i){
                break;
            }
            int cardColor = playerHand.get(i).getColor();
            int cardNum = -1;
            int ability = -1;
            if (playerHand.get(i-1) instanceof UnoNumberCard) {
                cardNum = ((UnoNumberCard) playerHand.get(i-1)).getNum();
            } else if (playerHand.get(i-1) instanceof UnoSpecialCard) {
                ability = ((UnoSpecialCard) playerHand.get(i-1)).getAbility();
            }
            switch (i-1) {
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
            indexOfCard = i;
        }
        if(arrayLength < 4){
            int changeCard = 4 - arrayLength;
            switch (changeCard){
                case 1:
                    setImage(cardSlotFour, -99, -99, -99);
                    break;
                case 2:
                    setImage(cardSlotThree,-99, -99, -99);
                    setImage(cardSlotFour, -99, -99, -99);
                    break;
                case 3:
                    setImage(cardSlotTwo, -99, -99, -99);
                    setImage(cardSlotThree,-99, -99, -99);
                    setImage(cardSlotFour, -99, -99, -99);
                    break;
                case 4:
                    setImage(cardSlotOne,-99, -99, -99);
                    setImage(cardSlotTwo, -99, -99, -99);
                    setImage(cardSlotThree,-99, -99, -99);
                    setImage(cardSlotFour, -99, -99, -99);
                    break;
            }
        }
    }

    public void setPlayedCard(){
        UnoCard playedCard = (UnoCard) firstInstance.getDiscardPile().get(firstInstance.getDiscardPile().size() - 1);
        int pcColor = playedCard.getColor();
        int pcNum = -1;
        int pcAbility = -1;
        if(playedCard instanceof UnoSpecialCard){
            pcAbility = ((UnoSpecialCard) playedCard).getAbility();
        }
        else if(playedCard instanceof UnoNumberCard){
            pcNum = ((UnoNumberCard) playedCard).getNum();
        }
        setImage(discardPile,pcColor,pcNum,pcAbility);
    }
    @Override
    public void onClick(View v) {

        setPlayedCard();

        if(v.equals(leftButton) && handCounter > 1){
            handCounter--;
            setCardView();
        }
        else if(v.equals(leftButton) && handCounter == 1){
            flash(Color.RED,100);
        }
        else if(v.equals(rightButton)){
            handCounter++;
            setCardView();
        }

        //If the Draw Card button is clicked.
        // the local game automatically tells the player if it's a valid/invalid move
        if (v.equals(myActivity.findViewById(R.id.deckSlot))) {
            UnoDrawCardAction draw = new UnoDrawCardAction(this);
            game.sendAction(draw);
        }
        //If the Play Card button is clicked.
        else if (v.equals(myActivity.findViewById(R.id.cardSlot1)) || v.equals(myActivity.findViewById(R.id.cardSlot2)) || v.equals(myActivity.findViewById(R.id.cardSlot3)) || v.equals(myActivity.findViewById(R.id.cardSlot4))) {

            setCardView();
            if(v.equals(myActivity.findViewById(R.id.cardSlot1))){
                UnoPlayCardAction play = new UnoPlayCardAction(this,indexOfCard - 4);
                game.sendAction(play);
            }
            else if(v.equals(myActivity.findViewById(R.id.cardSlot2))){
                UnoPlayCardAction play = new UnoPlayCardAction(this,indexOfCard - 3);
                game.sendAction(play);
            }
            else if(v.equals(myActivity.findViewById(R.id.cardSlot3))){
                UnoPlayCardAction play = new UnoPlayCardAction(this,indexOfCard - 2);
                game.sendAction(play);
            }
            else if(v.equals(myActivity.findViewById(R.id.cardSlot4))){
                UnoPlayCardAction play = new UnoPlayCardAction(this,indexOfCard - 1);
                game.sendAction(play);
            }
        }
        //If the Uno Button is clicked.
        else if (v.equals(myActivity.findViewById(R.id.unoButton))) {
            v.setVisibility(View.INVISIBLE);
        }
        status.setText("");
        for(int k = 1; k < firstInstance.getPlayerNum();k++){
            String playerText = "Player " + k + " hand size: " + firstInstance.getHandArray().get(k).size();
            status.setText(playerText);
        }
    }
}
