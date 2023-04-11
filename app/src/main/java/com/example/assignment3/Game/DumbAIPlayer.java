package com.example.assignment3.Game;

import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameComputerPlayer;

import com.example.assignment3.Uno.UnoCard.UnoCard;
import com.example.assignment3.Uno.UnoCard.UnoDrawCardAction;
import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoCard.UnoPlayCardAction;
import com.example.assignment3.Uno.UnoGameState;

import java.util.ArrayList;

/**
 * @authoer Ayden Semerak
 */
public class DumbAIPlayer extends GameComputerPlayer {
    //The dumb AI lives, it works.

    private UnoGameState gameState;

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
        if (info instanceof UnoGameState) {
            gameState = (UnoGameState) info;
        } else {
            return;
        }
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }

        boolean hasPlayed = false;
        //Is turn may be redundant as currently it is always false.

        ArrayList<UnoCard> hand = gameState.getHandArray().get(this.playerNum);
        int colorInPlay = gameState.getColorInPlay();
        int numInPlay = gameState.getNumInPlay();
        for (int i = 0; i < gameState.getHandArray().get(this.playerNum).size(); i++) {
            UnoPlayCardAction play = new UnoPlayCardAction(this, i);
            if (hand.get(i).getColor() == colorInPlay) {
                game.sendAction(play);
                hasPlayed = true;
                break;
            }
            else if (hand.get(i) instanceof UnoNumberCard) {
                if (((UnoNumberCard) hand.get(i)).getNum() == numInPlay) {
                    game.sendAction(play);
                    hasPlayed = true;
                    break;
                }
            }
        }
        if (!hasPlayed) {
            UnoDrawCardAction draw = new UnoDrawCardAction(this);
            game.sendAction(draw);
        }
    }
}