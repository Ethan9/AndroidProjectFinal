package com.example.ethan.androidprojectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewFestivalActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditFestivalView;
    private EditText mEditLong;
    private EditText mEditLat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_festival);
        mEditFestivalView = findViewById(R.id.edit_festival);
        mEditLong = findViewById(R.id.edit_long);
        mEditLat = findViewById(R.id.edit_lat);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditFestivalView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String festivalName = mEditFestivalView.getText().toString();
                    Double latitude = Double.parseDouble(mEditLat.getText().toString());
                    Double longitude = Double.parseDouble(mEditLong.getText().toString());
                    Festival festival = new Festival(festivalName, latitude, longitude);
                    replyIntent.putExtra(EXTRA_REPLY, festival);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
