package com.example.assignment3;


import static org.junit.Assert.*;

import com.example.assignment3.Uno.UnoCard.UnoNumberCard;
import com.example.assignment3.Uno.UnoCard.UnoSpecialCard;
import com.example.assignment3.Uno.UnoGameState;
import com.example.assignment3.Uno.UnoCard.UnoCard;
import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnoGameTest {
    //Ensure the card's color and number always match and do not become jumbled.
    @org.junit.Test
    public void jumbleTest() throws Exception {
        UnoGameState gameState = new UnoGameState();
        for (int i = 0; i < 93; i++)
            gameState.drawCard();
        ArrayList<UnoCard> hand = gameState.getHandArray().get(gameState.getPlayerID());
        int currHandSize = hand.size();
        assertEquals(100, currHandSize);
    }

    //Test the AI after every commit to ensure it doesn't end up in an endless loop or an unintended action.
    @org.junit.Test
    public void aiTest() throws Exception {
        UnoGameState gameState = new UnoGameState();
        gameState.setCardInPlay(0, -1, UnoCard.RED);
        for (int y = 0; y < 6; y++) {
            gameState.playCard(y);
        }
        ArrayList<UnoCard> hand = gameState.getHandArray().get(gameState.getPlayerID());
        int currHandSize = hand.size();
        assertTrue(currHandSize > 0);
    }

//Test special card functions work properly.
    @org.junit.Test
    public void specialCardFunction() throws Exception {
        UnoGameState gameState = new UnoGameState();
        UnoSpecialCard wild = new UnoSpecialCard(0,UnoSpecialCard.WILD);
        gameState.setCardInPlay(0, -1, UnoCard.RED);
        gameState.setPlayerHand(gameState.getPlayerID());
        gameState.playCard(3);
        gameState.playCard(4);
        gameState.playCard(0);
        assertEquals(2, gameState.getHandArray().get(gameState.getPlayerID()).size());
    }
}