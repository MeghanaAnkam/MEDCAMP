package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.SmileRating;
import com.like.LikeButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;
    FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
ProgressDialog progressDialog;
MyAdapter myAdapter;
EditText txt_Search;
ImageButton maps;


 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);




        txt_Search = (EditText) findViewById(R.id.txt_searchtext);
        maps = (ImageButton) findViewById(R.id.maps);
//circleImageView = findViewById(R.id.circle);


progressDialog = new ProgressDialog(this);
progressDialog.setMessage("Loading items....");
myFoodList = new ArrayList<>();
        myAdapter = new MyAdapter(MainActivity.this,myFoodList);
   mRecyclerView.setAdapter(myAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");
progressDialog.show();






eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myFoodList.clear();

                for(DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    foodData.setKey(itemSnapshot.getKey());
                    myFoodList.add(foodData);



                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
progressDialog.dismiss();
            }
        });


        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());


            }
        });



    }



    private void filter(String text) {
        ArrayList<FoodData> filterList = new ArrayList<>();
        for (FoodData item : myFoodList) {

            if (item.getItemName().toLowerCase().contains(text.toLowerCase()))
            {

            {

                filterList.add(item);
            }
            }
        }
        myAdapter.filteredList(filterList);
    }
    public void btn_uploadActivity(View view) {
        startActivity(new Intent(this,Upload_Recipe.class));
    }

    public void onmaps(View view) {

        Intent intent = new Intent(MainActivity.this, Maps.class);
        startActivity(intent);
    }
}


