package com.example.assignment3.Game;

import GameFramework.infoMessage.GameInfo;
import GameFramework.players.GameComputerPlayer;

import com.example.assignment3.Uno.UnoDrawCardAction;
import com.example.assignment3.Uno.UnoGameState;
import com.example.assignment3.Uno.UnoLocalGame;
import com.example.assignment3.Uno.UnoNumberCard;
import com.example.assignment3.Uno.UnoPlayCardAction;
import com.example.assignment3.Uno.UnoCard;
import java.util.Random;
import java.util.ArrayList;

public class DumbAIPlayer extends GameComputerPlayer {

    private Random ran = new Random();

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
        UnoGameState gameState = (UnoGameState) info;
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }
        if (gameState.isTurn()) {

            int colorInPlay = gameState.getColorInPlay();
            int numInPlay = gameState.getNumInPlay();
            ArrayList <UnoCard> hand = (ArrayList<UnoCard>) gameState.getHandArray().get(gameState.getPlayerID());
            if (gameState.getColorsInHand().contains(colorInPlay)) {

                UnoPlayCardAction action = new UnoPlayCardAction(this);
                UnoDrawCardAction draw = new UnoDrawCardAction(this);

                if (gameState.getColorsInHand().contains(colorInPlay)) {
                    //A bit messy but this gets the AI's hand and finds the last card that is of a color and plays it.
                   UnoNumberCard play = (UnoNumberCard) hand.get(hand.lastIndexOf(colorInPlay));
                    gameState.setCardInPlay(gameState.getPlayerID(),play.getNum(),play.getColor());
                    hand.remove(hand.lastIndexOf(colorInPlay));
                    game.sendAction(action);
                } else if (gameState.getNumInHandCards().contains(numInPlay)) {
                    //A bit messy but this gets the AI's hand and finds the last card that is of a number and plays it.
                    UnoNumberCard play = (UnoNumberCard) hand.get(hand.lastIndexOf(numInPlay));
                    gameState.setCardInPlay(gameState.getPlayerID(),play.getNum(),play.getColor());
                    hand.remove(hand.lastIndexOf(numInPlay));
                    game.sendAction(action);
                } else {
                    gameState.incremHandSize(gameState.getPlayerNum(), true);
                    game.sendAction(draw);
                }
                gameState.setCurrentTurn(false);
            }
        }
    }
}