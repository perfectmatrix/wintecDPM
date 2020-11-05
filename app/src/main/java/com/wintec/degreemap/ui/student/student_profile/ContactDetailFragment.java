package com.wintec.degreemap.ui.student.student_profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.databinding.FragmentContactDetailBinding;
import com.wintec.degreemap.viewmodel.UserViewModel;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.KEY_USER_KEY;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public class ContactDetailFragment extends Fragment {
    private FragmentContactDetailBinding binding;

    private ImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SharedPreferences mPrefs;

    private EditText firstName, lastName, id, phone, email;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_detail, container, false);
        View view = binding.getRoot();

        profileImage = view.findViewById(R.id.details_avatar);
        firstName = view.findViewById(R.id.details_first_name);
        lastName = view.findViewById(R.id.details_last_name);
        id = view.findViewById(R.id.details_id);
        phone = view.findViewById(R.id.details_phone);
        email = view.findViewById(R.id.details_email);

        btnSave = view.findViewById(R.id.btn_details_save);

        mPrefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        loadData();

        return view;
    }

    // Choose image file for avatar
    private void openFileChooser() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData() {
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        User user = new User(null,
                firstName.getText().toString(),
                lastName.getText().toString(),
                phone.getText().toString(),
                email.getText().toString(),
                "",
                PATHWAY_WEB_DEVELOPMENT);
        userViewModel.insertUser(id.getText().toString(), user);

        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_USER_KEY, id.getText().toString());
        editor.apply();

        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        String userKey = mPrefs.getString(KEY_USER_KEY, "");

        // TODO: If userKey is found then get data from firebase
        if (!userKey.isEmpty()) {
            id.setText(userKey);
        }
    }
}