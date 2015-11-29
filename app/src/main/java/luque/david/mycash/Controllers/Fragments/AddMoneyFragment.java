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

import org.w3c.dom.Text;

import luque.david.mycash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFragment extends Fragment{

    private Spinner categoriesSpinner;
    private String categorySelected;
    private TextView subject;


    public AddMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_add_money, container, false);

        Button addButton = (Button) rootView.findViewById(R.id.add_button);

        //spinner
        categoriesSpinner = (Spinner) rootView.findViewById(R.id.categories_spinner);
        subject = (TextView) rootView.findViewById(R.id.subject);

        SetUpSpinner();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView cash = (TextView) rootView.findViewById(R.id.cash_textview);

                ParseUser user = ParseUser.getCurrentUser();

                ParseObject newcash = new ParseObject("Cash");
                newcash.put("value", Integer.valueOf(cash.getText().toString()));
                newcash.put("currency", "EUR");
                newcash.put("subject", subject.getText().toString());
                newcash.put("userID", String.valueOf(user.getObjectId()));
                newcash.put("category", categorySelected);
                newcash.saveInBackground();

                Snackbar.make(view, "Dinero añadido con exito", Snackbar.LENGTH_LONG)
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

        categoriesSpinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                categorySelected = categoriesSpinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
