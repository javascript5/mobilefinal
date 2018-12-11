package a59070108.kmitl.ac.th.mobilefinal.Friend;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import a59070108.kmitl.ac.th.mobilefinal.FriendModel;
import a59070108.kmitl.ac.th.mobilefinal.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FriendFragment extends Fragment {
    private ArrayList<FriendModel> friendModelArrayList = new ArrayList<>();
    private ListView listView;
    private FriendAdapter friendAdapter;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getView().findViewById(R.id.list_view_friend_fragment);
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://jsonplaceholder.typicode.com/posts")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());
                    friendModelArrayList.clear();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        friendModelArrayList.add(new FriendModel(object.getInt("id"),
                                object.getString("name"),
                                object.getString("username"),
                                object.getString("email"),
                                object.getString("address")
                        ));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                if(getActivity() != null) {
                    friendAdapter = new FriendAdapter(
                            getContext(),
                            R.layout.adapter_friend,
                            friendModelArrayList
                    );
                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                    }
                });
                friendAdapter.notifyDataSetChanged();
                listView.setAdapter(friendAdapter);

            }


        }.execute();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
