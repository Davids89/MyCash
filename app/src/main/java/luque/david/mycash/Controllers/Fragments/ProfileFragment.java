package luque.david.mycash.Controllers.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import luque.david.mycash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView name_profile;
    TextView email_profile;
    TextView birthdate_profile;
    TextView location_profile;
    ImageView user_avatar;
    View rootView;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        ParseUser currentUser = ParseUser.getCurrentUser();

        name_profile = (TextView) rootView.findViewById(R.id.name_profile);
        email_profile = (TextView) rootView.findViewById(R.id.email_profile);
        birthdate_profile = (TextView) rootView.findViewById(R.id.birthdate_profile);
        location_profile = (TextView) rootView.findViewById(R.id.location_profile);
        user_avatar = (ImageView) rootView.findViewById(R.id.user_avatar);

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

        Picasso.with(getActivity()).load("http://www.femto.it/wp-content/uploads/2014/04/default-user-avatar.png").into(user_avatar);
    }


}
