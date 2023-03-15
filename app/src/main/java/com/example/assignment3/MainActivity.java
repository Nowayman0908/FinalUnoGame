package com.example.assignment3;

import GameFramework.GameMainActivity;
import GameFramework.LocalGame;
import GameFramework.gameConfiguration.GameConfig;
import GameFramework.infoMessage.GameState;

public class MainActivity extends GameMainActivity {


    @Override
    public GameConfig createDefaultConfig() {
        //Work in progress.
        //GameConfig config = new GameConfig(players, 1,3,"Uno", getConfig().getPortNum());
        return null;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }

    // %20 |Implement an onClick() listener method in your MainActivity that’s connected to the Run Test button. When the user clicks this button the
    //following things should occur in this order:
    //– Any text currently displayed in in the multi-line EditText (probably
    //from a previous test run) should be cleared.
    //– A new instance of the game state class is created using the default
    //constructor and assigned to a variable named firstInstance.
    //– Use your other constructor to create a deep copy of firstInstance from
    //the perspective of player one. Assign this copy to a variable named
    //secondInstance.
    //– Using firstInstance, call each method in the game state class at least
    //once. In each case it should be making a legal move in the game.
    //For each method call, a brief description of the action taken should
    //be printed to the multi-line EditText. (e.g., “Player 1 has moved his
    //pawn from position 10 to position 14.” or “Player 3 has rolled the
    //dice. She rolled a 9.” New messages should be appended to previous
    //ones, not overwrite them.
    //2
    //– Create a new instance of the game state class using the default constructor. Assign this value to a variable named thirdInstance.
    //– Use your deep copy constructor to make a deep copyof thirdInstance
    //from the perspective of player one. Assign this copy to a variable
    //named fourthInstance.
    //– Call the toString() method on secondInstance and fourthInstance.
    //The two strings should be identical. Your code should verify this.
    //Also, print both strings to the multi-line EditText for visual inspection. Again, append these rather than overwrite previous messages.
}