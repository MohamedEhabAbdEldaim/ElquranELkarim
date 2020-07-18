package com.midooabdaim.elquranelkarim.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.midooabdaim.elquranelkarim.R;


public class CustomDailog extends Dialog {

    private Activity activity;
    private Button yes, no;
    private Button.OnClickListener listener;
    private Button.OnClickListener onClickListenerNo;


    public CustomDailog(Activity activity, Button.OnClickListener onClickListenerYes, Button.OnClickListener onClickListenerNo) {
        super(activity);
        this.activity = activity;
        this.listener = onClickListenerYes;
        this.onClickListenerNo = onClickListenerNo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dailog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        no.setOnClickListener(onClickListenerNo);
        yes.setOnClickListener(listener);


    }


}
