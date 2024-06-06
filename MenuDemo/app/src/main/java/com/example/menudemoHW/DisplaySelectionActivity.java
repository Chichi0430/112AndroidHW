package com.example.menudemoHW;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplaySelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_selection);

        TextView selectedMainCourse = findViewById(R.id.selected_main_course);
        TextView selectedSideDish = findViewById(R.id.selected_side_dish);
        TextView selectedDrinks = findViewById(R.id.selected_drinks);


        String mainCourse = getIntent().getStringExtra("MAIN_COURSE");
        String sideDish = getIntent().getStringExtra("SIDE_DISH");
        String drinks = getIntent().getStringExtra("DRINKS");

        selectedMainCourse.setText("主餐：" + mainCourse);
        selectedSideDish.setText("附餐：" + sideDish);
        selectedDrinks.setText("飲料：" + drinks);
    }
}
