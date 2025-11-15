pacpackage com.ozcelik05.tikapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    
    private static final int PERMISSION_REQUEST_CODE = 100;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        checkPermissions();
        setupButtons();
    }
    
    private void checkPermissions() {
        String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION
        };
        
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }
    
    private void setupButtons() {
        Button btnChat = findViewById(R.id.btnChat);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnVideoCall = findViewById(R.id.btnVideoCall);
        Button btnGallery = findViewById(R.id.btnGallery);
        Button btnCamera = findViewById(R.id.btnCamera);
        Button btnLocation = findViewById(R.id.btnLocation);
        Button btnContacts = findViewById(R.id.btnContacts);
        
        btnChat.setOnClickListener(v -> startActivity(new Intent(this, ChatActivity.class)));
        btnCall.setOnClickListener(v -> makePhoneCall());
        btnVideoCall.setOnClickListener(v -> startVideoCall());
        btnGallery.setOnClickListener(v -> openGallery());
        btnCamera.setOnClickListener(v -> openCamera());
        btnLocation.setOnClickListener(v -> shareLocation());
        btnContacts.setOnClickListener(v -> openContacts());
    }
    
    private void makePhoneCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:123456789"));
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        }
    }
    
    private void startVideoCall() {
        // Video call logic
        Intent videoIntent = new Intent(this, VideoCallActivity.class);
        startActivity(videoIntent);
    }
    
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/* video/*");
        startActivityForResult(galleryIntent, 1);
    }
    
    private void openCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 2);
    }
    
    private void shareLocation() {
        Intent locationIntent = new Intent(this, LocationActivity.class);
        startActivity(locationIntent);
    }
    
    private void openContacts() {
        Intent contactsIntent = new Intent(this, ContactsActivity.class);
        startActivity(contactsIntent);
    }
}kage com.ozcelik05.chspro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("CHS Pro V4 Çalışıyor!");
        setContentView(textView);
    }
}
