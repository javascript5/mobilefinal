package a59070108.kmitl.ac.th.mobilefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import a59070108.kmitl.ac.th.mobilefinal.Login.LoginFragment;
import a59070108.kmitl.ac.th.mobilefinal.Rgister.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new LoginFragment())
                    .commit();
        }
    }
}
