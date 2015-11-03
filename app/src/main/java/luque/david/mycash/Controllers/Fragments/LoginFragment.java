package luque.david.mycash.Controllers.Fragments;


import android.content.Intent;
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

                Log.d("DATA", username.getEditText().getText().toString() + " " + password.getEditText().getText().toString());

                ParseUser.logInInBackground(username.getEditText().getText().toString(),
                        password.getEditText().getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e == null) {
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
