package com.example.radiobuttonHW;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView tvDisplayInfo = findViewById(R.id.tvDisplayInfo);
        String ticketInfo = getIntent().getStringExtra("ticketInfo");
        if (ticketInfo != null && !ticketInfo.isEmpty()) {
            ticketInfo = formatTicketInfo(ticketInfo); // 格式化订票信息
        } else {
            ticketInfo = "沒有訂票訊息";
        }
        tvDisplayInfo.setText(ticketInfo);
    }

    private String formatTicketInfo(String info) {
        // 这里你可以添加更多的格式化逻辑
        return info.replaceAll(", ", "\n");
    }

}