package com.example.jobfinderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;


public class InfoFragment extends Fragment {

    private View mView;
    private TextView tvInfoFullName, tvInfoMajor, tvInfoGender, tvInfoAddress, tvInfoBirthday, tvInfoEmail, tvInfoPhone, tvInfoHobby;
    private ImageView ivInfoAvatar;
    private FirebaseAuth firebaseAuth;
    private CustomProgressDialog customProgressDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userRef = db.collection("User");
    private String userID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_info, container, false);

        tvInfoFullName = mView.findViewById(R.id.tvInfoFullName);
        tvInfoMajor = mView.findViewById(R.id.tvInfoMajor);
        tvInfoGender = mView.findViewById(R.id.tvInfoGender);
        tvInfoAddress = mView.findViewById(R.id.tvInfoAddress);
        tvInfoBirthday = mView.findViewById(R.id.tvInfoBirthday);
        tvInfoEmail = mView.findViewById(R.id.tvInfoEmail);
        tvInfoPhone = mView.findViewById(R.id.tvInfoPhone);
        tvInfoHobby = mView.findViewById(R.id.tvInfoHobby);
        ivInfoAvatar = mView.findViewById(R.id.ivInfoAvatar);
        customProgressDialog = new CustomProgressDialog(getContext());
        customProgressDialog.show();

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        getInfoUser(userID);
        changeAvatar(userID);
        return mView;
    }
    private void getInfoUser(String userID) {
        userRef.document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.getString("fullName").isEmpty() || documentSnapshot.getString("majors").isEmpty() || documentSnapshot.getString("gender").isEmpty() || documentSnapshot.getString("address").isEmpty() || documentSnapshot.getString("birthDay").isEmpty() || documentSnapshot.getString("email").isEmpty() || documentSnapshot.getString("phoneNumber").isEmpty() || documentSnapshot.getString("hobbies").isEmpty())
                {
                    tvInfoFullName.setText("*Unknown*");
                    tvInfoMajor.setText("*Unknown*");
                    tvInfoGender.setText("*Unknown*");
                    tvInfoAddress.setText("*Unknown*");
                    tvInfoBirthday.setText("*Unknown*");
                    tvInfoEmail.setText("*Unknown*");
                    tvInfoPhone.setText("*Unknown*");
                    tvInfoHobby.setText("*Unknown*");
                }
                else {
                    tvInfoFullName.setText(documentSnapshot.getString("fullName"));
                    tvInfoMajor.setText(documentSnapshot.getString("majors"));
                    tvInfoGender.setText(documentSnapshot.getString("gender"));
                    tvInfoAddress.setText(documentSnapshot.getString("address"));
                    tvInfoBirthday.setText(documentSnapshot.getString("birthDay"));
                    tvInfoEmail.setText(documentSnapshot.getString("email"));
                    tvInfoPhone.setText(documentSnapshot.getString("phoneNumber"));
                    tvInfoHobby.setText(documentSnapshot.getString("hobbies"));
                }
                if (customProgressDialog.isShowing())
                    customProgressDialog.dismiss();
            }
        });
    }
    private void changeAvatar(String userID) {
        userRef.document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.getString("profilePicture").isEmpty()) {
                    ivInfoAvatar.setImageResource(R.drawable.default_avatar);
                } else {
                    Picasso.get().load(documentSnapshot.getString("profilePicture")).into(ivInfoAvatar);
                }
            }
        });
    }
}