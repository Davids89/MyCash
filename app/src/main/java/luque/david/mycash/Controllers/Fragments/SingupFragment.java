package luque.david.mycash.Controllers.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import luque.david.mycash.Controllers.Activities.MainActivity;
import luque.david.mycash.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingupFragment extends Fragment {

    TextInputLayout email;
    TextInputLayout password;
    TextInputLayout username;
    CardView singup;


    public SingupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_singup, container, false);

        singup = (CardView) rootView.findViewById(R.id.card_view_login);

        email = (TextInputLayout) rootView.findViewById(R.id.textview_email);
        password = (TextInputLayout) rootView.findViewById(R.id.textview_password);
        username = (TextInputLayout) rootView.findViewById(R.id.textview_username);

        //TODO poner esto para los errores
        /*mEmailInput.setErrorEnabled(false);
        mEmailInput.setError("Cant be blank");*/

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                ParseUser user = new ParseUser();
                user.logOut();

                user.setEmail(email.getEditText().getText().toString());
                user.setPassword(password.getEditText().getText().toString());
                user.setUsername(username.getEditText().getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            Snackbar.make(view, "Usuario registrado con éxito",
                                    Snackbar.LENGTH_LONG).setAction("", null).show();
                            Intent myIntent = new Intent(getActivity(), MainActivity.class);
                            startActivity(myIntent);
                            getActivity().finish();

                        } else {
                            Log.e("ERROR", e.toString());
                        }
                    }
                });

                /*if(validate()){
                    user.signUpInBackground(new SignUpCallback(){
                        @Override
                        public void done(ParseException e) {

                            if(e == null){

                                Snackbar.make(view, "Usuario registrado con éxito",
                                        Snackbar.LENGTH_LONG).setAction("",null).show();
                                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                                startActivity(myIntent);
                                getActivity().finish();

                            }else{
                                Log.e("ERROR",e.toString());
                            }
                        }
                    });
                }else{
                    onLoginFailed();
                }*/

            }
        });

        return rootView;
    }

    public boolean validate(){

        boolean valid = true;

        String usernameText = username.getEditText().getText().toString();
        String emailText = email.getEditText().getText().toString();
        String passwordText = password.getEditText().toString();

        if(usernameText.isEmpty()){
            username.setErrorEnabled(true);
            username.setError("Debes introducir un nombre de usuario");
            valid = false;
        }

        if(emailText.isEmpty() ||  !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            Log.d("EMAIL",String.valueOf(Patterns.EMAIL_ADDRESS.matcher(emailText).matches()));
            email.setError("Introduzca email válido");
            valid = false;
        }else{
            email.setErrorEnabled(false);
        }

        if(passwordText.isEmpty() || passwordText.length() < 4 || passwordText.length() > 10){
            password.setError("Contraseña entre 4 y 10 caracteres");
            valid = false;
        }else{
            password.setErrorEnabled(false);
        }

        return valid;

    }

    public void onLoginFailed(){

        Snackbar.make(getView(), "Registro fallido", Snackbar.LENGTH_SHORT).show();

    }

}
