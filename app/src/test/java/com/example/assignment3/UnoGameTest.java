package com.example.assignment3;


import static org.junit.Assert.*;

import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoGameState;
import com.example.assignment3.Uno.UnoCard.UnoCard;
import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnoGameTest {
    @org.junit.Test
    public void drawCards() throws Exception{
        UnoGameState gameState = new UnoGameState();
        for(int i = 0; i < 93; i++)
            gameState.drawCard();
        ArrayList<UnoCard> hand = gameState.getHandArray().get(gameState.getPlayerID());
        int currHandSize = hand.size();
        assertEquals(100,currHandSize);
    }

    @org.junit.Test
    public void playCards() throws Exception{
        UnoGameState gameState = new UnoGameState();
        gameState.setCardInPlay(0,-1,UnoCard.RED);
        for(int y = 0; y < 6; y++){
            gameState.playCard(y);
        }
        ArrayList<UnoCard> hand = gameState.getHandArray().get(gameState.getPlayerID());
        int currHandSize = hand.size();
        assertTrue(currHandSize > 0);
    }

    //This does not always work, funny.
    @org.junit.Test
    public void handCheck() throws Exception{
        UnoGameState gameState = new UnoGameState();
        gameState.setCardInPlay(gameState.getPlayerID(),0,UnoCard.RED);
        ArrayList<UnoCard> hand = gameState.getHandArray().get(gameState.getPlayerID());
        gameState.drawCard();
        gameState.drawCard();
        for(int y = 0; y < 6; y++){
            if(hand.get(y) instanceof UnoNumberCard) {
                if(((UnoNumberCard) hand.get(y)).getNum() == gameState.getNumInPlay() || hand.get(y).getColor() == gameState.getColorInPlay()){
                    gameState.playCard(y);
                    assertEquals(8,hand.size());
                    break;
                }
            }
        }
        if(hand.size() == 9){
            gameState.setCardInPlay(9,0,hand.get(0).getColor());
            gameState.playCard(0);
            assertEquals(8
                    ,hand.size());
        }
    }
}