package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;
import java.util.Objects;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;
    //Pass in the context and list of tweets


    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the data at position
        Tweet tweet = tweets.get(position);
        //Bind the tweet with the view holder
        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        tweets.addAll(list);
        notifyDataSetChanged();
    }


    //Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreeName;
        ImageView ivComment;
        ImageView ivRetweet;
        ImageView ivLike;
        ImageView ivPhoto;
        TextView tvCreatedAt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreeName = itemView.findViewById(R.id.tvScreenName);
            ivComment = itemView.findViewById(R.id.ivComment);
            ivRetweet = itemView.findViewById(R.id.ivRetweet);
            ivLike = itemView.findViewById(R.id.ivLike);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);

        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreeName.setText("@" + tweet.user.screenName);
            ivComment.setImageResource(R.drawable.ic_vector_compose_dm);
            ivRetweet.setImageResource(R.drawable.ic_vector_retweet);
            ivLike.setImageResource(R.drawable.ic_vector_heart_stroke);
            tvCreatedAt.setText(tweet.getRelativeTimeAgo(tweet.createAt));

            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
            if (!Objects.equals(tweet.mediaUrl,"")){
                Glide.with(context).load(tweet.mediaUrl).into(ivPhoto);
            } else{
               ivPhoto.setVisibility(View.GONE);
            }
        }
    }



}
