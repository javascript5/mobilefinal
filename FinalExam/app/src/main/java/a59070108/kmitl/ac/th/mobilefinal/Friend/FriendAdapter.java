package a59070108.kmitl.ac.th.mobilefinal.Friend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import a59070108.kmitl.ac.th.mobilefinal.FriendModel;
import a59070108.kmitl.ac.th.mobilefinal.R;

public class FriendAdapter extends ArrayAdapter<FriendModel> {
    private Context context;
    private ArrayList<FriendModel> friendModelArrayList = new ArrayList<>();

    public FriendAdapter(@NonNull Context context, int resource, ArrayList<FriendModel> friendModelArrayList) {
        super(context, resource);
        this.context =context;
        this.friendModelArrayList = friendModelArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View firendItem = LayoutInflater.from(context).inflate(
                R.layout.adapter_friend,
                parent,
                false
        );

        TextView id = firendItem.findViewById(R.id.id_friend_fragment);
        TextView name = firendItem.findViewById(R.id.name_friend_fragment);
        TextView email = firendItem.findViewById(R.id.email_friend_fragment);
        TextView phone = firendItem.findViewById(R.id.phone_friend_fragment);
        TextView website = firendItem.findViewById(R.id.website_friend_fragment);

        id.setText(friendModelArrayList.get(position).getId()+"");
        name.setText(friendModelArrayList.get(position).getName()+"");
        email.setText(friendModelArrayList.get(position).getEmail()+"");
        phone.setText(friendModelArrayList.get(position).getPhone()+"");
        website.setText(friendModelArrayList.get(position).getWebsite()+"");


        return super.getView(position, convertView, parent);
    }
}
