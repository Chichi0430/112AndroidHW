package com.example.menudemoHW;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private int resource;

    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item);
        textView.setTextSize(19); // 调整字体大小
        return convertView;
    }
}
