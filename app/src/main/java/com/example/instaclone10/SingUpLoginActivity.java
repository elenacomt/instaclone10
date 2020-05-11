package com.example.instaclone10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SingUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSingUp, edtUserNameLogin, edtPasswordSingUp, edtPasswordLogin;
    private Button btnSingUp, btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.singup_login_activity);

        edtUserNameSingUp = findViewById(R.id.edtUserNameSingUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordSingUp = findViewById(R.id.edtPasswordSingUp);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);

        btnSingUp = findViewById(R.id.btnUserSingUp);
        btnLogin = findViewById(R.id.btnUserLogin);

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSingUp.getText().toString());
                appUser.setPassword(edtPasswordSingUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            FancyToast.makeText(SingUpLoginActivity.this, appUser.get("username") + " is singet app successfuly ", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        } else {

                            FancyToast.makeText(SingUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUserNameLogin.getText()
                        .toString(), edtPasswordLogin.getText().toString(),
                        new LogInCallback() {


                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if ( user != null && e == null) {
                            FancyToast.makeText(SingUpLoginActivity.this, user.get("username") + " is logged  in successfuly ", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();


                        }else {
                            FancyToast.makeText(SingUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                        }

                    }
                });
            }
        });




    }
}
