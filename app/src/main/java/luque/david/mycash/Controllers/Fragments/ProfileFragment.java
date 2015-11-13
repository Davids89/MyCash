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
import android.widget.TextView;

import com.parse.ParseUser;

import luque.david.mycash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public static final String spFile = "userPrefs";
    TextView name_profile;
    TextView email_profile;
    TextView birthdate_profile;
    TextView location_profile;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        ParseUser currentUser = ParseUser.getCurrentUser();

        name_profile = (TextView) rootView.findViewById(R.id.name_profile);
        email_profile = (TextView) rootView.findViewById(R.id.email_profile);
        birthdate_profile = (TextView) rootView.findViewById(R.id.birthdate_profile);
        location_profile = (TextView) rootView.findViewById(R.id.location_profile);

        String name = currentUser.getString("username");
        String email = currentUser.getString("email");
        String birthdate = currentUser.getString("birthdate");
        String location = currentUser.getString("location");

        initValues(name, email, birthdate, location);

        return rootView;
    }

    public void initValues(String name, String email, String birthdate, String location){

        if(name != null){
            name_profile.setText(name);
        }else{
            name_profile.setText("No has introducido tu nombre");
        }

        if(email != null){
            email_profile.setText(email);
        }else{
            email_profile.setText("usuario@correo.com");
        }

        if(birthdate != null){
            birthdate_profile.setText(birthdate);
        }else{
            birthdate_profile.setText("01/01/2015");
        }

        if(location != null) {
            location_profile.setText(location);
        }else{
            //TODO coger la localizacion por gps
            location_profile.setText("Sin localización");
        }
    }


}
