package com.example.assignment3.Uno;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;

import com.example.assignment3.R;
import com.google.android.material.textfield.TextInputEditText;

import GameFramework.GameMainActivity;
import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameHumanPlayer;

public class UnoHumanPlayer extends GameHumanPlayer implements OnClickListener  {
    UnoGameState firstInstance = new UnoGameState();
    UnoLocalGame localGame = new UnoLocalGame();
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
        if(info instanceof UnoGameState){
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);

        Button playGame = activity.findViewById(R.id.run);
        playGame.setOnClickListener(this);
        Button drawCard = activity.findViewById(R.id.drawCard);
        drawCard.setOnClickListener(this);
        Button playCard = activity.findViewById(R.id.playCard);
        playCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        EditText text = myActivity.findViewById(R.id.editText1);
        text.setText("");


        //If the Draw Card button is clicked.
        if(v.equals(myActivity.findViewById(R.id.drawCard))){
            if(localGame.canMove(firstInstance.getPlayerID())) {
                firstInstance.incremHandSize(0, true);
                UnoDrawCardAction draw = new UnoDrawCardAction(this);
                localGame.sendAction(draw);
            }
        }
        //If the Play Card button is clicked.
        else if(v.equals(myActivity.findViewById(R.id.playCard))){
            //The text box above the buttons is read to get the card to play.
            UnoPlayCardAction play = new UnoPlayCardAction(this);
            if(localGame.canMove(firstInstance.getPlayerID())) {
                TextInputEditText cardInfo = myActivity.findViewById(R.id.cardToPlay);
                String cardInput = cardInfo.getText().toString().toLowerCase();
                int cardNumber = -1;
                int cardColor = -1;
                for (int i = 0; i < cardInput.length(); i++) {
                    if (cardInput.charAt(i) == ' ') {
                        cardNumber = cardInput.charAt(i - 1);
                        switch (cardInput.substring(i, cardInput.length())) {
                            case "red":
                                cardColor = 0;
                                break;
                            case "green":
                                cardColor = 1;
                                break;
                            case "blue":
                                cardColor = 2;
                                break;
                            case "yellow":
                                cardColor = 3;
                                break;
                            default:
                                cardInfo.setText("Error");
                        }
                    }

                }

                firstInstance.setCardInPlay(0, cardNumber, cardColor);
                localGame.makeMove(play);
                firstInstance.playCard();
            }
        }
        //If the Uno Button is clicked.
        else if(v.equals(myActivity.findViewById(R.id.unoButton))){
            if(localGame.canMove(firstInstance.getPlayerID())) {}
            //This should do something.
        }
        ArrayList <UnoCard> hand = (ArrayList<UnoCard>) firstInstance.getHandArray().get(firstInstance.getPlayerID());
        text.append("The Player's hand size is " + firstInstance.getHandSize() + ".\n");
        text.append("There are " + firstInstance.getPlayerNum() + " players in the game.\n");
        text.append("The current number in play is " + firstInstance.getNumInPlay() + ".\n");
        text.append("The current color in play is " + firstInstance.getColorInPlay() + ".\n");
        for(int i = 0; i < hand.size() - 1; i++){
            String card = hand.get(i) + " ";
            int switchy = (int) hand.get(i).getColor();
            switch (switchy){
                case 0:
                    card = card + "Red, ";
                    break;
                case 1:
                    card = card + "Green, ";
                    break;
                case 2:
                    card = card + "Blue, ";
                    break;
                case 3:
                    card = card + "Yellow, ";
                    break;
            }
            text.append(card);

        }
    }
}
