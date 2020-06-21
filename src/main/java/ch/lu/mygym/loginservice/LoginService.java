package ch.lu.mygym.loginservice;

import ch.lu.mygym.dtos.entities.UserEntity;
import ch.lu.mygym.dtos.plain.UserDTO;
import ch.lu.mygym.exerciseservice.ExerciseRepository;
import ch.lu.mygym.exerciseservice.FirebaseSingelton;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@RestController
@RequestMapping("/login-service")
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    // @CrossOrigin(origins = "http://workoutfrontend-env.eba-tdwzai3v.eu-west-2.elasticbeanstalk.com")
    @CrossOrigin(origins = {"http://workoutfrontend-env.eba-tdwzai3v.eu-west-2.elasticbeanstalk.com", "http://localhost:4200"})
    @PostMapping(value = "/validate",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public UserDTO validateLogin(@RequestBody String tokenid) {
        System.out.println(tokenid);

        String tokenDecoded;
        try {
            tokenDecoded = URLDecoder.decode(tokenid, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }

        String exerciseDecodedAsJsonString = tokenDecoded.substring(tokenDecoded.indexOf(":") + 2, tokenDecoded.lastIndexOf("}") - 1);
        System.out.println(exerciseDecodedAsJsonString);


        Gson jsonHandler = new Gson();

        Token token = new Token();
        token.setTokenid(exerciseDecodedAsJsonString);

        FirebaseToken firebaseToken = verifier(token.getTokenid());

        String uid;

        UserDTO userDTO = new UserDTO();

        if (firebaseToken != null) {

            // Print user identifier
            uid = firebaseToken.getUid();

            userDTO.setUid(uid);
            userDTO.setEmail(firebaseToken.getEmail());
            userDTO.setEmailVerified(firebaseToken.isEmailVerified());
            userDTO.setIssuer(firebaseToken.getIssuer());
            userDTO.setName(firebaseToken.getName());
            userDTO.setPictureUrl(firebaseToken.getPicture());

            UserEntity userEntity = this.isUserAlreadySaved(uid);
            if(userEntity != null){
                userDTO.setFirstSignIn(false);
                userDTO.setId(userEntity.getUserid());
            } else {
                userEntity = this.saveResult(userDTO);
                userDTO.setFirstSignIn(true);
                userDTO.setId(userEntity.getUserid());
            }
        } else {
            System.out.println("Invalid ID token.");
        }
        return userDTO;
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

    private FirebaseToken verifier(String token) {

        FirebaseOptions options = null;

        try {
            InputStream refreshToken = new FileInputStream(System.getProperty("user.dir") + "/refreshToken.json");

            // ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            // InputStream refreshToken = classloader.getResourceAsStream(System.getProperty("user.dir") + "/refreshToken.json");
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://workouttest2.firebaseio.com/")
                    .build();


        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseSingelton.instanciateFirebase(options);
        // FirebaseApp.initializeApp(options);
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        return decodedToken;
    }
    public UserEntity isUserAlreadySaved(String uid){

        UserEntity result = userRepository.findByRemoteid(uid);
        return result;
    }

    public UserEntity saveResult(UserDTO userDTO){
            UserEntity userEntity = UserConverter.convertUserDTOToEntity(userDTO);
            return userRepository.save(userEntity);
    }
}
