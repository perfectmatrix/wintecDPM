package com.wintec.degreemap.ui.manager.manage_students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.ui.manager.manage_courses.ManagerCourseAdapter;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<User> userList;
    private View view;
    private OnItemClickListener listener;

    public void setUsers(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        StudentAdapter.StudentViewHolder evh = new StudentAdapter.StudentViewHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        User selectedUser = userList.get(position);

        Glide.with(view).load(selectedUser.getProfileUrl()).into(holder.profileImageView);
        holder.studentNameTextView.setText(selectedUser.getFirstName() + " " + selectedUser.getLastName());
        holder.studentIdTextView.setText(selectedUser.getKey());
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView profileImageView;
        public TextView studentNameTextView;
        public TextView studentIdTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.profileImageView);
            studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
            studentIdTextView = itemView.findViewById(R.id.studentIdTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
