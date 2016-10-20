package name.msutherland.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClientBuilder;
import com.amazonaws.services.elasticbeanstalk.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

public class DeployApp {

    private final String myKeyPair = "SimpleTestKey";

    public static void main(String[] args) throws IOException {
        try {
            System.out.println("Runing\n");
            new DeployApp().deploy();

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

    public DeployApp(){
    }

    public void deploy(){

        AWSElasticBeanstalk bc = AWSElasticBeanstalkClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).withRegion(Regions.EU_WEST_1).build();
        CreateApplicationRequest createApplicationRequest = new CreateApplicationRequest();
        createApplicationRequest.setApplicationName("Soduko2");

        //bc.createApplication(createApplicationRequest);

        CreateApplicationVersionRequest createApplicationVersionRequest = new CreateApplicationVersionRequest();
        createApplicationVersionRequest.withSourceBundle(new S3Location("name.msutherland.soduko.test1","sodukoWar"));
        createApplicationVersionRequest.withApplicationName("Soduko2").withVersionLabel("1.0");
        //bc.createApplicationVersion(createApplicationVersionRequest);

/*        ListAvailableSolutionStacksResult result = bc.listAvailableSolutionStacks();
        for (String s:result.getSolutionStacks()
             ) {
            System.out.println(s);
        }*/
        CreateEnvironmentRequest createEnvironmentRequest = new CreateEnvironmentRequest("Soduko2","SodukoEnv")
                .withVersionLabel("1.0").withSolutionStackName("64bit Amazon Linux 2016.03 v2.2.0 running Tomcat 8 Java 8");
        bc.createEnvironment(createEnvironmentRequest);
        bc.shutdown();
    }
/*
    private void createKeyPair(AmazonEC2 amazonEC2Client){
        CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();

        createKeyPairRequest.withKeyName(myKeyPair);
        CreateKeyPairResult createKeyPairResult =
                amazonEC2Client.createKeyPair(createKeyPairRequest);
    }

    private void runInstance(){
        RunInstancesRequest runInstancesRequest =
                new RunInstancesRequest();

        runInstancesRequest.withImageId("ami-701b5b03")
                .withInstanceType("m1.small")
                .withMinCount(1)
                .withMaxCount(1)
                .withKeyName(myKeyPair)
                .withSecurityGroups("sg-f6d41490");
    }*/
}
