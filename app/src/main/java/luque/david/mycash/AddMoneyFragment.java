package luque.david.mycash;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFragment extends Fragment {


    public AddMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_add_money, container, false);

        Button addButton = (Button) rootView.findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView cash = (TextView) rootView.findViewById(R.id.cash_textview);

                ParseObject newcash = new ParseObject("UserCash");
                newcash.put("value", Integer.valueOf(cash.getText().toString()));
                newcash.put("currency", "EUR");
                newcash.put("userID", "david");
                newcash.saveInBackground();

                Snackbar.make(view, "Dinero añadido con exito", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }




}
