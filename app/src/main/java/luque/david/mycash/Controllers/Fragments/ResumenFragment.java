package luque.david.mycash.Controllers.Fragments;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import luque.david.mycash.Controllers.Activities.MainActivity;
import luque.david.mycash.Models.Cash;
import luque.david.mycash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumenFragment extends Fragment {

    Integer total = 0;
    TextView valueTextView;
    ArrayList<String> Categories = new ArrayList<String>();
    Bundle args = new Bundle();
    Realm realm;

    public ResumenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_resumen, container, false);
        //get value from Parse

        valueTextView = (TextView) rootView.findViewById(R.id.resumen_value);

        total = 0; //when reenter in this fragment we have to reinit the value

        //Resume the user status
        Resume();

        Button moreButton = (Button) rootView.findViewById(R.id.more);
        Button lessButton = (Button) rootView.findViewById(R.id.less);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container, new AddMoneyFragment())
                        .addToBackStack(null).commit();
            }
        });

        lessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container, new SubtractMoneyFragment())
                        .addToBackStack(null).commit();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void Resume(){

        realm = ((MainActivity)getActivity()).getRealm();

        RealmQuery<Cash> query = realm.where(Cash.class);

        query.findAll();

        Log.wtf("CASH", query.toString());

    }

}
