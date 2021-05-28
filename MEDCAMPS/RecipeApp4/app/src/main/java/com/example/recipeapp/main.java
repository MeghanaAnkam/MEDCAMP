package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class main extends AppCompatActivity {
    private Button btn1;
    private TextView mTextViewShowUploads;
    private TextView mTextViewShowUpload;
    private Button show_upload3;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1 = (Button) findViewById(R.id.btn1);
        show_upload3 = findViewById(R.id.text_view_show_upload3);

        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mTextViewShowUpload = findViewById(R.id.text_view_show_upload);


        documentReference = db.collection("user").document("profile");

      show_upload3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(main.this, ProfileActivity.class);
              startActivity(intent);
          }
      });


        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUploadRecipe();

            }
        });

        mTextViewShowUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openrecycler();

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main.this, Upload_Recipe.class);
                startActivity(intent);
            }
        });

    }










    private void openrecycler() {
        Intent intent = new Intent(main.this, recycler.class);
        startActivity(intent);
    }

    private void openUploadRecipe() {
        Intent intent = new Intent(main.this, MainActivity.class);
        startActivity(intent);
    }


}




    