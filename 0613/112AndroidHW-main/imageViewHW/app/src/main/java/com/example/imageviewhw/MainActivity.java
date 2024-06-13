package com.example.imageviewhw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取 CheckBox、ImageView 和 TextView 的引用
        CheckBox chk1 = findViewById(R.id.chk1);
        ImageView output1 = findViewById(R.id.output1);
        CheckBox chk2 = findViewById(R.id.chk2);
        ImageView output2 = findViewById(R.id.output2);
        CheckBox chk3 = findViewById(R.id.chk3);
        ImageView output3 = findViewById(R.id.output3);
        CheckBox chk4 = findViewById(R.id.chk4);
        ImageView output4 = findViewById(R.id.output4);
        CheckBox chk5 = findViewById(R.id.chk5);
        ImageView output5 = findViewById(R.id.output5);
        TextView showOrder = findViewById(R.id.showOrder);

        // 定义一个方法，用于更新 TextView 显示的订单详情
        final Runnable updateOrderDetails = () -> {
            StringBuilder orderDetails = new StringBuilder();
            if (chk1.isChecked() || chk2.isChecked() || chk3.isChecked() || chk4.isChecked() || chk5.isChecked()) {
                showOrder.setText("您的餐點如下：");
                if (chk1.isChecked()) {
                    orderDetails.append(chk1.getText().toString()).append(", ");
                }
                if (chk2.isChecked()) {
                    orderDetails.append(chk2.getText().toString()).append(", ");
                }
                if (chk3.isChecked()) {
                    orderDetails.append(chk3.getText().toString()).append(", ");
                }
                if (chk4.isChecked()) {
                    orderDetails.append(chk4.getText().toString()).append(", ");
                }
                if (chk5.isChecked()) {
                    orderDetails.append(chk5.getText().toString()).append(", ");
                }
                // 移除最后的逗号和空格
                showOrder.append(orderDetails.substring(0, orderDetails.length() - 2));
            } else {
                showOrder.setText("請點餐");
            }
        };

        // 为每个 CheckBox 设置点击监听器
        chk1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            output1.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateOrderDetails.run();
        });
        chk2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            output2.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateOrderDetails.run();
        });
        chk3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            output3.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateOrderDetails.run();
        });
        chk4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            output4.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateOrderDetails.run();
        });
        chk5.setOnCheckedChangeListener((buttonView, isChecked) -> {
            output5.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            updateOrderDetails.run();
        });
    }
}
