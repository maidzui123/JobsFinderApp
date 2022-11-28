package com.example.jobfinderapp;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;
    private TextView tvProfileUsername, tvSendFeedback;
    private View mView;
    private ImageButton ibAccountPopUp;
    private Button btnUploadPhoto, btnRateYourStar;
    private ImageView ivProfileAvatar;
    ActivityResultLauncher<String> mTakePhoto;
    float myRating = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account, container, false);
        tvProfileUsername = mView.findViewById(R.id.tvProfileUsername);
        ibAccountPopUp = mView.findViewById(R.id.ibAccountPopUp);
        btnUploadPhoto = mView.findViewById(R.id.btnUploadPhoto);
        ivProfileAvatar = mView.findViewById(R.id.ivProfileAvatar);
        tvSendFeedback = mView.findViewById(R.id.tvSendFeedback);
        btnRateYourStar = mView.findViewById(R.id.btnRateYourStar);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseFirestore = FirebaseFirestore.getInstance();
//        userID = firebaseAuth.getCurrentUser().getUid();


        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                ivProfileAvatar.setImageURI(result);
            }
        });

//        DocumentReference documentReference = firebaseFirestore.collection("User").document(userID);
//        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                tvProfileUsername.setText(value.getString("userName"));
//            }
//        });
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
                               Toast.makeText(getContext(), "Log Out Clicked", Toast.LENGTH_SHORT).show();
                               break;
                           case R.id.itAbout:
                               Toast.makeText(getContext(), "App Code By Zuykorean & DuyGocDua", Toast.LENGTH_SHORT).show();
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
                openFeedbackDialog(Gravity.CENTER);
            }
        });
        btnRateYourStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRateStarDialog(Gravity.CENTER);
            }
        });
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

        TextInputLayout tilSendFeedback = dialog.findViewById(R.id.tilSendFeedback);
        Button btnSendFeedback = dialog.findViewById(R.id.btnSendFeedback);

        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Send Feedback", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Your rating is: " + String.valueOf(myRating) + " stars!", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

}