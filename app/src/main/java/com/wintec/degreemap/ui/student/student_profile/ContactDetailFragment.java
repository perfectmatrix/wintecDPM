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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.databinding.FragmentContactDetailBinding;
import com.wintec.degreemap.viewmodel.UserViewModel;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.wintec.degreemap.util.Constants.GENDER_DIVERSE;
import static com.wintec.degreemap.util.Constants.GENDER_FEMALE;
import static com.wintec.degreemap.util.Constants.GENDER_MALE;
import static com.wintec.degreemap.util.Constants.GENDER_NOT_SAY;
import static com.wintec.degreemap.util.Constants.KEY_USER_KEY;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public class ContactDetailFragment extends Fragment implements View.OnClickListener {
    private FragmentContactDetailBinding binding;

    private ImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SharedPreferences prefs;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_detail, container, false);
        view = binding.getRoot();

        prefs = getActivity().getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);

        profileImage = view.findViewById(R.id.details_avatar);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        Button saveBtn = view.findViewById(R.id.btn_details_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        // set gender onClickListener
        view.findViewById(R.id.radio_notSay).setOnClickListener(this);
        view.findViewById(R.id.radio_diverse).setOnClickListener(this);
        view.findViewById(R.id.radio_male).setOnClickListener(this);
        view.findViewById(R.id.radio_female).setOnClickListener(this);

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
                binding.getUser().getFirstName(),
                binding.getUser().getLastName(),
                binding.getUser().getPhone(),
                binding.getUser().getEmail(),
                binding.getUser().getAddress(),
                binding.getUser().getGender(),
                PATHWAY_WEB_DEVELOPMENT);

        String currentUserKey = prefs.getString(KEY_USER_KEY, "");
        boolean isKeyUpdated = !currentUserKey.equals(user.getKey());

        // If key has been updated, delete the previous record
        if (isKeyUpdated) {
            userViewModel.deleteUser(currentUserKey);
        }

        userViewModel.insertUser(binding.getUser().getKey(), user);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_USER_KEY, binding.getUser().getKey());
        editor.apply();

        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_contactDetailFragment_to_studentProfileFragment);
    }

    public void loadData() {
        String userKey = prefs.getString(KEY_USER_KEY, "");

        if (userKey.isEmpty()) {
            setEmptyUser();
        } else {
            UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getUserDetails(userKey).observe(getActivity(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null) {
                        binding.setUser(user);
                    } else {
                        setEmptyUser();
                    }
                }
            });
        }
    }

    private void setEmptyUser() {
        User user = new User("", "", "", "", "", "", "", "");
        binding.setUser(user);
    }

    @Override
    public void onClick(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_notSay:
                if (isChecked)
                    binding.getUser().setGender(GENDER_NOT_SAY);
                break;
            case R.id.radio_diverse:
                if (isChecked)
                    binding.getUser().setGender(GENDER_DIVERSE);
                break;
            case R.id.radio_female:
                if (isChecked)
                    binding.getUser().setGender(GENDER_MALE);
                break;
            case R.id.radio_male:
                if (isChecked)
                    binding.getUser().setGender(GENDER_FEMALE);
                break;
        }
    }
}