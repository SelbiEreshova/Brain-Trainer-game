package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GoPage extends AppCompatActivity
{
    public void onClickMyButton(View view){

        Intent intent = new Intent(GoPage.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_page);
    }
}