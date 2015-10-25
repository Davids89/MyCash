package luque.david.mycash.Controllers;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import luque.david.mycash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumenFragment extends Fragment {

    Integer total = 0;
    TextView valueTextView;
    ArrayList<String> categories;
    Bundle args = new Bundle();


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

        //load categories
        LoadCategories();

        Button moreButton = (Button) rootView.findViewById(R.id.more);
        Button lessButton = (Button) rootView.findViewById(R.id.less);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment addFragment = new AddMoneyFragment();

                args.putStringArrayList("CATEGORIAS", categories);

                addFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container, addFragment)
                        .addToBackStack(null).commit();
            }
        });

        lessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment subtractFragment = new SubtractMoneyFragment();

                args.putStringArrayList("CATEGORIAS", categories);

                subtractFragment.setArguments(args);

                getFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.container, subtractFragment)
                        .addToBackStack(null).commit();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void Resume(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Cash");
        query.whereEqualTo("userID", "david");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects != null) {
                    for (ParseObject object : objects) {
                        total += object.getInt("value");
                    }
                }

                valueTextView.setText(String.valueOf(total) + "€");
            }
        });
    }

    public void LoadCategories(){
        Bundle args = getArguments();
        categories = args.getStringArrayList("CATEGORIAS");
    }


}
