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
public class SubtractMoneyFragment extends Fragment {


    public SubtractMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_subtract_money, container, false);

        Button button = (Button) rootView.findViewById(R.id.subtract_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = (TextView) rootView.findViewById(R.id.cash_subtract_textview);

                Integer value = Integer.valueOf(text.getText().toString()) * -1;

                ParseObject newcash = new ParseObject("UserCash");
                newcash.put("value", value);
                newcash.put("currency", "EUR");
                newcash.put("userID", "david");
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


}
