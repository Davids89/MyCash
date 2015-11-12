package luque.david.mycash.Controllers.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseUser;

import luque.david.mycash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public static final String spFile = "userPrefs";


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        ParseUser currentUser = ParseUser.getCurrentUser();

        String name = currentUser.getString("username");
        String email = currentUser.getString("email");
        String birthdate = currentUser.getString("birthdate");
        String locatin = currentUser.getString("location");

        return rootView;
    }


}
