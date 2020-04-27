package ch.lu.mygym.loginservice;

import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.ExerciseDTO;
import ch.lu.mygym.dtos.plain.SetDTO;
import ch.lu.mygym.dtos.plain.SupersetDTO;
import ch.lu.mygym.exerciseservice.DTOConverter;
import ch.lu.mygym.savesetservice.SetConverter;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import com.google.api.client.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.*;


@Controller
@RestController
@RequestMapping("/login-service")
public class LoginService {

    @CrossOrigin
    @PostMapping(value="/validate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String validateLogin(@RequestBody String tokenid) {
        System.out.println(tokenid);

        String tokenDecoded;
        try {
            tokenDecoded = URLDecoder.decode(tokenid, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
        String exerciseDecodedAsJsonString = tokenDecoded.substring(tokenDecoded.indexOf(":")+2, tokenDecoded.lastIndexOf("}")-1);
        System.out.println(exerciseDecodedAsJsonString);



        Gson jsonHandler = new Gson();

Token token = new Token();
token.setTokenid(exerciseDecodedAsJsonString);

        verifier(token.getTokenid());
        return null;
    }
    class Token {
        private String tokenid;

        public String getTokenid() {
            return tokenid;
        }

        public void setTokenid(String tokenid) {
            this.tokenid = tokenid;
        }
    }
    private void verifier(String token){
        FirebaseOptions options = null;


        try {
           // FileInputStream refreshToken = new FileInputStream("refreshToken.json");
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream refreshToken = classloader.getResourceAsStream("refreshToken.json");
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://workouttest2.firebaseio.com/")
                    .build();


        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        String uid = decodedToken.getUid();
        /*
        System.out.println(new DateTime(System.currentTimeMillis()));
        final JacksonFactory jacksonFactory = new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(UrlFetchTransport.getDefaultInstance(), jacksonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("965935905357-kfpgdeg58d28mb0quql39n6ioq059g4g.apps.googleusercontent.com"))
                //.setAudience(Collections.singletonList("osqJEGyNPtdhHgFpDIXY3kbUseF3"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

// (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = null;


        System.out.println(new DateTime(verifier.getExpirationTimeMilliseconds()));
        try {
            idToken = verifier.verify(token);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
        }
         */
    }
/*
    @CrossOrigin
    @GetMapping(value="/gettest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPropertyJSON(@RequestParam(defaultValue="") String exercise) {

        System.out.println(exercise);
        return "bla bal";
    }
    */
}
