package com.example.assignment3.Uno;

import android.graphics.Color;
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
    UnoGameState firstInstance;
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

            firstInstance = (UnoGameState)info;

            //Finds the text that is used to display the state of the game and sets it blank.
            EditText text = myActivity.findViewById(R.id.editText1);
            text.setText("");

            ArrayList <UnoCard> hand = (ArrayList<UnoCard>) firstInstance.getHandArray().get(playerNum);
            ArrayList <UnoCard> otherHand = (ArrayList<UnoCard>) firstInstance.getHandArray().get(playerNum + 1);

            //Adding information to the UI that the player needs to play the game.
            text.append("The Player's hand size is " + hand.size() + ".\n");
            text.append("CPU Player's hand size is " + otherHand.size() + ".\n");
            text.append("There are " + firstInstance.getPlayerNum() + " players in the game.\n");
            if(firstInstance.getColorInPlay() == -1)
            {
                switch (firstInstance.getSpcInPlay()){
                    case UnoSpecialCard.SKIP:
                        text.append("The current ability in play is Skip" + ".\n");
                        break;
                    case UnoSpecialCard.DRAWTWO:
                        text.append("The current ability in play is Draw 2" + ".\n");
                        break;
                    case UnoSpecialCard.REVERSE:
                        text.append("The current ability in play is Reverse" + ".\n");
                        break;
                    case UnoSpecialCard.WILD:
                        text.append("The current ability in play is Wild" + ".\n");
                        break;
                    case UnoSpecialCard.DRAWFOUR:
                        text.append("The current ability in play is Draw 4" + ".\n");
                        break;
                }
            }
            else {
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
            }
            text.append("The current number in play is " + firstInstance.getNumInPlay() + ".\n");
            text.append("The current player's turn is " + firstInstance.getPlayerID() + ".\n");

            for(int i = 0; i < hand.size() - 1; i++){
                String card = "-1";
                if(hand.get(i) instanceof  UnoNumberCard) {
                    card = "" + ((UnoNumberCard) hand.get(i)).getNum();
                }
                else if (hand.get(i) instanceof UnoSpecialCard){
                    card = ((UnoSpecialCard) hand.get(i)).getAbility() + " ";
                    if(((UnoSpecialCard) hand.get(i)).getAbility() == UnoSpecialCard.WILD){
                        card = "Wild, ";
                    }
                    if(((UnoSpecialCard) hand.get(i)).getAbility() == UnoSpecialCard.DRAWFOUR){
                        card = "Draw 4, ";
                    }
                }
                int switchy = hand.get(i).getColor();
                //Adds the color to the number to display the string on the UI.
                switch (switchy){
                    case 0:
                        card = "Red " + card + ", ";
                        break;
                    case 1:
                        card = "Green " + card + ", ";
                        break;
                    case 2:
                        card = "Blue " + card + ", ";
                        break;
                    case 3:
                        card = "Yellow " + card + ", ";
                        break;
                }
                text.append(card);

            }
        }
        else{
            flash(Color.RED, 500);
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

        ArrayList <UnoCard> hand = (ArrayList<UnoCard>) firstInstance.getHandArray().get(firstInstance.getPlayerID());

        //If the Draw Card button is clicked.
        // the local game automatically tells the player if it's a valid/invalid move
        if(v.equals(myActivity.findViewById(R.id.drawCard))){
            UnoDrawCardAction draw = new UnoDrawCardAction(this);
            game.sendAction(draw);
        }
        //If the Play Card button is clicked.
        else if(v.equals(myActivity.findViewById(R.id.playCard))){
            //The text box above the buttons is read to get the card to play.
            TextInputEditText cardInfo = myActivity.findViewById(R.id.cardToPlay);
            String cardInput = cardInfo.getText().toString().trim().toLowerCase();
            int cardNumber = -1;
            int cardAbility = -1;
            int cardColor = -1;
            for (int i = 0; i < cardInput.length(); i++) {
                if (cardInput.charAt(i) == ' ') {
                    switch (cardInput.substring(0, i)) {
                        case "red":
                            cardColor = UnoCard.RED;
                            break;
                        case "green":
                            cardColor = UnoCard.GREEN;
                            break;
                        case "blue":
                            cardColor = UnoCard.BLUE;
                            break;
                        case "yellow":
                            cardColor = UnoCard.YELLOW;
                            break;
                        case "wild":
                            cardAbility = UnoSpecialCard.WILD;
                            cardColor = UnoCard.COLORLESS;
                            break;
                        case "draw":
                            cardAbility = UnoSpecialCard.DRAWFOUR;
                            cardColor = UnoCard.COLORLESS;
                            break;
                        default:
                            cardInfo.setText("Error");
                    }
                    switch(cardInput.substring(i + 1)){
                        case "zero":
                        case "0":
                            cardNumber = 0;
                            break;
                        case "one":
                        case "1":
                            cardNumber = 1;
                            break;
                        case "two":
                        case "2":
                            cardNumber = 2;
                            break;
                        case "three":
                        case "3":
                            cardNumber = 3;
                            break;
                        case "four":
                        case "4":
                            cardNumber = 4;
                            break;
                        case "five":
                        case "5":
                            cardNumber = 5;
                            break;
                        case "six":
                        case "6":
                            cardNumber = 6;
                            break;
                        case "seven":
                        case "7":
                            cardNumber = 7;
                            break;
                        case "eight":
                        case "8":
                            cardNumber = 8;
                            break;
                        case "nine":
                        case "9":
                            cardNumber = 9;
                            break;
                        case "draw two":
                        case "draw 2":
                            cardAbility = UnoSpecialCard.DRAWTWO;
                            break;
                        case "skip":
                            cardAbility = UnoSpecialCard.SKIP;
                            break;
                        case "reverse":
                            cardAbility = UnoSpecialCard.REVERSE;
                            break;
                        default:
                            cardAbility = UnoSpecialCard.WILD;
                            break;

                    }
                }
            }
            for(int j = 0; j < hand.size() - 1; j++){
                if(hand.get(j) instanceof UnoNumberCard) {
                    if (((UnoNumberCard) hand.get(j)).getNum() == cardNumber && hand.get(j).getColor() == cardColor) {
                        UnoPlayCardAction play = new UnoPlayCardAction(this, j);
                        game.sendAction(play);
                        break;
                    }
                }
                else if(hand.get(j) instanceof UnoSpecialCard) {
                    if (((UnoSpecialCard) hand.get(j)).getAbility() == cardNumber && hand.get(j).getColor() == cardColor) {
                        UnoPlayCardAction play = new UnoPlayCardAction(this, j);
                        game.sendAction(play);
                        break;
                    }
                }
            }


        }
        //If the Uno Button is clicked.
        else if(v.equals(myActivity.findViewById(R.id.unoButton))){
           // if(game.canMove(firstInstance.getPlayerID())) {

           // }
            //This should do something.
        }

    }
}
