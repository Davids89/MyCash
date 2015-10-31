package luque.david.mycash.Controllers.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        return rootView;
    }


}
