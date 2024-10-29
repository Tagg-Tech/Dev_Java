package school.sptech;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.InputStream;
import java.util.List;

public class FindObject {
    public InputStream isPresent (List<S3ObjectSummary> objects, AmazonS3 client, String buckName){
        final String archName = "allData.csv";

        for (S3ObjectSummary objectSummary : objects){
            String chaveObjeto = objectSummary.getKey();

            if(chaveObjeto.endsWith("/" + archName) || chaveObjeto.equals(archName)){
                return client.getObject(buckName, chaveObjeto).getObjectContent();
            }
        }


        return null;
    }
}
