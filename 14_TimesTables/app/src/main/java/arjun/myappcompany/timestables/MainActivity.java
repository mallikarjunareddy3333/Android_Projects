package arjun.myappcompany.timestables;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public void createTimeTable(int number) {

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ListView myListView = findViewById(R.id.myListView);

        for (int i = 1; i <=10; i++) {
            numbers.add(number*i);
        }

        ArrayAdapter<Integer> timesTableAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, numbers);
        myListView.setAdapter(timesTableAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar mySeekBar = findViewById(R.id.seekBar);
        mySeekBar.setProgress(1);
        mySeekBar.setMax(20);

        createTimeTable(1);

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0) {
                    i = 1;
                    seekBar.setProgress(1);
                }
                createTimeTable(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}