package luque.david.mycash;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OperationsFragment extends Fragment {

    ArrayList<String> Categories = new ArrayList<String>();
    private Spinner categoriesSpinner;
    String categorySelected;


    public OperationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_operations, container, false);

        categoriesSpinner = (Spinner) rootView.findViewById(R.id.spinner_operations);

        LoadCategories();

        // Inflate the layout for this fragment
        return rootView;
    }

    public void LoadCategories(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Categories");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects != null){
                    for(ParseObject object: objects) {
                        Categories.add(object.getString("name"));
                    }
                }

                //se tiene que llamar aqui porque al ser asíncrona no se mostraba el item en el spinner
                SetUpSpinner();
            }
        });
    }

    public void SetUpSpinner(){

        Log.d("Entra", "ENTRA");

        for(String a: Categories){
            Log.d("Valor",a);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                Categories
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
