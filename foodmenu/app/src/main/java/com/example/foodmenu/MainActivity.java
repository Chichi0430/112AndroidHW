

package com.example.foodmenu;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
        implements CompoundButton.OnCheckedChangeListener {

    // 宣告CheckBox
    private CheckBox chkBurger, chkFrenchfry, chkSoftdrink, chkSoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 設定OnCheckedChangeListener
        int[] chkIDs = {R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4};
        for (int id : chkIDs) {
            CheckBox checkBox = (CheckBox) findViewById(id);
            checkBox.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton chkBox, boolean isChecked) {

        // 設定ImageView
        ImageView output1 = (ImageView) findViewById(R.id.output1);
        ImageView output2 = (ImageView) findViewById(R.id.output2);
        ImageView output3 = (ImageView) findViewById(R.id.output3);
        ImageView output4 = (ImageView) findViewById(R.id.output4);

        // 設定TextView
        TextView show = (TextView) findViewById(R.id.showOrder);

        // 判斷CheckBox是否被選中
        if (chkBox.getId() == R.id.chk1) {

            // 選中圖片的Visibility設為"VISIBLE"，未選中則設為"GONE"
            output1.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);

            // 顯示文字"你點的餐點如下"
            show.setText("你點的餐點如下");

        } else if (chkBox.getId() == R.id.chk2) {
            output2.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
            show.setText("你點的餐點如下");
        } else if (chkBox.getId() == R.id.chk3) {
            output3.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
            show.setText("你點的餐點如下");
        } else if (chkBox.getId() == R.id.chk4) {
            output4.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
            show.setText("你點的餐點如下");
        }

        // 全部都沒選到時，顯示文字"請點餐"
        if (output1.getVisibility() == View.GONE &&
                output2.getVisibility() == View.GONE &&
                output3.getVisibility() == View.GONE &&
                output4.getVisibility() == View.GONE) {
            show.setText("請點餐");
        }
    }
}