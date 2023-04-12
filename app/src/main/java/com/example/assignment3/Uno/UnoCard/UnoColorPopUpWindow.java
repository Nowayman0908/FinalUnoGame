package com.example.assignment3.Uno.UnoCard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.assignment3.Uno.UnoMainActivity;

import GameFramework.utilities.MessageBox;

public class UnoColorPopUpWindow extends MessageBox {

    public static void displayPopUp(UnoMainActivity activity) {
        String[] items = new String[]{"Red", "Green", "Blue", "Yellow"};

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Choose Color:");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int chosen) {
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
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
