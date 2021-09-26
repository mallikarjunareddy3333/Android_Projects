package arjun.myappcompany.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view) {
        EditText currencyValue = (EditText) findViewById(R.id.currencyEditText);
        double poundValue = Double.parseDouble(currencyValue.getText().toString());
        double dollar = 1.3 * poundValue;
        Toast.makeText(this, "£" + poundValue + " is $" + String.format("%.2f", dollar), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}