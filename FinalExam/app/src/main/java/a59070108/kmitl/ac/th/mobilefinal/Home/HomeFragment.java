package a59070108.kmitl.ac.th.mobilefinal.Home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import a59070108.kmitl.ac.th.mobilefinal.DBHelper;
import a59070108.kmitl.ac.th.mobilefinal.Login.LoginFragment;
import a59070108.kmitl.ac.th.mobilefinal.Profile.ProfileSetupFragment;
import a59070108.kmitl.ac.th.mobilefinal.R;
import a59070108.kmitl.ac.th.mobilefinal.TextFile.FileHelper;
import a59070108.kmitl.ac.th.mobilefinal.User;

public class HomeFragment extends Fragment {
    private  TextView welcomeText , descriptionText, homeButton;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         welcomeText = getView().findViewById(R.id.welcome_text_home_fragment);
         descriptionText = getView().findViewById(R.id.description_text_home_fragment);
        homeButton = getView().findViewById(R.id.profile_setup_button_home_fragment);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("59070108", getActivity().MODE_PRIVATE);
        String userId = sharedPreferences.getString("userid", "null");
        if(!userId.equals("null")){
            DBHelper dbHelper = new DBHelper(getContext());
            User user = dbHelper.getUser(userId);
            welcomeText.setText("Hello "+user.getName());
            FileHelper fileHelper = new FileHelper(getContext());
            descriptionText.setText(fileHelper.ReadFile(user.getUserId()));

            //GO TO PROFILE SETUP
            homeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new ProfileSetupFragment()).addToBackStack(null).commit();
                }
            });
        }else{
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).addToBackStack(null).commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
