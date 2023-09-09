package com.example.apifirsitlct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apifirsitlct.databinding.CustomPostBinding;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsHolder> {
    List<Posts> PostsList;
    Context context;
    CommentClick commentClick;

    public CommentClick getCommentClick() {
        return commentClick;
    }

    public void setCommentClick(CommentClick commentClick) {
        this.commentClick = commentClick;
    }

    public PostsAdapter(List<Posts> postsList, Context context) {
        PostsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomPostBinding binding = CustomPostBinding.inflate(
                LayoutInflater.from(context),parent,false);
        return new PostsHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PostsHolder holder, int position) {
        Posts post = PostsList.get(position);
        holder.binding.tvTitle.setText(post.getTitle());
        holder.binding.tvBody.setText(post.getBody());
        holder.binding.cvPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commentClick.ComClick(post.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return PostsList.size();
    }

    class PostsHolder extends RecyclerView.ViewHolder{
        CustomPostBinding binding;
        public PostsHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomPostBinding.bind(itemView);
        }
    }
}
