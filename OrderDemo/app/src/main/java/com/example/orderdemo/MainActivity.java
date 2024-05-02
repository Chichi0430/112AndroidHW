package com.example.orderdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private  CheckBox chk1,chk2,chk3,chk4;
    private int[] chkIDs = {R.id.chk1, R.id.chk2,R.id.chk3,R.id.chk4};
    private TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        chk1 = (CheckBox) findViewById(R.id.chk1);
        chk2 = (CheckBox) findViewById(R.id.chk2);
        chk3 = (CheckBox) findViewById(R.id.chk3);
        chk4 = (CheckBox) findViewById(R.id.chk4);

        for ( int id : chkIDs){
            CheckBox chk = (CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }
        output = (TextView) findViewById(R.id.showOrder);
    }
        @Override
        public void onClick(View v) {
            String str = "";
            if (chk1.isChecked())
                str += chk1.getText() + "\n";
            if (chk2.isChecked())
                str += chk2.getText() + "\n";
            if (chk3.isChecked())
                str += chk3.getText() + "\n";
            if (chk4
                    .isChecked())
                str += chk4.getText() + "\n";

            TextView output = (TextView) findViewById(R.id.showOrder);
            output.setText(str);
        }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.chk1 && isChecked) {
            // 显示汉堡图像
            ImageView output1 = findViewById(R.id.output1);
            output1.setImageResource(R.mipmap.burger);

            // 更新 TextView showOrder 的文本为"漢堡"
            TextView showOrder = findViewById(R.id.showOrder);
            showOrder.setText("漢堡");
        }
    }
}