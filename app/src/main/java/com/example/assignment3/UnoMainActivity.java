package com.example.assignment3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import GameFramework.GameMainActivity;
import GameFramework.LocalGame;
import GameFramework.gameConfiguration.GameConfig;
import GameFramework.gameConfiguration.GamePlayerType;
import GameFramework.infoMessage.GameState;
import GameFramework.players.GamePlayer;
import GameFramework.utilities.Logger;

/**
 * @authors Starr Nakamitsu, Eduardo Gonon, Ayden Semerak.
 */

public class UnoMainActivity extends GameMainActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set Context for Toast Logging
        Logger.setContext(getApplicationContext());

        // Initialize the layout
        setContentView(R.layout.activity_main);

        // Sets the button's onClickListener to the MainActivity
        Button run = findViewById(R.id.run);
        run.setOnClickListener(this);

    }

    @Override
    public GameConfig createDefaultConfig() {
        // This array list holds different types of players
        ArrayList<GamePlayerType> players = new ArrayList<>();

        // This adds a human player type to the array list
        players.add(new GamePlayerType("Human Player") {
            public GamePlayer createPlayer(String name) {
                return new UnoHumanPlayer(name);
            }
        });

        // This adds a dumb AI player to the array list
        players.add(new GamePlayerType("Dumb AI Player") {

            public GamePlayer createPlayer(String name) {
                return new DumbAIPlayer(name);
            }

        });

        // This adds a smart AI player to the array list
        players.add(new GamePlayerType("Smart AI Player") {

            public GamePlayer createPlayer(String name) {
                return new SmartAIPlayer(name);
            }

        });


        GameConfig config = new GameConfig(players, 1, 3, "Uno", 1234);
        return config;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return new UnoLocalGame();
    }

    // %25 |Implement an onClick() listener method in your MainActivity that’s connected to the Run Test button. When the user clicks this button the
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
    //For each  the multi-line EditText. (e.g., “Player 1 has moved his
    //    //pawn from position 10 to position 14.” or “Player 3 has rolled the
    //    //dice. She rolled a 9.” New messages should be appended to previous
    //    //ones, not overwrite them.method call, a brief description of the action taken should
    //be printed to
    //2
    //– Create a new instance of the game state class using the default constructor. Assign this value to a variable named thirdInstance.
    //– Use your deep copy constructor to make a deep copyof thirdInstance
    //from the perspective of player one. Assign this copy to a variable
    //named fourthInstance.
    //– Call the toString() method on secondInstance and fourthInstance.
    //The two strings should be identical. Your code should verify this.
    //Also, print both strings to the multi-line EditText for visual inspection. Again, append these rather than overwrite previous messages.


    // Everything that will occur when the button is clicked
    public void onClick(View v){
        // This deletes any existing text
        EditText text = findViewById(R.id.editText1);
        text.setText("");

        // new instance of the UnoGameState class
        UnoGameState firstInstance = new UnoGameState();

        // deep copy of firstInstance
        UnoGameState secondInstance = new UnoGameState(firstInstance);

        // calling all of the methods in UnoGameState using firstInstance
        // dummy parameters are used when needed
        text.append("The Player's hand size is " + firstInstance.getHandSize() + ".\n");
        text.append("There are " + firstInstance.getPlayerNum() + " players in the game.\n");
        text.append("The current number in play is " + firstInstance.getNumInPlay() + ".\n");
        text.append("The current color in play is " + firstInstance.getColorInPlay() + ".\n");
        firstInstance.setNumInPlay(1, 1);
        firstInstance.setCurrentTurn();
        firstInstance.incremHandSize(3, true);

        //Last part of the checkpoint.
        UnoGameState thirdInstance = new UnoGameState();
        UnoGameState fourthInstance = new UnoGameState(thirdInstance);
        String secIns = secondInstance.toString();
        String fourIns = fourthInstance.toString();
        if(secIns.equals(fourIns)){
            text.append("Second Instance: " + secIns + "\n");
            text.append("Third Instance: " + fourIns + "\n");
        }
    }
}