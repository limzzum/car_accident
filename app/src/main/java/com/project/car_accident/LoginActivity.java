package com.project.car_accident;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import java.security.MessageDigest;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Firebase
    private static final int RC_SIGN_IN = 10;
    //private GoogleApiClient mGoogleApiClient;
    private GoogleSignInClient mSignInClient;
    private FirebaseAuth mAuth;

    //hashkey init
    private Context mContext;

    private GoogleSignInClient mGoogleSignInClient;


    private static final int REQUEST_CODE_GOOGLE_SIGN_IN = 1; /* unique request id */





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //hashkey
        mContext = getApplicationContext();
        //getHashKey(mContext);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);

        signInButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sign_in_button){
            signIn();
        }

    }



    private void signIn() {
        GetSignInIntentRequest request =
                GetSignInIntentRequest.builder()
                        .setServerClientId(getString(R.string.client_id))
                        .build();

        Identity.getSignInClient(this)
                .getSignInIntent(request)
                .addOnSuccessListener(
                        new OnSuccessListener<PendingIntent>() {
                            @Override
                            public void onSuccess(PendingIntent result) {
                                try {
                                    LoginActivity.this.startIntentSenderForResult(
                                            result.getIntentSender(),
                                            REQUEST_CODE_GOOGLE_SIGN_IN,
                                            /* fillInIntent= */ null,
                                            /* flagsMask= */ 0,
                                            /* flagsValue= */ 0,
                                            /* extraFlags= */ 0,
                                            /* options= */ null);
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GOOGLE_SIGN_IN) {
            try {
                SignInCredential credential = Identity.getSignInClient(this).getSignInCredentialFromIntent(data);
                // Signed in successfully - show authenticated UI
                
                //Log.e(TAG,credential.getId()+" "+credential.getDisplayName());
                //이메일/이름 저장
                Sharedpreference.setSharedPrefEmail(this,credential.getId());
                Sharedpreference.setSharedPrefName(this,credential.getDisplayName());
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
                //updateUI(credential)
            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                e.printStackTrace();
            }
        }
    }




}