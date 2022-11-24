package com.example.jobfinderapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView tvProfileUsername;
    private View mView;
    private ImageButton ibAccountPopUp;
    private Button btnUploadPhoto;
    private ImageView ivProfileAvatar;
    ActivityResultLauncher<String> mTakePhoto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account, container, false);
        tvProfileUsername = mView.findViewById(R.id.tvProfileUsername);
        ibAccountPopUp = mView.findViewById(R.id.ibAccountPopUp);
        btnUploadPhoto = mView.findViewById(R.id.btnUploadPhoto);
        ivProfileAvatar = mView.findViewById(R.id.ivProfileAvatar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();


        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                ivProfileAvatar.setImageURI(result);
            }
        });

        DocumentReference documentReference = firebaseFirestore.collection("User").document(userID);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                tvProfileUsername.setText(value.getString("userName"));
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
        return mView;

    }


}