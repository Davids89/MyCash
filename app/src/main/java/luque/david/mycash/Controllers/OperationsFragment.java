package luque.david.mycash.Controllers;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import luque.david.mycash.Adapters.CashAdapter;
import luque.david.mycash.Models.Cash;
import luque.david.mycash.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OperationsFragment extends Fragment {

    ArrayList<String> Categories = new ArrayList<String>();
    private Spinner categoriesSpinner;
    String categorySelected;
    View rootView;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;


    public OperationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_operations, container, false);

        categoriesSpinner = (Spinner) rootView.findViewById(R.id.spinner_operations);

        recycler = (RecyclerView) rootView.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        manager = new LinearLayoutManager(rootView.getContext());
        recycler.setLayoutManager(manager);

        LoadCategories();

        // Inflate the layout for this fragment
        return rootView;
    }

    public void LoadCategories(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Categories");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects != null) {
                    for (ParseObject object : objects) {
                        Categories.add(object.getString("name"));
                    }
                }

                //se tiene que llamar aqui porque al ser asíncrona no se mostraba el item en el spinner
                SetUpSpinner();
            }
        });
    }

    public void SetUpSpinner(){

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

                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Cash");
                query.setLimit(10);
                query.whereEqualTo("userID", "david");
                query.whereEqualTo("category", categorySelected);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        List items = new ArrayList();

                        if (objects != null) {
                            for(ParseObject object: objects){
                                Log.d("VALOR", String.valueOf(object.getInt("value")));
                                items.add(new Cash(object.getInt("value"),
                                        object.getString("currency"),
                                        object.getString("category"),
                                        object.getString("userID")));
                            }

                            Log.d("ITEMS", String.valueOf(items.size()));

                            if(items.size() == 0){
                                items.add(new Cash());
                            }

                            ConfigRecyclerView(items);

                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void ConfigRecyclerView(List items){

        //obtenemos el recycler

        adapter = new CashAdapter(items);
        recycler.setAdapter(adapter);
    }


}
