package com.example.instaclone10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

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

        Intent intent = new Intent(SingUp.this, SocialMadiaActivity.class);
        startActivity(intent);

    }
}


////////////////////////////////

//package com.example.instaclone10;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.os.Bundle;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.Toast;
//
//        import com.parse.LogInCallback;
//        import com.parse.ParseException;
//        import com.parse.ParseUser;
//        import com.shashank.sony.fancytoastlib.FancyToast;
//
//public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
//
//    private EditText edtLoginEmail,  edtLoginPassword;
//    private Button btlLoginActivity, btnSingUpLoginActivity;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        setTitle("Log In");
//
//        edtLoginEmail = findViewById(R.id.edtLoginEmail);
//        edtLoginPassword = findViewById(R.id.edtLoginPassword);
//        btlLoginActivity= findViewById(R.id.btnLoginActivity);
//        btnSingUpLoginActivity = findViewById(R.id.btnSingUpLoginActivity);
//
//        btlLoginActivity.setOnClickListener(this);
//        btnSingUpLoginActivity.setOnClickListener(this);
//
//        if(ParseUser.getCurrentUser() != null) {
//            ParseUser.getCurrentUser().logOut();
//        }
//
//    }
//
//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()) {
//
//            case R.id.btnLoginActivity:
//                ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
//                        edtLoginPassword.getText().toString(),
//                        new LogInCallback() {
//                            @Override
//                            public void done(ParseUser user, ParseException e) {
//
//                                if (user != null && e == null) {
//
//                                    FancyToast.makeText(LoginActivity.this,
//                                            user.getUsername() + " is Logget in successfully",
//                                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
//                                            true).show();
//                                }
//
//                            }
//                        });
//
//
//
//                break;
//
//            case R.id.btnSingUpLoginActivity:
//                break;
//
//        }
//
//    }
