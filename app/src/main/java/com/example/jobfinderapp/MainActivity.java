package com.example.jobfinderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.Toast;


import com.example.jobfinderapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.type.DateTime;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private String userID;
    private String strProfileFullName, strProfileMajors;
    private String strProgressScore;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        getUserInfo(userID);
        replaceFragment(new HomeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.nav_near:
                    replaceFragment(new NearbyFragment());
                    break;
                case R.id.nav_applied:
                    replaceFragment(new AppliedFragment());
                    break;
                case R.id.nav_account:
                    replaceFragment(new AccountFragment());
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    public String getUsername() {
        return strProfileFullName;
    }
    public String getMajors() {
        return strProfileMajors;
    }
    public String getProgressScore() {
        return strProgressScore;
    }

    private void getUserInfo(String userID) {
        DocumentReference documentReference = firebaseFirestore.collection("User").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                strProfileFullName = value.getString("fullName");
                strProfileMajors = value.getString("majors");
//                strProgressScore = value.getString("progressScore");
            }
        });
    }
//    private void updateUser(String userID) {
//        final DocumentReference docRef = FirebaseFirestore.getInstance().collection("User").document(userID);
//        Map<String, Object> map = new HashMap<>();
//        map.put("fullName", "Nguyen Mai Duy");
//        map.put("address", "");
//        map.put("birthDay", "");
//        map.put("phoneNumber", "");
//        map.put("majors", "");
//        docRef.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(getApplicationContext(),"Update Success", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    public String getAvatar() {
//        return imageUri;
//    }
//    public void setAvatar(String imageUri) {
//        this.imageUri = imageUri;
//    }

}
