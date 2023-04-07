package com.example.assignment3.Uno;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class UnoView extends SurfaceView {

    private Paint black = new Paint(Color.BLACK);
    private Paint red = new Paint(Color.RED);
    private Paint blue = new Paint(Color.BLUE);
    private Paint yellow = new Paint(Color.YELLOW);
    private Paint green = new Paint(Color.GREEN);

    public UnoView(Context context) {

        super(context);

    }

    public void onDraw (Canvas canvas){
        //Fill in later.
    }

    public void drawCard (Canvas canvas,int x,int y, int colorChoice, int cardNumber){

        canvas.drawRect(x,y,x+50,y+50,black);
        switch(colorChoice){
            case 0:
                canvas.drawRect(x+ 10,y + 10,x+40,y+40,red);
                break;
            case 1:
                canvas.drawRect(x+10,y+10,x+40,y+40,green);
                break;
            case 2:
                canvas.drawRect(x+10,y+10,x+40,y+40,blue);
                break;
            case 3:
                canvas.drawRect(x+10,y+10,x+40,y+40,yellow);
                break;
        }
    }

    public void drawPlayerCards(Canvas canvas, int playerNum){
        switch (playerNum){
            case 2:
                //canvas.drawRect();
                //canvas.drawText();
                break;
            case 3:
                //canvas.drawRect();
                //canvas.drawText();
                break;
            case 4:
                //canvas.drawRect();
                //canvas.drawText();
                break;
            default:
                //canvas.drawRect();
                //canvas.drawText();
                break;

        }
    }
}
