package luque.david.mycash;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResumenFragment extends Fragment {

    Integer total = 0;
    TextView valueTextView;


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

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("UserCash");
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

        //initial value

        Button moreButton = (Button) rootView.findViewById(R.id.more);
        Button lessButton = (Button) rootView.findViewById(R.id.less);

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new AddMoneyFragment())
                        .addToBackStack(null).commit();
            }
        });

        lessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new SubtractMoneyFragment())
                        .addToBackStack(null).commit();
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }


}
