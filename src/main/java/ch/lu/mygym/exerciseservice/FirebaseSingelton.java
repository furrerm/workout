package ch.lu.mygym.exerciseservice;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseSingelton {

    private static FirebaseApp firebaseInstance;

    public static void instanciateFirebase(FirebaseOptions options) {
        System.out.println(firebaseInstance);

        if(FirebaseApp.getApps().isEmpty()){
            new FirebaseSingelton(options);
        }
    }

    private FirebaseSingelton(FirebaseOptions options) {
        FirebaseApp.initializeApp(options);
    }
}
