package fr.loiodice_marco.fingerprintdemo;

import android.content.pm.PackageManager;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.KeyguardManager;
import android.widget.Toast;

import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.KeyGenerator;


public class MainActivity extends AppCompatActivity {
    
    private FingerprintManagerCompat fingerprintManagerCompat;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private KeyGenerator keyGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        
        fingerprintManagerCompat = FingerprintManagerCompat.from(getApplicationContext());
        
        if(!keyguardManager.isKeyguardSecure()){
            Toast.makeText(this, "Lock screen security not configure in Settings", Toast.LENGTH_LONG).show();
            return;
        }
        
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Fingerprint authentification permission not enabled", Toast.LENGTH_LONG).show();
            return;
        }

        if (!fingerprintManagerCompat.hasEnrolledFingerprints()){
            Toast.makeText(this, "Register at least one fingerprint in Settings", Toast.LENGTH_LONG).show();
            return;
        }

    }

    protected void generateKey(){
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e){
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }
    }
}