package com.example.ar_realestate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ar_realestate.databinding.FragmentLoginBinding;
public class LoginFragment extends Fragment{

    private FragmentLoginBinding binding;
    private EditText inputUserMail, inputPassword;
    private Button buttonLogin;
    public  User user;
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

                if(TextUtils.isEmpty(inputUserMail.getText().toString()) || TextUtils.isEmpty(inputPassword.getText().toString())){

                    Toast.makeText(getActivity(), "Please fill in all the blanks !!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(!TextUtils.isEmpty(inputUserMail.getText().toString()) && !TextUtils.isEmpty(inputPassword.getText().toString())){
                    Database database=new Database(getContext());
                    String userMail=inputUserMail.getText().toString();
                    String userPassword=inputPassword.getText().toString();
                  /*  user= database.loginUser(userMail,userPassword);
                   */
                    final ServiceManage serviceManage=new ServiceManage();
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            user=serviceManage.loginUser(userMail, userPassword);
                        }
                    });
                    thread.start();
                    if(user!=null){
                        Intent intent=new Intent(getActivity().getBaseContext(),MainActivity.class);
                        intent.putExtra("UserInformation",user);
                        getActivity().startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(), "E-mail or password is wrong !!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return binding.getRoot();
    }



}