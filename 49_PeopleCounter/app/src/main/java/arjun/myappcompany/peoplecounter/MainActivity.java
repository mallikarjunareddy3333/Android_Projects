package arjun.myappcompany.peoplecounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import arjun.myappcompany.peoplecounter.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;

    int count = 0;

    public void plusOne(View view) {
        count++;
        mTextView.setText(Integer.toString(count));
    }

    public void reset(View view) {
        count = 0;
        mTextView.setText(Integer.toString(count));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = findViewById(R.id.textView);

    }
}