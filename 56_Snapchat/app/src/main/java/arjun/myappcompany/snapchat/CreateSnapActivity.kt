package arjun.myappcompany.snapchat

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.*
import com.google.firebase.storage.UploadTask




class CreateSnapActivity : AppCompatActivity() {

    var createSnapImageView: ImageView? = null
    var messageEditText: EditText? = null

    val imageName = UUID.randomUUID().toString() + ".jpg"

    var imagePickActivity:ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_snap)

        createSnapImageView = findViewById(R.id.createSnapImageView)
        messageEditText = findViewById(R.id.messageEditText)

        imagePickActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            val selectedImage = data!!.data
            if (result.resultCode == RESULT_OK && data != null) {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                    createSnapImageView?.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto()
            }
        }
    }

    fun getPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickActivity?.launch(intent)
    }

    fun chooseImageClicked(view: View) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        } else {
            getPhoto()
        }
    }

    fun nextClicked(view: View) {
        // Get the data from an ImageView as bytes
        createSnapImageView?.isDrawingCacheEnabled = true
        createSnapImageView?.buildDrawingCache()
        val bitmap = (createSnapImageView?.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val filePath: StorageReference = FirebaseStorage.getInstance().getReference().child("/images").child(imageName)
        var uploadTask = filePath.putBytes(data)

        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            Toast.makeText(this,"UploadFailed", Toast.LENGTH_SHORT).show()
        }.addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot?> {
            filePath.downloadUrl.addOnSuccessListener(
                OnSuccessListener<Any> { uri ->
                    Log.d("URL", "onSuccess: uri= $uri")

                    val intent = Intent(this, ChooseUserActivity::class.java)
                    intent.putExtra("imageURL", "$uri")
                    intent.putExtra("imageName", imageName)
                    intent.putExtra("message", messageEditText?.text.toString())
                    startActivity(intent)
                })
        })

    }
}