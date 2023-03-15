package com.example.assignment3;

import java.util.ArrayList;

import GameFramework.Game;
import GameFramework.GameMainActivity;
import GameFramework.LocalGame;
import GameFramework.gameConfiguration.GameConfig;
import GameFramework.gameConfiguration.GamePlayerType;
import GameFramework.infoMessage.GameState;
import GameFramework.players.GamePlayer;
import android.view.View.OnClickListener;

public class UnoMainActivity extends GameMainActivity {


    @Override
    public GameConfig createDefaultConfig() {
        //Work in progress
        // This array list holds different types of players
        ArrayList<GamePlayerType> players = new ArrayList<>();

        // This adds a human player type to the array list
        players.add(new GamePlayerType("Human Player"){
            public GamePlayer createPlayer(String name){
                return new UnoHumanPlayer(name);
            }
        });

        // This adds a dumb AI player to the array list
        players.add(new GamePlayerType("Dumb AI Player"){

            public GamePlayer createPlayer(String name){
                return new DumbAIPlayer(name);
            }

        });

        players.add(new GamePlayerType("Smart AI Player"){

            public GamePlayer createPlayer(String name){
                return new SmartAIPlayer(name);
            }

        });

        GameConfig config = new GameConfig(players, 1,3,"Uno", 1234);
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