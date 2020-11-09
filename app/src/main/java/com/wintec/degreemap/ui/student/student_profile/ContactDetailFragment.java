package com.wintec.degreemap.ui.student.student_profile;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.databinding.FragmentContactDetailBinding;
import com.wintec.degreemap.util.Helpers;
import com.wintec.degreemap.viewmodel.UserViewModel;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.GENDER_DIVERSE;
import static com.wintec.degreemap.util.Constants.GENDER_FEMALE;
import static com.wintec.degreemap.util.Constants.GENDER_MALE;
import static com.wintec.degreemap.util.Constants.GENDER_NOT_SAY;
import static com.wintec.degreemap.util.Constants.KEY_USER_KEY;
import static com.wintec.degreemap.util.Constants.REQUEST_PICK_IMAGE;
import static com.wintec.degreemap.util.Constants.REQUEST_STORAGE_PERMISSION;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public class ContactDetailFragment extends Fragment implements View.OnClickListener {
    private FragmentContactDetailBinding binding;

    private ImageView profileImage;
    private Uri profileImageUri;
    private SharedPreferences prefs;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_detail, container, false);
        view = binding.getRoot();

        prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

        profileImage = view.findViewById(R.id.details_avatar);
        profileImage.setOnClickListener(this);

        view.findViewById(R.id.btn_details_save).setOnClickListener(this);
        view.findViewById(R.id.radio_notSay).setOnClickListener(this);
        view.findViewById(R.id.radio_diverse).setOnClickListener(this);
        view.findViewById(R.id.radio_male).setOnClickListener(this);
        view.findViewById(R.id.radio_female).setOnClickListener(this);

        loadData();

        return view;
    }

    public void loadData() {
        String userKey = prefs.getString(KEY_USER_KEY, "");

        if (userKey.isEmpty()) {
            setEmptyUser();
        } else {
            final UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getUserDetails(userKey).observe(getViewLifecycleOwner(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null) {
                        Glide.with(view).load(user.getProfileUrl()).into(profileImage);
                        binding.setUser(user);
                    } else {
                        setEmptyUser();
                    }
                }
            });
        }
    }

    private void setEmptyUser() {
        User user = new User("", "", "", "", "", "", "", "", "");
        binding.setUser(user);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            profileImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), profileImageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_notSay:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setGender(GENDER_NOT_SAY);
                break;
            case R.id.radio_diverse:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setGender(GENDER_DIVERSE);
                break;
            case R.id.radio_male:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setGender(GENDER_MALE);
                break;
            case R.id.radio_female:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setGender(GENDER_FEMALE);
                break;
            case R.id.details_avatar:
                openFileChooser();
                break;
            case R.id.btn_details_save:
                saveData();
                break;
        }
    }

    // Choose image file for avatar
    private void openFileChooser() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ContactDetailFragment.this.requestPermissions(
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_STORAGE_PERMISSION);
        } else {
            Intent gallery = new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery, "Select Picture"), REQUEST_PICK_IMAGE);
        }
    }

    public void saveData() {
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        User user = new User(null,
                binding.getUser().getProfileUrl(),
                binding.getUser().getFirstName(),
                binding.getUser().getLastName(),
                binding.getUser().getPhone(),
                binding.getUser().getEmail(),
                binding.getUser().getAddress(),
                binding.getUser().getGender(),
                binding.getUser().getPathway());

        String currentUserKey = prefs.getString(KEY_USER_KEY, "");
        boolean isKeyUpdated = !currentUserKey.equals(binding.getUser().getKey());

        // If key has been updated, delete the previous record
        if (isKeyUpdated && !currentUserKey.isEmpty()) {
            userViewModel.deleteUser(currentUserKey);
        }

        userViewModel.insertUser(binding.getUser().getKey(),
                profileImageUri,
                Helpers.getFileExtension(getContext(), profileImageUri),
                user);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_USER_KEY, binding.getUser().getKey());
        editor.apply();

        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_contactDetailFragment_to_studentProfileFragment);
    }
}