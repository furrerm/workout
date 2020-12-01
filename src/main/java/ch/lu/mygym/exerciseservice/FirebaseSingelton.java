package ch.lu.mygym.exerciseservice;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseSingelton {

    public static void instanciateFirebase(FirebaseOptions options) {
        if(FirebaseApp.getApps().isEmpty()){
            new FirebaseSingelton(options);
        }
    }

    private FirebaseSingelton(FirebaseOptions options) {
        FirebaseApp.initializeApp(options);
    }
}
