package ch.lu.mygym.workoutpreviewservice;

import ch.lu.mygym.dtos.entities.SavedWorkoutsEntity;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/workoutpreview-service")
public class WorkoutPreviewService {

    @Autowired
    SavedWorkoutsRepository savedWorkoutsRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value = "/get-workoutpreview-images-urls", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getImageUrls(@RequestParam(defaultValue = "") String userIdFromFrontend) {

        List<SavedWorkoutsEntity> entities = savedWorkoutsRepository.findByUserEntity_UseridOrderById(Integer.valueOf(userIdFromFrontend));
        return entities.stream().map(a -> a.getWorkoutEntity().getImageUrl()).limit(4).collect(Collectors.toList());
    }

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value = "/get-workoutpreview-images", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImagesFromAWSS3(@RequestParam(defaultValue = "") String url) {

        InputStream in = getImage(url);
        InputStreamResource inr = new InputStreamResource(in);
        return ResponseEntity.ok(inr);

    }

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value = "/get-exericse-video", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public ResponseEntity<InputStreamResource> getVideosFromAWSS3(@RequestParam(defaultValue = "") String url) {

        InputStream in = this.getVideo(url);
        InputStreamResource inr = new InputStreamResource(in);
        // return ResponseEntity.ok(inr);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory
                        .getMediaType(inr)
                        .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(inr);

    }

    private InputStream getImage(String url) {

        // todo connection string goes to external .yml file
        String bucketName = "elasticbeanstalk-eu-west-2-345269114307";
        String key = url;

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

            fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));

            return fullObject.getObjectContent().getDelegateStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStream getVideo(String url) {

        // todo connection string goes to external .yml file
        String bucketName = "elasticbeanstalk-eu-west-2-345269114307";
        String key = "resources/" + url;

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

            fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));

            return fullObject.getObjectContent().getDelegateStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
