package com.example.assignment3.Uno.UnoMVC;
import android.view.View;

public class UnoController implements View.OnClickListener{

    private UnoView UV;
    private UnoModel UM;
    public UnoController (UnoView initView){
        UV = initView;
        UM = UV.getUM();
    }
    @Override
    public void onClick(View view) {

    }
}
