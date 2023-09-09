package com.example.apifirsitlct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apifirsitlct.databinding.CustomCommentBinding;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsHolder> {
    List<Comments> commentsList;
    Context context ;


    public CommentsAdapter(List<Comments> commentsList, Context context) {
        this.commentsList = commentsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCommentBinding binding =CustomCommentBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CommentsHolder(binding.getRoot()) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsHolder holder, int position) {
        Comments comments = commentsList.get(position);
        holder.binding.tvCommentName.setText(comments.getName());
        holder.binding.tvCommentEmail.setText(comments.getEmail());
        holder.binding.tvCommentBody.setText(comments.getBody());
        holder.binding.tvCommentID.setText(String.valueOf(comments.getId()));
        holder.binding.tvCommentPostId.setText(String.valueOf(comments.getPostId()));

    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    class  CommentsHolder extends RecyclerView.ViewHolder{
        CustomCommentBinding binding;
        public CommentsHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomCommentBinding.bind(itemView);
        }
    }
}
