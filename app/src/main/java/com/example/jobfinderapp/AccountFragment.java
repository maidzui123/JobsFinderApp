package com.example.jobfinderapp;

import android.annotation.SuppressLint;
    import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }
    private TextView tvProfileUsername, tvSendFeedback, tvProfileMajors;
    private static TextView tvProgressString;
    private View mView;
    private ImageButton ibAccountPopUp;
    private Button btnUploadPhoto, btnRateYourStar, btnProfileDetail;
    private ImageView ivProfileAvatar;
    private CustomProgressDialog customProgressDialog;
    private MainActivity mainActivity;
    ActivityResultLauncher<String> mTakePhoto;
    float myRating = 0;
    private static Uri imageUri;
    private ProgressBar pbProfileProgress;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;
    private StorageReference storageRef;
    Map<String, Object> map = new HashMap<>();
    static int progressScore;


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account, container, false);

        tvProfileUsername = mView.findViewById(R.id.tvProfileUsername);
        tvProfileMajors = mView.findViewById(R.id.tvProfileMajors);
        ibAccountPopUp = mView.findViewById(R.id.ibAccountPopUp);
        btnUploadPhoto = mView.findViewById(R.id.btnUploadPhoto);
        ivProfileAvatar = mView.findViewById(R.id.ivProfileAvatar);
        tvSendFeedback = mView.findViewById(R.id.tvSendFeedback);
        btnRateYourStar = mView.findViewById(R.id.btnRateYourStar);
        btnProfileDetail = mView.findViewById(R.id.btnProfileDetail);
        pbProfileProgress = mView.findViewById(R.id.pbProfileProgress);
        tvProgressString = mView.findViewById(R.id.tvProgressString);

        mainActivity = (MainActivity) getActivity();
        if(mainActivity.getUsername() == "" || mainActivity.getMajors() == "")
        {
            tvProfileUsername.setText("*Unknown*");
            tvProfileMajors.setText("*Unknown*");
        }
        else {
            tvProfileUsername.setText(mainActivity.getUsername());
            tvProfileMajors.setText(mainActivity.getMajors());
        }
        pbProfileProgress.setProgress(progressScore);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        ivProfileAvatar.setImageURI(imageUri);

        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageUri = result;
                uploadAvatar();
            }
        });

        ibAccountPopUp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), ibAccountPopUp);
                popupMenu.getMenuInflater().inflate(R.menu.popup_account, popupMenu.getMenu());
                popupMenu.setForceShowIcon(true);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                       switch (menuItem.getItemId()){
                           case R.id.itLogOut:
                               startActivity(new Intent(getActivity(), SignInActivity.class));
                               mainActivity.finish();
                               break;
                           case R.id.itAbout:
                               Toast.makeText(getContext(), "App Code By ZuyKorean & DuyGocDua", Toast.LENGTH_SHORT).show();
                               break;
                       }
                        return true;
                    }
                });
                popupMenu.show();

            }
        });
        btnUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto.launch("image/*");
            }
        });
        tvSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.BOTTOM);
            }
        });
        btnRateYourStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRateStarDialog(Gravity.CENTER);
            }
        });
        btnProfileDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailsDialog(Gravity.CENTER, userID);
            }
        });
        if(progressScore == 30)
        {
            tvProgressString.setText("(1/3)");
        }
        else if (progressScore == 60)
        {
            tvProgressString.setText("(2/3)");
        }
        else if (progressScore == 90)
        {
            tvProgressString.setText("(3/3)");
        }
        return mView;

    }
    private void openFeedbackDialog(int gravity) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_feedback);
        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        }
        else {
            dialog.setCancelable(false);
        }
        TextInputLayout tilSendFeedback = dialog.findViewById(R.id.tilSendFeedback);
        Button btnSendFeedback = dialog.findViewById(R.id.btnSendFeedback);
        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progressScore == 30)
                {
                    tvProgressString.setText("(1/3)");
                }
                else if (progressScore == 60)
                {
                    tvProgressString.setText("(2/3)");
                }
                else if (progressScore == 90)
                {
                    tvProgressString.setText("(3/3)");
                }
                final DocumentReference docRefFeedback = FirebaseFirestore.getInstance().collection("User").document(userID);
                map.put("feedBack", tilSendFeedback.getEditText().getText().toString());
                docRefFeedback.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        if(pbProfileProgress.getProgress() != 90){
                            progressScore += 30;
                            pbProfileProgress.setProgress(progressScore);
                        }
                        Toast.makeText(getActivity(),"Thank you for feedback <3", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void openRateStarDialog(int gravity) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_ratestar);
        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        RatingBar rbRatingBar = dialog.findViewById(R.id.rbRatingBar);
        Button btnRateStar = dialog.findViewById(R.id.btnRateStar);

        rbRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                String message = null;
                myRating = ratingBar.getRating();
                switch((int) rating) {
                    case 1:
                        message = "Sorry to hear that! :(";
                        break;
                    case 2:
                        message = "You always accept suggestions!";
                        break;
                    case 3:
                        message = "Good enough!";
                        break;
                    case 4:
                        message = "Great! Thank you!";
                        break;
                    case 5:
                        message = "Awesome! You are the best!";
                        break;
                }
                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
            }
        });
        btnRateStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(progressScore == 30)
                {
                    tvProgressString.setText("(1/3)");
                }
                else if (progressScore == 60)
                {
                    tvProgressString.setText("(2/3)");
                }
                else if (progressScore == 90)
                {
                    tvProgressString.setText("(3/3)");
                }
                final DocumentReference docRefFeedback = FirebaseFirestore.getInstance().collection("User").document(userID);
                int finalRating = (int) myRating;
                map.put("rateStar", String.valueOf(finalRating));
                docRefFeedback.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        if(pbProfileProgress.getProgress() != 90){
                            progressScore += 30;
                            pbProfileProgress.setProgress(progressScore);
                        }
                        Toast.makeText(getActivity(),"Thank you for rating <3", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void openDetailsDialog(int gravity, String userID) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_info);
        Window window = dialog.getWindow();
        if(window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        TextInputLayout tilProfileFullName, tilProfileBirthday, tilProfilePhoneNumber, tilProfileAddress, tilProfileMajors;
        RadioButton rbProfileMale, rbProfileFemale;
        tilProfileFullName = dialog.findViewById(R.id.tilProfileFullName);
        tilProfileBirthday = dialog.findViewById(R.id.tilProfileBirthday);
        tilProfilePhoneNumber = dialog.findViewById(R.id.tilProfilePhoneNumber);
        tilProfileAddress = dialog.findViewById(R.id.tilProfileAddress);
        tilProfileMajors = dialog.findViewById(R.id.tilProfileMajors);
        rbProfileMale = dialog.findViewById(R.id.rbProfileMale);
        rbProfileFemale = dialog.findViewById(R.id.rbProfileFemale);
        Button btnProfileConfirm = dialog.findViewById(R.id.btnProfileConfirm);

        btnProfileConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progressScore == 30)
                {
                    tvProgressString.setText("(1/3)");
                }
                else if (progressScore == 60)
                {
                    tvProgressString.setText("(2/3)");
                }
                else if (progressScore == 90)
                {
                    tvProgressString.setText("(3/3)");
                }
                String gender;
                if(rbProfileMale.isChecked())
                {
                    gender = rbProfileMale.getText().toString();
                }
                else if (rbProfileFemale.isChecked()) {
                    gender = rbProfileFemale.getText().toString();
                }
                else {
                    gender = "";
                }
                updateUser(tilProfileFullName.getEditText().getText().toString(),
                        tilProfileBirthday.getEditText().getText().toString(),
                        gender,
                        tilProfilePhoneNumber.getEditText().getText().toString(),
                        tilProfileAddress.getEditText().getText().toString(),
                        tilProfileMajors.getEditText().getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    // Update du lieu moi len Firestore
    private void updateUser(String fullName, String birthDay, String gender, String phoneNumber, String address, String majors) {
        final DocumentReference docRefInfo = FirebaseFirestore.getInstance().collection("User").document(userID);
        map.put("fullName", fullName);
        map.put("address", address);
        map.put("birthDay", birthDay);
        map.put("gender", gender);
        map.put("phoneNumber", phoneNumber);
        map.put("majors", majors);
        docRefInfo.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                if(pbProfileProgress.getProgress() != 90){
                    progressScore += 30;
                    pbProfileProgress.setProgress(progressScore);
                }
                Toast.makeText(getActivity(),"Update Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadAvatar() {
        customProgressDialog = new CustomProgressDialog(getContext());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.KOREAN);
        Date now = new Date();
        String image_name = formatter.format(now);
        if(imageUri != null) {
            customProgressDialog.show();
            storageRef = FirebaseStorage.getInstance().getReference("avatars/"+ image_name);
            storageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if(pbProfileProgress.getProgress() != 90){
                        progressScore += 30;
                        pbProfileProgress.setProgress(progressScore);
                    }
                    if(progressScore == 30)
                    {
                        tvProgressString.setText("(1/3)");
                    }
                    else if (progressScore == 60)
                    {
                        tvProgressString.setText("(2/3)");
                    }
                    else if (progressScore == 90)
                    {
                        tvProgressString.setText("(3/3)");
                    }
                    ivProfileAvatar.setImageURI(imageUri);
                    Toast.makeText(getActivity(), "Upload Avatar Success", Toast.LENGTH_SHORT).show();
                    customProgressDialog.cancel();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    customProgressDialog.cancel();
                }
            });
        }
    }
//    private void updateProgress(String userID, int progressScore) {
//        final DocumentReference docRef = FirebaseFirestore.getInstance().collection("User").document(userID);
//        Map<String, Object> map = new HashMap<>();
//        if(progressScore == 0)
//        {
//            map.put("progressScore", 0);
//            docRef.update(map);
//        }
//        else if(progressScore == 30)
//        {
//            map.put("progressScore", 30);
//            docRef.update(map);
//        }
//        else if(progressScore == 60)
//        {
//            map.put("progressScore", 60);
//            docRef.update(map);
//        }
//        else {
//            map.put("progressScore", 90);
//            docRef.update(map);
//        }
//
//    }
}