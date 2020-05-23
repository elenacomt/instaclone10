package com.example.instaclone10;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.parse.LogInCallback;
        import com.parse.ParseException;
        import com.parse.ParseUser;
        import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btlLoginActivity, btnSingUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btlLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSingUpLoginActivity = findViewById(R.id.btnSingUpLoginActivity);

        btlLoginActivity.setOnClickListener(this);
        btnSingUpLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLoginActivity:

//                final ParseUser appUser = new ParseUser();
//                appUser.setEmail(edtLoginEmail.getText().toString());
//                appUser.setPassword(edtLoginPassword.getText().toString());

                ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                        edtLoginPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (user != null && e == null) {

                                    FancyToast.makeText(LoginActivity.this,
                                            user.getUsername() + " is Logget in successfully",
                                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                            true).show();
//                                }   else {
//                                    FancyToast.makeText(LoginActivity.this,
//                                            "There was an error:" + e.getMessage(),
//                                            Toast.LENGTH_LONG, FancyToast.ERROR,
//                                            true).show();
                               transitionToSocialMediaActivity();
                                }

                            }
                        });

                break;

            case R.id.btnSingUpLoginActivity:
                break;

        }

    }

    private void transitionToSocialMediaActivity() {

        Intent intent = new Intent(LoginActivity.this, SocialMadiaActivity.class);
        startActivity(intent);

    }
}
