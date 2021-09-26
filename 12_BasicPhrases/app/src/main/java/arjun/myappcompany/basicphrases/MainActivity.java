package arjun.myappcompany.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void playPhrase(View view) {

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        Button btn = (Button) view;
        String tagName = btn.getTag().toString();

        Log.i("ButtonPressed", tagName);

        MediaPlayer mPlayer = MediaPlayer.create(this, getResources().getIdentifier(tagName, "raw", getPackageName()));
        mPlayer.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}