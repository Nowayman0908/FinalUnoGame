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

        //Retrieves all the buttons on the layout activity and sets the Human Players as the on Click Listener.
        Button playGame = activity.findViewById(R.id.run);
        playGame.setOnClickListener(this);
        Button drawCard = activity.findViewById(R.id.drawCard);
        drawCard.setOnClickListener(this);
        Button playCard = activity.findViewById(R.id.playCard);
        playCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Finds the text that is used to display the state of the game and sets it blank.
        EditText text = myActivity.findViewById(R.id.editText1);
        text.setText("");

        ArrayList <UnoCard> hand = (ArrayList<UnoCard>) firstInstance.getHandArray().get(firstInstance.getPlayerID());


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
                String cardInput = cardInfo.getText().toString().trim().toLowerCase();
                int cardNumber = -1;
                int cardColor = -1;
                for (int i = 0; i < cardInput.length(); i++) {
                    if (cardInput.charAt(i) == ' ') {
                        cardNumber = cardInput.charAt(i - 1);
                        switch (cardInput.substring(i)) {
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
                for(int j = 0; j < hand.size() - 1; j++){
                    if(hand.get(j) instanceof UnoNumberCard)
                    if(((UnoNumberCard) hand.get(j)).getNum() == cardNumber && hand.get(j).getColor() == cardColor){
                        hand.remove(j);
                        break;
                    }
                }
                firstInstance.setCardInPlay(firstInstance.getPlayerID(), cardNumber, cardColor);
                localGame.makeMove(play);
                firstInstance.playCard();
            }
        }
        //If the Uno Button is clicked.
        else if(v.equals(myActivity.findViewById(R.id.unoButton))){
            if(localGame.canMove(firstInstance.getPlayerID())) {}
            //This should do something.
        }

        //Adding information to the UI that the player needs to play the game.
        text.append("The Player's hand size is " + firstInstance.getHandSize() + ".\n");
        text.append("There are " + firstInstance.getPlayerNum() + " players in the game.\n");
        switch (firstInstance.getColorInPlay()){
            case 0:
                text.append("The current color in play is Red" + ".\n");
                break;
            case 1:
                text.append("The current color in play is Green" + ".\n");
                break;
            case 2:
                text.append("The current color in play is Blue" + ".\n");
                break;
            case 3:
                text.append("The current color in play is Yellow" + ".\n");
                break;
        }
        text.append("The current number in play is " + firstInstance.getNumInPlay() + ".\n");

        for(int i = 0; i < hand.size() - 1; i++){
            String card = "-1";
            if(hand.get(i) instanceof  UnoNumberCard) {
                card = ((UnoNumberCard) hand.get(i)).getNum() + " ";
            }
            else if (hand.get(i) instanceof UnoSpecialCard){
                card = ((UnoSpecialCard) hand.get(i)).getAbility() + " ";
            }
            int switchy = hand.get(i).getColor();
            //Adds the color to the number to display the string on the UI.
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
