package luque.david.mycash.Controllers.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import luque.david.mycash.Controllers.Activities.MainActivity;
import luque.david.mycash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    TextInputLayout username;
    TextInputLayout password;
    CardView login;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        username = (TextInputLayout) rootView.findViewById(R.id.textview_username_login);
        password = (TextInputLayout) rootView.findViewById(R.id.textview_password_login);
        login = (CardView) rootView.findViewById(R.id.card_view_login_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser.logInInBackground(username.getEditText().getText().toString(),
                        password.getEditText().getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e == null) {

                                    ParseUser userdata = ParseUser.getCurrentUser();

                                    String userEmail = userdata.getEmail();
                                    String name = userdata.getString("username");
                                    String birthdate = userdata.getString("birthdate");
                                    String location = userdata.getString("location");

                                    Log.d("DATOS ANTES", userEmail + " " + name + " " + birthdate + " " + location);

                                    SharedPreferences myUserData = getActivity().getPreferences(Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = myUserData.edit();
                                    editor.putString(userEmail, "email");
                                    editor.putString(name, "name");
                                    editor.putString(birthdate, "birthdate");
                                    editor.putString(location, "location");
                                    editor.commit();

                                    Intent myIntent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(myIntent);
                                    getActivity().finish();
                                } else {
                                    Snackbar.make(getView(), "Las credenciales no son válidas", Snackbar.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
            }
        });

        return rootView;
    }


}
