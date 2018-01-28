package com.mygdx.game.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018/1/27.
 */
public class ActivityForSwitch extends Activity {
    Button switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_switch_layout);
        switchButton = (Button)findViewById(R.id.switch_button);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityForSwitch.this, LibgdxActivity.class);
                startActivity(intent);
            }
        });
    }
}
