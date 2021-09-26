package arjun.myappcompany.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void onClickFunction(View view) {
        TextView usernameEditText = (TextView) findViewById(R.id.usernameEditText);
        TextView passwordEditText = (TextView) findViewById(R.id.passwordEditText);

        Log.i( "Username: ", usernameEditText.getText().toString());
        Log.i( "Password: ", passwordEditText.getText().toString());

        Toast.makeText( this, "Hi there!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}