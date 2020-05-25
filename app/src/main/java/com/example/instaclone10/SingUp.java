package com.example.instaclone10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SingUp extends AppCompatActivity implements View.OnClickListener{


   // Ui Components
    private EditText edtEmail, edtUserName, edtPassword;
    private  Button btnSingUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       setTitle("Sing Up");

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtPassword = findViewById(R.id.edtEnterPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(btnSingUp);

                }
                return false;
            }
        });

        edtUserName = findViewById(R.id.edtUserName);
        btnSingUp = findViewById(R.id.btnSingUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSingUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
          //  ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSingUp:

                if(edtEmail.getText().toString().equals("") ||
                        edtUserName.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")) {

                    FancyToast.makeText(SingUp.this,
                            "Email, Username, Password is required ",
                            Toast.LENGTH_SHORT, FancyToast.INFO,
                            true).show();


                }else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up" + edtUserName.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SingUp.this,
                                        appUser.getUsername() + "is signing up",
                                        Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                        true).show();
                                transitionToSocialMediaActivity();

                            } else {
                                FancyToast.makeText(SingUp.this,
                                        "There was an error: " + e.getMessage(),
                                        Toast.LENGTH_LONG, FancyToast.ERROR,
                                        true).show();
                            }
                            progressDialog.dismiss();

                        }
                    });

                }

                break;
            case R.id.btnLogin:

                Intent intent = new Intent(SingUp.this, LoginActivity.class);
                startActivity(intent);

                break;
        }
    }

    public void rootLayoutTapped (View view) {

        try {

            InputMethodManager inpuntMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inpuntMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(SingUp.this, SocialMediaActivity.class);
        startActivity(intent);

    }
}


