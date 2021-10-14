package arjun.myappcompany.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("arjun.myappcompany.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Prasad");
        friends.add("Mani");
        friends.add("Kuppaiah");
        friends.add("Ramesh");

//        try {
//            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();
//            Log.i("Friends", ObjectSerializer.serialize(friends));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ArrayList<String> newFriends = new ArrayList<String>();

        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ""));
            Log.i("New friends", newFriends.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}