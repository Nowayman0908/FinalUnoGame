package com.example.assignment3.Uno.UnoCard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ImageButton;

import com.example.assignment3.Game.UnoHumanPlayer;
import com.example.assignment3.R;
import com.example.assignment3.Uno.UnoMainActivity;

import GameFramework.utilities.MessageBox;

/**
 * @author Starr Nakamitsu.
 */
public class UnoColorPopUpWindow extends MessageBox implements DialogInterface.OnClickListener{

    // the gui player that the popup appears on
    private final UnoHumanPlayer player;
    public void displayPopUp(UnoMainActivity activity) {
        String[] items = new String[]{"Red", "Green", "Blue", "Yellow"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Choose Color:");
        builder.setItems(items, this);
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int chosen) {
        //Once the PopUp is shown to the player, which even button the player clicks will be relayed.
        if(chosen == UnoCard.RED) {
            System.out.println("Red chosen");
        }
        else if (chosen == UnoCard.GREEN) {
            System.out.println("Green chosen");
        }
        else if (chosen == UnoCard.BLUE) {
            System.out.println("Blue chosen");
        }
        else if (chosen == UnoCard.YELLOW) {
            System.out.println("Yellow chosen");
        }

        player.chooseColor(chosen);
    }

    public UnoColorPopUpWindow(UnoHumanPlayer player){
        // the game to send actions to
        this.player = player;
    }
}
