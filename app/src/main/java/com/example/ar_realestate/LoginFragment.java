package com.example.ar_realestate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ar_realestate.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private EditText inputUserMail, inputPassword;
    private Button buttonLogin;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentLoginBinding.inflate(inflater,container,false);
       binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SignUpFragment signUpFragment=new SignUpFragment();

               FragmentManager fragmentManager=getFragmentManager();
               FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,signUpFragment);
               fragmentTransaction.commit();
           }
       });

        inputUserMail=(EditText) binding.inputUserMail;
        inputPassword=(EditText) binding.inputPassword;
        buttonLogin=(Button) binding.buttonLogin;

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Database database=new Database(getContext());
               String userMail=inputUserMail.getText().toString();
               String userPassword=inputPassword.getText().toString();
               User user= database.loginUser(userMail,userPassword);

                if(user!=null){
                        Intent intent=new Intent(getActivity().getBaseContext(),MainActivity.class);
                    Toast.makeText(getActivity(), user.getUserId()+" "+ user.getUserName().toString(),
                            Toast.LENGTH_LONG).show();
                   intent.putExtra("UserInformation",user);
                    getActivity().startActivity(intent);

                    //https://www.youtube.com/watch?v=TjAsoWs0f5o
                    //35.dakika
               }
            }
        });

        return binding.getRoot();
    }
}