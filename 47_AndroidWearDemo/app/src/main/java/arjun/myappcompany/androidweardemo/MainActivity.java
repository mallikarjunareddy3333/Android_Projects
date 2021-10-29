package arjun.myappcompany.androidweardemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import arjun.myappcompany.androidweardemo.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;

        if ( getResources().getConfiguration().isScreenRound()) {
            Log.i("Device", "Round!");
        } else {
            Log.i("Device", "Square!");
        }
    }
}