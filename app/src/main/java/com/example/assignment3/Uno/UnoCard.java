package com.example.assignment3.Uno;

public class UnoCard {

    // These colors are constants that can be referenced anywhere
    public static final int RED = 0;
    public static final int GREEN = 1;
    public static final int BLUE = 2;
    public static final int YELLOW = 3;
    public static final int COLORLESS = 4;

    // instance variable
    protected int color;

    // constructor
    public UnoCard (int initColor){
        this.color = initColor;
    }

    // getter method: this method gets the color of the card
    public int getColor(){
        return this.color;
    }

    // setter method: this method sets a color to a colorless (wild) card
    public void setColor(int assignedColor){
        if (this.color == 4){
            color = assignedColor;
        }
    }
}
