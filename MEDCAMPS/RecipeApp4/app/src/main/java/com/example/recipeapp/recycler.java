package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class recycler extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;
    FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;
    MyAdapter myAdapter;
  //  EditText txt_Search;
   // ImageButton maps;
    //   private SmileRating mRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);





            //  bt=(Button)findViewById(R.id.button2);
            //bt.setOnClickListener(new View.OnClickListener() {
            //  @Override
            //public void onClick(View view) {
            //  Intent myIntent = new Intent(Intent.ACTION_SEND);
            //myIntent.setType("text/plain");
            //String shareBody = "Your body is here";
            //  String shareSub = "Your Subject here";
            //myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            //              myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            //            startActivity(Intent.createChooser(myIntent, "Share using"));
            //      }
            //});


            //  SmileRating smileRating = (SmileRating) findViewById(R.id.ratingBar);

            //    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            //   DatabaseReference databaseReference = rootRef.child("ratings");
            //    final DatabaseReference finalDatabaseReference = databaseReference;
            //   smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
            //     @Override
            //   public void onRatingSelected(int level, boolean reselected) {
            //     Toast.makeText(getApplicationContext(), "Rating Successfully done:Rating " + level,
            //           Toast.LENGTH_SHORT).show();
            // finalDatabaseReference.child("rating").setValue(String.valueOf(level));

            // reselected is false when user selects different smiley that previously selected one
            // true when the same smiley is selected.
            // Except if it first time, then the value will be false.
            //   }
            // });

            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);


            GridLayoutManager gridLayoutManager = new GridLayoutManager(recycler.this, 1);
            mRecyclerView.setLayoutManager(gridLayoutManager);




       //     txt_Search = (EditText) findViewById(R.id.txt_searchtext);
         //   maps = (ImageButton) findViewById(R.id.maps);
            //  share = (ImageButton) findViewById(R.id.share);


            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading posts....");



            myFoodList = new ArrayList<>();




            myAdapter = new MyAdapter(recycler.this,myFoodList);
            mRecyclerView.setAdapter(myAdapter);
            databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");
            progressDialog.show();

/*btnlike.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, Rating1.class);
        startActivity(intent);
    }
});
*/

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


        //    txt_Search.addTextChangedListener(new TextWatcher() {
          //      @Override
            //    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
  //              }

    //            @Override
      //          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
  //              }

    //            @Override
      //          public void afterTextChanged(Editable editable) {
        //            filter(editable.toString());


          //      }
            //});



        }



   //    private void filter(String text) {
     //   ArrayList<FoodData> filterList = new ArrayList<>();
       //     for (FoodData item : myFoodList) {
//
  //              if (item.getItemName().toLowerCase().contains(text.toLowerCase()))
    //            {
//
  //                  {
//
  //                      filterList.add(item);
    //                }
      //          }
        //    }
          //  myAdapter.filteredList(filterList);
//
  //      }
//
     //   public void btn_uploadActivity(View view) {
       //     startActivity(new Intent(this,Upload_Recipe.class));
  //      }
//
  //      public void onmaps(View view) {
//
  //          Intent intent = new Intent(recycler.this, Maps.class);
    //       startActivity(intent);
      //  }


    }



