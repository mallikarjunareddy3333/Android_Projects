package arjun.myappcompany.roundorsquare;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import arjun.myappcompany.roundorsquare.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;

    public void determineRoundOrSquare(View view) {
        if ( getResources().getConfiguration().isScreenRound()) {
            Toast.makeText(this, "Device is Round!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Device is Square!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}