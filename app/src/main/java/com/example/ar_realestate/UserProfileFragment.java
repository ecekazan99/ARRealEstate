package com.example.ar_realestate;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
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
                inputNewPassword=(EditText) binding.inputNewPassword;

                if(!TextUtils.isEmpty(inputUserName.getText().toString()) && !TextUtils.isEmpty(inputUserSurname.getText().toString()) &&
                        !TextUtils.isEmpty(inputUserMail.getText().toString()) && !TextUtils.isEmpty(inputPassword.getText().toString()) ){

                    if(inputPassword.getText().toString().equals(userPassword)){
                        Database database=new Database(getContext());
                        int updateUserInfo=0;
                        String tempPassword="";
                        int temp=0;
                        if(!TextUtils.isEmpty(inputNewPassword.getText().toString())){
                            if(inputNewPassword.getText().toString().length()<8){
                                temp=1;
                            }
                            tempPassword=inputNewPassword.getText().toString();
                        }
                        else{
                            tempPassword=inputPassword.getText().toString();
                        }

                        updateUserInfo= database.updateUser(userId,inputUserName.getText().toString(),inputUserSurname.getText().toString(),
                                inputUserMail.getText().toString(),inputPassword.getText().toString(),tempPassword);

                        if(updateUserInfo==1&&temp==0){
                            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                            String finalTempPassword = tempPassword;
                            builder.setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    user.setPassword(finalTempPassword);
                                    user.setUserName(inputUserName.getText().toString());
                                    user.setUserSurname(inputUserSurname.getText().toString());
                                    user.setMailAddress(inputUserMail.getText().toString());

                                    UserProfileFragment userProfileFragment=new UserProfileFragment();
                                    FragmentManager fragmentManager=getFragmentManager();
                                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,userProfileFragment);
                                    fragmentTransaction.commit();
                                }
                            }).setNegativeButton("No",null );

                            AlertDialog alert=builder.create();
                            alert.show();
                        }
                        else{
                            Toast.makeText(getActivity(), "New Password is too short !!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Current Password is null or wrong !!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Please fill in all the blanks !!",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });
        return  binding.getRoot();
    }
}