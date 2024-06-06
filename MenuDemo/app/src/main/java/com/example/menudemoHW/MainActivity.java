package com.example.menudemoHW;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;


import com.example.menudemoHW.CustomArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private ListView itemListView;
    private TextView mainCourseSelection, sideDishSelection, drinksSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.category_spinner);
        itemListView = findViewById(R.id.item_listview);
        mainCourseSelection = findViewById(R.id.main_course_selection);
        sideDishSelection = findViewById(R.id.side_dish_selection);
        drinksSelection = findViewById(R.id.drinks_selection);


//        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
//                R.array.category_array, android.R.layout.simple_spinner_item);
//        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, R.layout.spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);



        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int arrayResId;
                if (position == 0) {
                    arrayResId = R.array.main_course_array;
                } else if (position == 1) {
                    arrayResId = R.array.side_dish_array;
                } else if (position == 2) {
                    arrayResId = R.array.drinks_array;
                } else {
                    arrayResId = R.array.main_course_array;
                }

                String[] items = getResources().getStringArray(arrayResId);
                CustomArrayAdapter itemAdapter = new CustomArrayAdapter(MainActivity.this,
                        android.R.layout.simple_list_item_1, items);
                itemListView.setAdapter(itemAdapter);

                itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedItem = parent.getItemAtPosition(position).toString();
                        int categoryPosition = categorySpinner.getSelectedItemPosition();
                        if (categoryPosition == 0) {
                            mainCourseSelection.setText(selectedItem);
                        } else if (categoryPosition == 1) {
                            sideDishSelection.setText(selectedItem);
                        } else if (categoryPosition == 2) {
                            drinksSelection.setText(selectedItem);
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_submit) {

            String mainCourse = mainCourseSelection.getText().toString();
            String sideDish = sideDishSelection.getText().toString();
            String drinks = drinksSelection.getText().toString();


            Intent intent = new Intent(MainActivity.this, DisplaySelectionActivity.class);
            intent.putExtra("MAIN_COURSE", mainCourse);
            intent.putExtra("SIDE_DISH", sideDish);
            intent.putExtra("DRINKS", drinks);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.action_cancel) {

            mainCourseSelection.setText("請選擇");
            sideDishSelection.setText("請選擇");
            drinksSelection.setText("請選擇");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
