package com.example.tikweb.loginSection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tikweb.R;
import com.example.tikweb.databinding.ActivityUserProfile2Binding;
import com.example.tikweb.databinding.ActivityWelcomeBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    ActivityUserProfile2Binding binding;
    ImageView ivImage;

    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile2);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ivImage=findViewById(R.id.imageView);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_user_profile2);
//        tvName=findViewById(R.id.tv_name);
//        btLogout=findViewById(R.id.bt_logout);

        // Initialize firebase auth
        firebaseAuth=FirebaseAuth.getInstance();

        // Initialize firebase user
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        // Check condition
        if(firebaseUser!=null)
        {
            // When firebase user is not equal to null
            // Set image on image view
            Glide.with(UserProfileActivity.this)
                    .load(firebaseUser.getPhotoUrl())
                    .into(ivImage);
            // set name on text view
            binding.userName.setText(firebaseUser.getDisplayName());
            binding.userEmail.setText(firebaseUser.getEmail());
            binding.details.setText("Created Date and time: "+new Date(firebaseUser.getMetadata().getCreationTimestamp()));
        }

        // Initialize sign in client
        googleSignInClient= GoogleSignIn.getClient(UserProfileActivity.this
                , GoogleSignInOptions.DEFAULT_SIGN_IN);

//        btLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Sign out from google
//                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // Check condition
//                        if(task.isSuccessful())
//                        {
//                            // When task is successful
//                            // Sign out from firebase
//                            firebaseAuth.signOut();
//
//                            // Display Toast
//                            Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
//
//                            // Finish activity
//                            finish();
//                        }
//                    }
//                });
//            }
//        });
    }
}