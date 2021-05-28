package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Share extends AppCompatActivity {
Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        share = findViewById(R.id.share);

    }


        public void btnclickact5(View view) {
            Intent sharingItent = new Intent(Intent.ACTION_SEND);
            sharingItent.setType("text/plain");
            sharingItent.putExtra(Intent.EXTRA_TEXT,"whatsapp");
            sharingItent.setPackage("com.whatsapp");
            startActivity(sharingItent);
        }


    }
