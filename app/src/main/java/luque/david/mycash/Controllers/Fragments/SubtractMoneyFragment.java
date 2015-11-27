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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.realm.Realm;
import luque.david.mycash.Controllers.Fragments.ResumenFragment;
import luque.david.mycash.Models.Cash;
import luque.david.mycash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubtractMoneyFragment extends Fragment {

    private Spinner spinner;
    private String categorySelected;
    private Realm realm;
    private TextView cash;


    public SubtractMoneyFragment() {
        // Required empty public constructor
    }

    public void iniciaRealm(){
        realm.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_subtract_money, container, false);

        Button button = (Button) rootView.findViewById(R.id.subtract_button);

        iniciaRealm();

        //Spinner

        spinner = (Spinner) rootView.findViewById(R.id.categories_spinner_subtract);

        SetUpSpinner();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cash = (TextView) rootView.findViewById(R.id.cash_subtract_textview);
                Cash myCash = new Cash();

                myCash = creaValor(myCash);

                guardaValor(myCash);

                Snackbar.make(view, "Restado añadido con exito", Snackbar.LENGTH_LONG)
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

    private void guardaValor(Cash cash) {

        realm.beginTransaction();
        realm.copyToRealm(cash);
        realm.commitTransaction();

    }

    public Cash creaValor (Cash myCash){
        myCash.setmCategory(categorySelected);
        myCash.setmCurrency("EUR");

        DateFormat df = new SimpleDateFormat("dd MM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        myCash.setmDate(date);

        ParseUser user = ParseUser.getCurrentUser();

        myCash.setmUserID(String.valueOf(user.getObjectId())); //TODO poner id de usuario

        myCash.setmValue(Integer.valueOf(cash.getText().toString()) * -1);

        return myCash;
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}
