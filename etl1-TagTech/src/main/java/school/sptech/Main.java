package school.sptech;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class Main implements RequestHandler<S3Event, String> {

    // Criação do cliente S3 para acessar os buckets
    private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

    // Bucket de destino para o CSV gerado
    private static final String DESTINATION_BUCKET = "bucket-trusted-tag-tech";

    @Override
    public String handleRequest(S3Event s3Event, Context context) {

        // Extraímos o nome do bucket de origem e a chave do arquivo CSV
        String sourceBucket = s3Event.getRecords().getFirst().getS3().getBucket().getName();
        String sourceKey = s3Event.getRecords().getFirst().getS3().getObject().getKey();

        // Verificando o nome do bucket
        System.out.println("sourceKey: " + sourceKey);


        try {
            // Geração do arquivo CSV a partir da lista de RegisterFormat usando o CsvWriter
            CsvWriter csvWriter = new CsvWriter();


            // Verificando existência de arquivo no bucket de destino
            boolean s3ObjExist = s3Client.doesObjectExist(DESTINATION_BUCKET, "allData.csv");
            List<RegisterFormat> registerFormats;
            InputStream s3InputStream = s3Client.getObject(sourceBucket, sourceKey).getObjectContent();

            if(s3ObjExist){
                System.out.println("Objeto existe no S3!");

                // Lista que armazenara os arquivos
                List<RegisterFormat> registerFormats1;

                InputStream s3InputStreamNew = s3Client.getObject(DESTINATION_BUCKET, "allData.csv").getObjectContent();
                // Verifica a extensão do arquivo para direcionar o tipo de escrita
                if(sourceKey.endsWith(".csv")){
                    System.out.println("MapperCsv tr");
                    MapperCsv mapperCsv = new MapperCsv();
                    registerFormats = mapperCsv.map(s3InputStream);
                    registerFormats1 = mapperCsv.map(s3InputStreamNew);
                } else {
                    System.out.println("MapperJson tr");
                    MapperJson mapperJson = new MapperJson();
                    MapperCsv mapperCsv = new MapperCsv();
                    registerFormats = mapperJson.map(s3InputStream);
                    registerFormats1 = mapperCsv.map(s3InputStreamNew);
                }

                // Removendo antigo arquivo para transformalo em novo
                s3Client.deleteObject(DESTINATION_BUCKET, "allData.csv");

                ByteArrayOutputStream csvOutputStream = csvWriter.writeCsv(registerFormats, registerFormats1);
                InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

                s3Client.putObject(DESTINATION_BUCKET, "allData.csv", csvInputStream, null);
            } else{
                System.out.println("Objeto não existe no S3!");
                // Verifica a extensão do arquivo para direcionar o tipo de escrita
                if(sourceKey.endsWith(".csv")){
                    MapperCsv mapperCsv = new MapperCsv();
                    registerFormats = mapperCsv.map(s3InputStream);
                } else {
                    MapperJson mapperJson = new MapperJson();
                    registerFormats = mapperJson.map(s3InputStream);
                }


                ByteArrayOutputStream csvOutputStream = csvWriter.writeCsv(registerFormats);

                // Converte o ByteArrayOutputStream para InputStream para enviar ao bucket de destino
                InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

                // Envio do CSV para o bucket de destino
                s3Client.putObject(DESTINATION_BUCKET, "allData.csv", csvInputStream, null);
            }
            return "Sucesso no processamento";
        } catch (Exception e) {
            // Tratamento de erros e log do contexto em caso de exceção
            context.getLogger().log("Erro: " + e.getMessage());
            return "Erro no processamento";
        }
    }
}