package ch.lu.mygym.saveworkoutservice;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


@Controller
@CrossOrigin
@RequestMapping("/save-workout-service")
public class FilesController {

    @CrossOrigin
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

        InputStream inputStream = null;

        try {
            inputStream = new BufferedInputStream(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.saveImage(inputStream, file.getOriginalFilename());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                body(new ResponseMessage("message from backend saveSetServeice"));
    }

    private void saveImage(InputStream file, String fileName) {
        String bucketName = "elasticbeanstalk-eu-west-2-345269114307";

        String filePath = System.getProperty("user.dir") + "/.aws/credentials";
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider(filePath, "app-1-development");

        try {

            ClientConfiguration clientConfiguration = new ClientConfiguration();
            clientConfiguration.setConnectionTimeout(50000);
            clientConfiguration.setMaxConnections(500);
            clientConfiguration.setSocketTimeout(50000);
            clientConfiguration.setMaxErrorRetry(10);

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().
                    withCredentials(awsCredentialsProvider).
                    withClientConfiguration(clientConfiguration).
                    build();

            s3Client.putObject(bucketName, "resources/" + fileName, file, new ObjectMetadata());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
