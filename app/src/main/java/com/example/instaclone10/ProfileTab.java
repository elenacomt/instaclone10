package com.example.instaclone10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession,
            edtProfileHobbies, edtProfileFavSport;
    private Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment

        View view =   inflater.inflate(R.layout.fragment_profile_tab, container,
                false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavoriteSport);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser  parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null) {
            edtProfileName.setText("");
        }else {
            edtProfileName.setText(parseUser.get("profileName").toString());


        }
        edtProfileBio.setText(parseUser.get("ProfileBio") + "");
        edtProfileProfession.setText(parseUser.get("profileProfession") + "");
        edtProfileHobbies.setText(parseUser.get("ProfileHobbies") + "");
        edtProfileFavSport.setText(parseUser.get("ProfileFavSport") + "");

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("ProfileName", edtProfileName.getText().toString());
                parseUser.put("ProfileBio", edtProfileBio.getText().toString());
                parseUser.put("ProfileProfession", edtProfileProfession.getText().toString());
                parseUser.put("ProfileHobbies", edtProfileHobbies.getText().toString());
                parseUser.put("ProfileFavSport", edtProfileFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            FancyToast.makeText(getContext(),
                                    "Info Updated",
                                    Toast.LENGTH_SHORT, FancyToast.INFO,
                                    true).show();

                        } else {

                            FancyToast.makeText(getContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT, FancyToast.ERROR,
                                    true).show();

                        }

                    }
                });
            }
        });

        return view;



    }

    }












