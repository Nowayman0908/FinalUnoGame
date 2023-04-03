package com.example.assignment3.Uno;

// This class will test to make sure the cards in a player's hand do not become mixed up after
// a certain action is done

import com.example.assignment3.Game.DumbAIPlayer;
import java.util.ArrayList;

public class UnoUnitTest{

    // This is the method for the first test;
    // making sure a card's number and color doesn't get mixed up
    // will return true if the test passes and false if it fails
    public boolean test1(){
        UnoGameState unitTest1 = new UnoGameState();
        DumbAIPlayer testPlayer = new DumbAIPlayer("test player");

        // Preset cards given to the player
        UnoNumberCard redCard = new UnoNumberCard(UnoCard.RED, 3);
        UnoNumberCard greenCard = new UnoNumberCard(UnoCard.GREEN, 6);
        UnoNumberCard blueCard = new UnoNumberCard(UnoCard.BLUE, 0);
        UnoNumberCard yellowCard = new UnoNumberCard(UnoCard.YELLOW, 9);

        // The hand the player's hand must match
        ArrayList<UnoCard> initColorNumberHand = new ArrayList<>();
        initColorNumberHand.add(redCard);
        initColorNumberHand.add(greenCard);
        initColorNumberHand.add(blueCard);
        initColorNumberHand.add(yellowCard);

        // The player's hand
        ArrayList<UnoCard> colorNumberHand = new ArrayList<>();
        colorNumberHand.add(redCard);
        colorNumberHand.add(greenCard);
        colorNumberHand.add(blueCard);
        colorNumberHand.add(yellowCard);



        if (colorNumberHand != initColorNumberHand){
            return false;
        }

        return true;
    }

}
