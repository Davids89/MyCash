package luque.david.mycash.Controllers.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import luque.david.mycash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences myUserData = getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = myUserData.getString("email", "");
        String username = myUserData.getString("username", "Nombre");
        String birthdate = myUserData.getString("birthdate", "Sin datos");
        String location = myUserData.getString("location", "Sin datos");

        Log.d("Datos", email + " " + username + " " + birthdate + " " + location);

        return rootView;
    }


}
