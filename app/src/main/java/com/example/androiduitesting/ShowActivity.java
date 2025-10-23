package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        TextView cityNameText = findViewById(R.id.text_view_city);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String cityName = bundle.getString("city_name");
            cityNameText.setText(cityName);
        }

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchBack = new Intent(ShowActivity.this, MainActivity.class);
                startActivity(switchBack);
            }
        });
    }
}
