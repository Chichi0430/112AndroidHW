package com.example.a0315homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 預設的帳號和密碼
    private final String defaultAccount = "A109221258";
    private final String defaultPassword = "900430";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText accountEditText = findViewById(R.id.Textaccount);
        EditText passwordEditText = findViewById(R.id.Textpassword);
        Button enterButton = findViewById(R.id.enterbutton);
        TextView showTextView = findViewById(R.id.show);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputAccount = accountEditText.getText().toString();
                String inputPassword = passwordEditText.getText().toString();

                // 檢查是否輸入了帳號和密碼
                if (inputAccount.isEmpty()) {
                    showTextView.setText("請輸入帳號"); // 直接使用字符串，也可以使用字符串资源
                } else if (inputPassword.isEmpty()) {
                    showTextView.setText("請輸入密碼"); // 直接使用字符串，也可以使用字符串资源
                } else if (defaultAccount.equals(inputAccount) && defaultPassword.equals(inputPassword)) {
                    showTextView.setText("登入成功"); // 直接使用字符串，也可以使用字符串资源
                } else {
                    showTextView.setText("帳號或密碼有誤"); // 直接使用字符串，也可以使用字符串资源
                }
            }
        });
    }
}
