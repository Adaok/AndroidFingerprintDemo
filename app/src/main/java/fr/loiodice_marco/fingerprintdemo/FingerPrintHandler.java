package fr.loiodice_marco.fingerprintdemo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.widget.Toast;


public class FingerPrintHandler extends FingerprintManagerCompat.AuthenticationCallback {

    private CancellationSignal cancellationSignal;
    private Context appContext;

    public FingerPrintHandler(Context context){
        appContext = context;
    }

    public void startAuth(FingerprintManagerCompat managerCompat, FingerprintManagerCompat.CryptoObject cryptoObject){
        cancellationSignal = new CancellationSignal();

        if (ActivityCompat.checkSelfPermission(appContext, android.Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        managerCompat.authenticate(cryptoObject, 0, cancellationSignal, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(appContext, "Authentification error\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(appContext, "Authentification help\n" + helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(appContext, "Authentification failed.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
        Toast.makeText(appContext, "Authentification succeeded.", Toast.LENGTH_LONG).show();
    }


}