package com.example.assignment3.Uno;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.assignment3.Game.DumbAIPlayer;
import com.example.assignment3.R;
import com.example.assignment3.Game.SmartAIPlayer;
import com.google.android.material.textfield.TextInputEditText;

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
        config.addPlayer("Human", 0);
        config.addPlayer("Computer", 1);
        return config;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        UnoLocalGame newGame = new UnoLocalGame();
        return newGame;
    }

}