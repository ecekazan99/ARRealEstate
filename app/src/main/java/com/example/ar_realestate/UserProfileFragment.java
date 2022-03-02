package com.example.ar_realestate;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ar_realestate.databinding.FragmentLoginBinding;
import com.example.ar_realestate.databinding.FragmentUserProfileBinding;

public class UserProfileFragment extends Fragment {

    private FragmentUserProfileBinding binding;

    private EditText inputUserName,inputUserSurname,inputUserMail, inputPassword,inputNewPassword;
    private Button buttonUpdate;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding= FragmentUserProfileBinding.inflate(inflater,container,false);

        Intent intent=getActivity().getIntent();
        User user=(User)intent.getSerializableExtra("UserInformation");

        binding.inputUserName.setText(user.getUserName());
        binding.inputUserSurname.setText(user.getUserSurname());
        binding.inputUserMail.setText(user.getMailAddress());

        buttonUpdate=(Button) binding.buttonUpdate;

        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int userId=user.getUserId();
                String userPassword=user.getPassword();

                inputUserName=(EditText)binding.inputUserName;
                inputUserSurname=(EditText)binding.inputUserSurname;
                inputUserMail=(EditText) binding.inputUserMail;
                inputPassword=(EditText) binding.inputPassword;
                //inputNewPassword=(EditText) binding.inputNewPassword;

                Database database=new Database(getContext());
                int a= database.updateUser(userId,inputUserName.getText().toString(),inputUserSurname.getText().toString(),
                 inputUserMail.getText().toString(),inputPassword.getText().toString(),inputPassword.getText().toString());

                if(a==1){
                    Intent intent=new Intent(getActivity().getBaseContext(),MainActivity.class);
                    Toast.makeText(getActivity(), user.getUserId()+" "+ user.getUserName().toString(),
                            Toast.LENGTH_LONG).show();
                    intent.putExtra("UserInformation",user);
                    getActivity().startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "asd",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

                return  binding.getRoot();
    }
}