package fr.loiodice_marco.fingerprintdemo;

import android.app.KeyguardManager;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompatApi23;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FingerprintManagerCompatApi23 fingerprintManagerCompatApi23;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        fingerprintManagerCompatApi23 = (FingerprintManagerCompatApi23) getSystemService(FINGERPRINT_SERVICE);
    }
}