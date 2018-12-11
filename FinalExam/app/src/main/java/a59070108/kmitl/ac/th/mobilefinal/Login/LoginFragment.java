package a59070108.kmitl.ac.th.mobilefinal.Login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import a59070108.kmitl.ac.th.mobilefinal.DBHelper;
import a59070108.kmitl.ac.th.mobilefinal.R;
import a59070108.kmitl.ac.th.mobilefinal.Rgister.RegisterFragment;
import a59070108.kmitl.ac.th.mobilefinal.User;

public class LoginFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView logo = getView().findViewById(R.id.image_login_fragment);
        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/a/ab/Android_O_Preview_Logo.png").into(logo);

        final EditText userIdEditText = getView().findViewById(R.id.user_id_login_fragment);
        final EditText passwordEditText = getView().findViewById(R.id.password_login_fragment);
        Button loginButton = getView().findViewById(R.id.login_button_login_fragment);
        TextView registerButton = getView().findViewById(R.id.register_button_login_fragment);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
            }
        });
        final DBHelper dbHelper = new DBHelper(getContext());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = dbHelper.getUser(userIdEditText.getText().toString());
                if(
                        !userIdEditText.getText().toString().equals("")&&
                        !passwordEditText.getText().toString().equals("")
                        ) {
                    if (passwordEditText.getText().toString().equals(user.getPassword())) {
                        Toast.makeText(getContext(),"Login สำเร็จ",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(),"User Id หรือ Password ไม่ถูกต้อง",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(),"กรุณากรอกข้อมูลให้ครบถ้วน",Toast.LENGTH_SHORT).show();
                }
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
