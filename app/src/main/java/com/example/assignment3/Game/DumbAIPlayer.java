package com.example.assignment3.Game;

import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameComputerPlayer;

import com.example.assignment3.Uno.UnoCard.UnoCard;
import com.example.assignment3.Uno.UnoCard.UnoDrawCardAction;
import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoCard.UnoPlayCardAction;
import com.example.assignment3.Uno.UnoCard.UnoSelectColorAction;
import com.example.assignment3.Uno.UnoCard.UnoSpecialCard;
import com.example.assignment3.Uno.UnoGameState;

import java.util.ArrayList;

/**
 * @author Ayden Semerak, Starr Nakamitsu
 */
public class DumbAIPlayer extends GameComputerPlayer {
    //The dumb AI lives, it works.

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public DumbAIPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        UnoGameState gameState;
        if (info instanceof UnoGameState) {
            gameState = (UnoGameState) info;
            if(gameState.getPlayerID() != this.playerNum) {
                return;
            }
        } else {
            return;
        }
        try {
            //Makes the game appear as though it is thinking.
            Thread.sleep(1500);
        } catch (Exception e) {

        }

        boolean hasPlayed = false;
        //Is turn may be redundant as currently it is always false.

        ArrayList<UnoCard> hand = gameState.getHandArray().get(this.playerNum);
        int colorInPlay = gameState.getColorInPlay();
        int numInPlay = gameState.getNumInPlay();
        UnoPlayCardAction play;

        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getColor() == colorInPlay) {
                play = new UnoPlayCardAction(this, i);
                game.sendAction(play);
                hasPlayed = true;
                break;
            }
            else if (hand.get(i) instanceof UnoNumberCard) {
                if (((UnoNumberCard) hand.get(i)).getNum() == numInPlay) {
                    play = new UnoPlayCardAction(this, i);
                    game.sendAction(play);
                    hasPlayed = true;
                    break;
                }
            }
            // if there's a wild or draw four, it will always choose red
            // *INCOMPLETE*
            else if (hand.get(i) instanceof UnoSpecialCard){
                if (hand.get(i).getColor() == UnoCard.COLORLESS){
                    play = new UnoPlayCardAction(this, i);
                    UnoSelectColorAction selectColor = new UnoSelectColorAction(this, UnoCard.YELLOW);
                    game.sendAction(play);
                    double random = Math.random();
                    if(random < 0.25){
                        selectColor = new UnoSelectColorAction(this, UnoCard.RED);
                    } else if (random < 0.5) {
                        selectColor = new UnoSelectColorAction(this, UnoCard.GREEN);
                    } else if (random < 0.75) {
                        selectColor = new UnoSelectColorAction(this, UnoCard.BLUE);
                    } else {
                        // its already initialized to yellow, send it
                    }
                    game.sendAction(selectColor);
                    hasPlayed = true;
                    System.out.println("playing wild");
                    break;
                }

            }
        }
        //If the Dumb AI has not played, then draw a card.
        if (!hasPlayed) {
            UnoDrawCardAction draw = new UnoDrawCardAction(this);
            game.sendAction(draw);
        }
    }
}