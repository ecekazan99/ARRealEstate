package com.example.ar_realestate;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_realestate.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    EditText userName, userSurname, userMail, userPassword;
    Button register;
    String regex =  "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    TextView deneme;
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentSignUpBinding.inflate(inflater,container,false);

        binding.buttonHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment=new LoginFragment();

                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,loginFragment);
                fragmentTransaction.commit();
            }
        });

        userName=(EditText) binding.inputUserName;
        userSurname=(EditText) binding.inputUserSurname;
        userMail=(EditText) binding.inputUserMail;
        userPassword=(EditText) binding.inputPassword;

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(controlUserInfo(userName.getText().toString(),userSurname.getText().toString(),userMail.getText().toString(),
                        userPassword.getText().toString())){
                    MainActivity.database.onCreate(MainActivity.db);
                    /*
                    String sqlQuery="INSERT INTO UserInformation (UserName, UserSurname, MailAddress, Password) VALUES(?,?,?,?);";
                    SQLiteStatement statement = MainActivity.db.compileStatement(sqlQuery);
                    statement.bindString(1,userName.getText().toString());
                    statement.bindString(2,userSurname.getText().toString());
                    statement.bindString(3,userMail.getText().toString());
                    statement.bindString(4,userPassword.getText().toString());
                    statement.execute();

                     */
                    final ServiceManage serviceManage=new ServiceManage();
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            serviceManage.addNewUser(userName.getText().toString(),userSurname.getText().toString(), userMail.getText().toString(), userPassword.getText().toString());
                        }
                    });
                    thread.start();

                    Toast.makeText(getActivity(), "Registration Successful !!",
                            Toast.LENGTH_LONG).show();

                    LoginFragment loginFragment=new LoginFragment();
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,loginFragment);
                    fragmentTransaction.commit();
                }
            }
        });

        return binding.getRoot();
    }

    public boolean controlUserInfo(String userName, String userSurname, String userMail, String userPassword){

        if(TextUtils.isEmpty(userName)||TextUtils.isEmpty(userSurname)||TextUtils.isEmpty(userMail)||TextUtils.isEmpty(userPassword)){
            Toast.makeText(getActivity(), "Please fill in all the blanks !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!userMail.matches(regex)){
            Toast.makeText(getActivity(), "E-mail address format is not match !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(userPassword.length()<8){
            Toast.makeText(getActivity(), "Password is too short !!", Toast.LENGTH_SHORT).show();
            return false;
        }

        final String[] flag = {"true"};

        final ServiceManage serviceManage=new ServiceManage();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                flag[0] =serviceManage.checkEmailExist(userMail);
            }
        });
        thread.start();

        if(flag[0]=="false"){
            Toast.makeText(getActivity(), "You cannot use this e-mail address !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}