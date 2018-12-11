package a59070108.kmitl.ac.th.mobilefinal.Profile;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import a59070108.kmitl.ac.th.mobilefinal.DBHelper;
import a59070108.kmitl.ac.th.mobilefinal.Home.HomeFragment;
import a59070108.kmitl.ac.th.mobilefinal.Login.LoginFragment;
import a59070108.kmitl.ac.th.mobilefinal.R;
import a59070108.kmitl.ac.th.mobilefinal.TextFile.FileHelper;
import a59070108.kmitl.ac.th.mobilefinal.User;

public class ProfileSetupFragment extends android.support.v4.app.Fragment {
    private EditText userIdEditText, nameEditText, passwordEditText ,ageEditText, quoteEditText;
    private Button saveButton;
    private User user;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userIdEditText = getActivity().findViewById(R.id.user_id_profile_setup_fragment);
        nameEditText = getActivity().findViewById(R.id.name_profile_setup_fragment);
        passwordEditText = getActivity().findViewById(R.id.password_profile_setup_fragment);
        ageEditText = getActivity().findViewById(R.id.age_profile_setup_fragment);
        saveButton = getActivity().findViewById(R.id.save_button_profile_setup_fragment);
        quoteEditText = getActivity().findViewById(R.id.quote_profile_setup_fragment);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("59070108", getActivity().MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid", "null");
        if(!userId.equals("null")){
            DBHelper dbHelper = new DBHelper(getContext());
            user = dbHelper.getUser(userId);
            userIdEditText.setText(user.getUserId());
            nameEditText.setText(user.getName());
            passwordEditText.setText(user.getPassword());
            ageEditText.setText(user.getAge()+"");
            quoteEditText.setText(FileHelper.ReadFile(user.getUserId()));
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkEmptyField())
                    {
                        if(userIdEditText.getText().length() >= 6 || userIdEditText.getText().length() <= 12){
                            if(checkSpaceBar()){
                                if(checkAge()){
                                    if(checkPassword()){
                                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(getActivity(), new String[]{
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            },3);
                                            return;
                                        }

                                        //Create File
                                        FileHelper.saveToFile(user.getUserId(), quoteEditText.getText().toString());

                                        //Add Data To Database
                                        DBHelper dbHelper = new DBHelper(getContext());
                                        dbHelper.updateTime(getUserFromField());

                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new HomeFragment()).addToBackStack(null).commit();

                                    }else{
                                        Toast.makeText(getContext(),"รหัสผ่านต้องมากกว่า 6 ตัวอักษร",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(getContext(),"อายุต้องอยู่ในช่วง 10 ถึง 80",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getContext(),"ชื่อ-นามสกุล ต้องมีช่องว่างจากกัน",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getContext(),"User Id ต้องมีความยาว 6 - 12 ตัวอักษร",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getContext(),"กรุณากรอกข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else{
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit();
        }
    }

    private User getUserFromField(){
        String userid = userIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String name = nameEditText.getText().toString();
        int age = Integer.parseInt(ageEditText.getText().toString());

        User user = new User();
        user.setUserId(userid);
        user.setName(name);
        user.setAge(age);
        user.setPassword(password);

        return user;
    }

    private boolean checkEmptyField(){
        if(
                !userIdEditText.getText().toString().equals("") &&
                        !nameEditText.getText().toString().equals("") &&
                        !ageEditText.getText().toString().equals("") &&
                        !passwordEditText.getText().toString().equals("")
                ){
            return true;
        }

        return false;
    }
    private boolean checkPassword(){
        if(passwordEditText.getText().length() > 6){
            return true;
        }
        return false;
    }
    private boolean checkAge(){
        int age = Integer.parseInt(ageEditText.getText().toString());
        Log.i("RegisterFragment", age+"");
        if(age >= 10 && age <= 80){
            return true;
        }
        return false;
    }
    private boolean checkSpaceBar(){
        int countSpaceBar = 0;
        for(char chr : nameEditText.getText().toString().toCharArray()){

            if(chr == ' '){
                countSpaceBar++;
            }
        }

        if(countSpaceBar == 1){
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_setup, container, false);
    }
}
