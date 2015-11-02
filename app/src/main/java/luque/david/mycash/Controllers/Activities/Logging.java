package luque.david.mycash.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parse.ParseFacebookUtils;

import luque.david.mycash.Controllers.Fragments.SelectSingupOrLoggin;
import luque.david.mycash.R;

public class Logging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container_loggin, new SelectSingupOrLoggin()).commit();
        }
    }

    //esto es para usar el logueo con datos que hay en el dispositivo a través de la app oficial
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

}
