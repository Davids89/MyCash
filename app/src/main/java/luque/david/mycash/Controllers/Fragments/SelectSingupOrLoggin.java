package luque.david.mycash.Controllers.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

import luque.david.mycash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectSingupOrLoggin extends Fragment {


    public SelectSingupOrLoggin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_select_singup_or_loggin, container, false);

        CardView singup = (CardView) rootView.findViewById(R.id.card_view_singup);
        CardView login = (CardView) rootView.findViewById(R.id.card_view_login);
        LoginButton facebook = (LoginButton) rootView.findViewById(R.id.login_button_facebook);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container_loggin, new SingupFragment()).commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container_loggin, new LoginFragment()).commit();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> permissions = Arrays.asList("user_birthday", "user_location", "user_friends", "email", "public_profile");

                ParseFacebookUtils.logInWithReadPermissionsInBackground(getActivity(), permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (user == null) {
                            Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                        } else if (user.isNew()) {
                            Log.d("MyApp", "User signed up and logged in through Facebook!");
                        } else {
                            Log.d("MyApp", "User logged in through Facebook!");
                        }
                    }
                });
            }
        });

        return rootView;
    }


}
