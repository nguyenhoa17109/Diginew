package com.nguyenhoa.diginew.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.nguyenhoa.diginew.R;

public class Splash2 extends AppCompatActivity {
    TextView tvTerms, tvSkip;
    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private LoginButton btLoginFb;
    private Button btLoginGg;
    private static final int GOOGLE_SIGN_IN_REQUEST = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash2);

        firebaseAuth = FirebaseAuth.getInstance();
        InitGoogleLogin();
        InitFacebookLogin();

        tvTerms = findViewById(R.id.tvTerms);
        tvSkip = findViewById(R.id.tvSkipLogin);

        SpannableString ss = new SpannableString("Bằng việc đăng nhập, bạn đồng ý với các Điều khoản và Chính sách của DigiNews");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(Splash2.this, Terms.class));
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 40, 64, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        tvTerms.setText(ss);
        tvTerms.setMovementMethod(LinkMovementMethod.getInstance());
        tvTerms.setHighlightColor(Color.TRANSPARENT);

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splash2.this, SubjectsFavorite.class));
                finish();
            }
        });

    }

    private void InitFacebookLogin() {
        btLoginFb = findViewById(R.id.btLoginFb);
        callbackManager = CallbackManager.Factory.create();

        btLoginFb.setReadPermissions("email", "public_profile");
        btLoginFb.setLoginBehavior(LoginBehavior.WEB_ONLY);

        btLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "Facebook success", Toast.LENGTH_SHORT).show();
                handleFacebookLogin(loginResult);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Facebook cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "Facebook error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookLogin(LoginResult loginResult) {
        AuthCredential authCredential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(Splash2.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    sendUserData(user);
                    Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Splash2.this, SubjectsFavorite.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void InitGoogleLogin() {
        btLoginGg = findViewById(R.id.btLoginGmai);
        btLoginGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGoogleLogin();
            }
        });
    }

    private void doGoogleLogin() {
        //creat google signin option object
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("661581717812-ket7hufc0kdj0gelfifdfm3p4ini9kp2.apps.googleusercontent.com")
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();
        //creat google client object
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(Splash2.this, googleSignInOptions);
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, GOOGLE_SIGN_IN_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check result come from GOOGLE
        if (requestCode == GOOGLE_SIGN_IN_REQUEST) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = accountTask.getResult(ApiException.class);
                processFirebaseLoginStep(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void processFirebaseLoginStep(String idToken) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(Splash2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            sendUserData(user);

                            startActivity(new Intent(Splash2.this, SubjectsFavorite.class));
                            finish();
                        }
                    }
                });
    }

    private void sendUserData(FirebaseUser user) {
        Toast.makeText(getApplicationContext(), "User: " + user.getDisplayName() + "\n" + user.getEmail(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(Splash2.this, SubjectsFavorite.class));
            Toast.makeText(getApplicationContext(), "User: " + user.getDisplayName() + "\n" + user.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }
}