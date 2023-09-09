package com.example.apifirsitlct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apifirsitlct.databinding.CustomPostBinding;
import com.example.apifirsitlct.databinding.CustomUserBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    List <User>  usersList;
    Context context;
    Clicks click;


    public UserAdapter(List<User> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;
    }

    public Clicks getClick() {
        return click;
    }

    public void setClick(Clicks click) {
        this.click = click;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomUserBinding binding = CustomUserBinding.inflate(LayoutInflater.from(context),parent,false);
        return new UserHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = usersList.get(position);
        holder.binding.tvName.setText(user.getName());
        holder.binding.tvCity.setText(user.getAddress().getCity());
        holder.binding.tvStreet.setText(user.getAddress().getStreet());
        holder.binding.tvSuite.setText(user.getAddress().getSuite());
        holder.binding.tvZip.setText(user.getAddress().getZipcode());
        holder.binding.tvComName.setText(user.getCompany().getName());
        holder.binding.cvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.onClicks(user.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{
        CustomUserBinding binding;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomUserBinding.bind(itemView);
        }
    }
}
