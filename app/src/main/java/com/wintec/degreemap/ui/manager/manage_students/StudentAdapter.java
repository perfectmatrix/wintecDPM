package com.wintec.degreemap.ui.manager.manage_students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<User> userList;

    public void setUsers(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        StudentAdapter.StudentViewHolder evh = new StudentAdapter.StudentViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        User selectedUser = userList.get(position);
        holder.studentNameTextView.setText(selectedUser.getFirstName() + " " + selectedUser.getLastName());
        holder.studentIdTextView.setText(selectedUser.getKey());
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout studentCard;
        public TextView studentNameTextView;
        public TextView studentIdTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentCard = itemView.findViewById(R.id.studentCard);
            studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
            studentIdTextView = itemView.findViewById(R.id.studentIdTextView);
        }
    }
}
