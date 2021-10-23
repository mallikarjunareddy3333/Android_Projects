package arjun.myappcompany.photoimportdemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.security.identity.ResultData;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        ActivityResultLauncher<Intent> imagePickActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        Uri selectedImage = data.getData();
                        if ( result.getResultCode() == Activity.RESULT_OK && data != null ) {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                                ImageView imageView = findViewById(R.id.imageView);
                                imageView.setImageBitmap(bitmap);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        imagePickActivity.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            getPhoto();
        }
    }
}