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
        } else {
            return;
        }
        try {
            //Makes the game appear as though it is thinking.
            Thread.sleep(750);
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
        }

        if (!hasPlayed) {
            UnoDrawCardAction draw = new UnoDrawCardAction(this);
            game.sendAction(draw);
        }
    }
}