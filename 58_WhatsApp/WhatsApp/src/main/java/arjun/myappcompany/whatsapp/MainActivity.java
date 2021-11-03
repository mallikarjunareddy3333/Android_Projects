/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package arjun.myappcompany.whatsapp;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

  Boolean loginModeActive = false;

  public void redirectIfLoggedIn() {

    if ( ParseUser.getCurrentUser() != null ) {

      Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
      startActivity(intent);

    }

  }

  public void toggleLoginMode(View view) {

    Button loginSignupButton = findViewById(R.id.loginSignupButton);
    TextView toggleLoginModeTextView = findViewById(R.id.toggleLoginModeTextView);

    if (loginModeActive) {
      loginModeActive = false;
      loginSignupButton.setText("Sign up");
      toggleLoginModeTextView.setText("Or, login");
    } else {
      loginModeActive = true;
      loginSignupButton.setText("Log In");
      toggleLoginModeTextView.setText("Or, sign up");
    }
  }

  public void signupLogin(View view) {

    EditText usernameEditText = findViewById(R.id.usernameEditText);
    EditText passwordEditText = findViewById(R.id.passwordEditText);

    if ( loginModeActive ) {

      ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
          if (e == null) {
            Log.i("Info", "user logged in!");
            redirectIfLoggedIn();
          } else {
            String errMsg = "";
            if (passwordEditText.getText().toString().trim() == "") {
              errMsg = "Password can't be empty";
            } else {
              errMsg = e.getMessage();
            }

            Toast.makeText(MainActivity.this, errMsg, Toast.LENGTH_SHORT).show();
          }
        }
      });

    } else {
      ParseUser user = new ParseUser();

      user.setUsername(usernameEditText.getText().toString());
      user.setPassword(passwordEditText.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null) {
            Log.i("Info", "user signed up!");
            redirectIfLoggedIn();
          } else {
            String errMsg = "";
            if (passwordEditText.getText().toString().trim() == "") {
              errMsg = "Password can't be empty";
            } else {
              errMsg = e.getMessage();
            }

            Toast.makeText(MainActivity.this, errMsg, Toast.LENGTH_SHORT).show();
          }
        }
      });
    }

  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setTitle("Whatsapp login");

    redirectIfLoggedIn();

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}