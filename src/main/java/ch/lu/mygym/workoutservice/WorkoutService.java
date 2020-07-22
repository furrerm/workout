package ch.lu.mygym.workoutservice;


import ch.lu.mygym.dtos.entities.UserEntity;
import ch.lu.mygym.dtos.plain.ExerciseGroupDTO;

import ch.lu.mygym.dtos.plain.UserDTO;
import ch.lu.mygym.dtos.plain.WorkoutDTO;
import ch.lu.mygym.loginservice.UserRepository;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.google.gson.Gson;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/workout-service")
public class WorkoutService {



    @Autowired
    private UserRepository userRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @PostMapping(value = "/get-workouts",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Iterable<WorkoutDTO> getPropertyJSON(@RequestBody UserDTO user) {

        System.out.println("check");


        UserEntity userEntity = userRepository.findById(user.getId());
        List<WorkoutDTO> workoutDTOs = WorkoutConverter.convertWorkoutEntitiesToDTO(userEntity.getSavedWorkoutEntities());

        return workoutDTOs;
    }

    @CrossOrigin
    @RequestMapping(value = "/get-workout-images", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response) throws IOException {
        InputStream in = getImage();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    private InputStream getImage() {

        // todo connection string goes to external .yml file
        String bucketName = "elasticbeanstalk-eu-west-2-345269114307";
        String key = "resources/testImage.jpeg";

        String filePath = System.getProperty("user.dir") + "/webapps/ROOT/.aws/credentials";
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider(filePath, "app-1-development");

        S3Object fullObject;
        try {

            ClientConfiguration clientConfiguration1 = new ClientConfiguration();
            clientConfiguration1.setConnectionTimeout(50000);
            clientConfiguration1.setMaxConnections(500);
            clientConfiguration1.setSocketTimeout(50000);
            clientConfiguration1.setMaxErrorRetry(10);

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(awsCredentialsProvider)
                    .withClientConfiguration(clientConfiguration1).build();

            // Get an object and print its contents.
            System.out.println("Downloading an object");
            fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));

            System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
            System.out.println("Content: ");
            //displayTextInputStream(fullObject.getObjectContent());
            return fullObject.getObjectContent().getDelegateStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
/*
    private static void displayTextInputStream(InputStream input) throws IOException {
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }

 */

}
/*
class UserId {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

 */
