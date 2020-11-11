package com.wintec.degreemap.ui.manager.manage_students;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
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
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.wintec.degreemap.R;
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.databinding.FragmentManageStudentFormBinding;
import com.wintec.degreemap.util.Helpers;
import com.wintec.degreemap.viewmodel.UserViewModel;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.wintec.degreemap.util.Constants.BUNDLE_USER_KEY;
import static com.wintec.degreemap.util.Constants.GENDER_DIVERSE;
import static com.wintec.degreemap.util.Constants.GENDER_FEMALE;
import static com.wintec.degreemap.util.Constants.GENDER_MALE;
import static com.wintec.degreemap.util.Constants.GENDER_NOT_SAY;
import static com.wintec.degreemap.util.Constants.PATHWAY_DATABASE_ARCHITECTURE;
import static com.wintec.degreemap.util.Constants.PATHWAY_NETWORK_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_SOFTWARE_ENGINEERING;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.REQUEST_PICK_IMAGE;
import static com.wintec.degreemap.util.Constants.REQUEST_STORAGE_PERMISSION;

public class ManageStudentFormFragment extends Fragment implements View.OnClickListener {
    private FragmentManageStudentFormBinding binding;
    private ImageView profileImage;
    private Uri profileImageUri;
    private View view;

    private TextInputLayout textInputId, textInputFirstName, textInputLastName, textInputEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage_student_form, container, false);
        view = binding.getRoot();

        profileImage = view.findViewById(R.id.details_avatar);
        profileImage.setOnClickListener(this);

        view.findViewById(R.id.btn_details_save).setOnClickListener(this);
        view.findViewById(R.id.btn_details_cancel).setOnClickListener(this);

        view.findViewById(R.id.radio_notSay).setOnClickListener(this);
        view.findViewById(R.id.radio_diverse).setOnClickListener(this);
        view.findViewById(R.id.radio_male).setOnClickListener(this);
        view.findViewById(R.id.radio_female).setOnClickListener(this);

        view.findViewById(R.id.radio_network).setOnClickListener(this);
        view.findViewById(R.id.radio_web).setOnClickListener(this);
        view.findViewById(R.id.radio_database).setOnClickListener(this);
        view.findViewById(R.id.radio_software).setOnClickListener(this);

        textInputId = view.findViewById(R.id.textInputId);
        textInputFirstName = view.findViewById(R.id.textInputFirstName);
        textInputLastName = view.findViewById(R.id.textInputLastName);
        textInputEmail = view.findViewById(R.id.textInputEmail);

        loadData();

        return view;
    }

    public void loadData() {
        String userKey = getArguments().getString(BUNDLE_USER_KEY, "");

        if (userKey.isEmpty()) {
            setEmptyUser();
        } else {
            final UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getUserDetails(userKey).observe(getViewLifecycleOwner(), new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null) {
                        if (!user.getProfileUrl().isEmpty()) {
                            Glide.with(view).load(user.getProfileUrl()).into(profileImage);
                        }

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
            case R.id.radio_network:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setPathway(PATHWAY_NETWORK_ENGINEERING);
                break;
            case R.id.radio_web:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setPathway(PATHWAY_WEB_DEVELOPMENT);
                break;
            case R.id.radio_database:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setPathway(PATHWAY_DATABASE_ARCHITECTURE);
                break;
            case R.id.radio_software:
                if (((RadioButton) view).isChecked())
                    binding.getUser().setPathway(PATHWAY_SOFTWARE_ENGINEERING);
                break;
            case R.id.details_avatar:
                openFileChooser();
                break;
            case R.id.btn_details_save:
                saveData();
                break;
            case R.id.btn_details_cancel:
                // Redirect to student list when creating new user, otherwise go to student details page
                if (getArguments().getString(BUNDLE_USER_KEY, "").isEmpty()) {
                    NavHostFragment.findNavController(this).navigate(R.id.action_manageStudentFormFragment_to_manageStudentListFragment);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(BUNDLE_USER_KEY, getArguments().getString(BUNDLE_USER_KEY));
                    NavHostFragment.findNavController(this).navigate(R.id.action_manageStudentFormFragment_to_manageStudentDetailsFragment, bundle);
                }
                break;
        }
    }

    // Choose image file for avatar
    private void openFileChooser() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ManageStudentFormFragment.this.requestPermissions(
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_STORAGE_PERMISSION);
        } else {
            Intent gallery = new Intent();
            gallery.setType("image/*");
            gallery.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(gallery, "Select Picture"), REQUEST_PICK_IMAGE);
        }
    }

    private boolean validateId() {
        if (binding.getUser().getKey().trim().isEmpty()) {
            textInputId.setError("Field can't be empty");
            return false;
        } else {
            textInputId.setError(null);
            return true;
        }
    }

    private boolean validateFirstName() {
        if (binding.getUser().getFirstName().trim().isEmpty()) {
            textInputFirstName.setError("Field can't be empty");
            return false;
        } else {
            textInputFirstName.setError(null);
            return true;
        }
    }

    private boolean validateLastName() {
        if (binding.getUser().getLastName().trim().isEmpty()) {
            textInputLastName.setError("Field can't be empty");
            return false;
        } else {
            textInputLastName.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String email = binding.getUser().getEmail().trim();
        if (email.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    public void saveData() {
        if (!validateId() || !validateFirstName() || !validateLastName() || !validateEmail())
            return;

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

        String currentUserKey = getArguments().getString(BUNDLE_USER_KEY, "");
        boolean isKeyUpdated = !currentUserKey.equals(binding.getUser().getKey());

        // If key has been updated, delete the previous record
        if (isKeyUpdated && !currentUserKey.isEmpty()) {
            userViewModel.deleteUser(currentUserKey);
        }

        userViewModel.saveUser(binding.getUser().getKey(),
                profileImageUri,
                Helpers.getFileExtension(getContext(), profileImageUri),
                user);

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_USER_KEY, binding.getUser().getKey());

        NavHostFragment.findNavController(this).navigate(R.id.action_manageStudentFormFragment_to_manageStudentDetailsFragment, bundle);
    }
}