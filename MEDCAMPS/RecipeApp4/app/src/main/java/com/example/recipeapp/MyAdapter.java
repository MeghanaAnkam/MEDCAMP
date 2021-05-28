package com.example.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hsalf.smilerating.SmileRating;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private Context mContext;
    private List<FoodData> myFoodList;
   private SmileRating mRatingBar;
    private DatabaseReference mRatingBarCh;





    public MyAdapter(Context mContext, List<FoodData> myFoodList) {
        this.mContext = mContext;
        this.myFoodList = myFoodList;
    }





    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_item,viewGroup,false);



        return new FoodViewHolder(mView);

    }
    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, int i) {
        Glide.with(mContext)
                .load(myFoodList.get(i).getItemImage())
                .into(foodViewHolder.imageView);


       // foodViewHolder.imageView.setImageResource(myFoodList.get(i).getItemImage());
        foodViewHolder.mTitle.setText(myFoodList.get(i).getItemName());
        foodViewHolder.mDescription.setText(myFoodList.get(i).getItemDescription());
        foodViewHolder.mPrice.setText(myFoodList.get(i).getItemPrice());




        foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("Image",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemDescription());
                intent.putExtra("keyValue",myFoodList.get(foodViewHolder.getAdapterPosition()).getKey());


                mContext.startActivity(intent);



            }
        });

        }




    @Override
    public int getItemCount() { return myFoodList.size();
    }

    public void filteredList(ArrayList<FoodData> filterList) {


myFoodList = filterList;
notifyDataSetChanged();
    }

        public void onRatingSelected(int level, boolean reselected) {
            Toast.makeText(mContext.getApplicationContext(), "Rating Successfully done:Rating " + level,
                    Toast.LENGTH_SHORT).show();
            mRatingBarCh.child("rating").setValue(String.valueOf(level));

            // reselected is false when user selects different smiley that previously selected one
            // true when the same smiley is selected.
            // Except if it first time, then the value will be false.
        }
    }





class FoodViewHolder extends RecyclerView.ViewHolder {

     ImageView imageView;
     TextView mTitle;
     TextView mDescription;
     TextView mPrice;
     CardView mCardView;

    private SmileRating mRatingBar;
    private DatabaseReference mRatingBarCh;
    public FoodViewHolder(View itemView) {
        super(itemView);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference mRatingBarCh = rootRef.child("ratings");
      //  SmileRating smileRating = itemView.findViewById(R.id.ratingBar);

        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDescription);
        mPrice = itemView.findViewById(R.id.tvPrice);
        mCardView = itemView.findViewById(R.id.myCardView);

        }
        }