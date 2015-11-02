package luque.david.mycash.Controllers.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import luque.david.mycash.Controllers.Fragments.ResumenFragment;
import luque.david.mycash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubtractMoneyFragment extends Fragment {

    private Spinner spinner;
    private String categorySelected;


    public SubtractMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_subtract_money, container, false);

        Button button = (Button) rootView.findViewById(R.id.subtract_button);

        //Spinner

        spinner = (Spinner) rootView.findViewById(R.id.categories_spinner_subtract);

        SetUpSpinner();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) rootView.findViewById(R.id.cash_subtract_textview);
                ParseUser user = ParseUser.getCurrentUser();

                Integer value = Integer.valueOf(text.getText().toString()) * -1;

                ParseObject newcash = new ParseObject("Cash");
                newcash.put("value", value);
                newcash.put("currency", "EUR");
                newcash.put("userID", String.valueOf( user.getObjectId() ));
                newcash.put("category", categorySelected);
                newcash.saveInBackground();

                Snackbar.make(view, "Dinero restado con exito", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                getFragmentManager().beginTransaction()
                                        .replace(R.id.container, new ResumenFragment()).commit();
                            }
                        },
                        500);
            }
        });

        return rootView;
    }

    public void SetUpSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.categories)
        );

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                categorySelected = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


}
