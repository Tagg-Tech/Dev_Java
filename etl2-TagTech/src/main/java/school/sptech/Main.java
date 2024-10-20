package school.sptech;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.*;

public class Main implements RequestHandler<S3Event, String> {

    // Criação do cliente S3 para acessar os buckets
    private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

    // Bucket de destino para o CSV gerado
    private static final String DESTINATION_BUCKET = "bucket-client-tag-tech";
    @Override
    public String handleRequest(S3Event s3Event, Context context) {
        // Leitura do arquivo CSV do bucket de origem
        String sourceBucket = s3Event.getRecords().getFirst().getS3().getBucket().getName();
        String sourceKey = s3Event.getRecords().getFirst().getS3().getObject().getKey();
        InputStream s3InputStream = s3Client.getObject(sourceBucket, sourceKey).getObjectContent();


        Mapper mapper = new Mapper();
        // Conversão do CSV para uma lista de objetos Stock usando o Mapper
        RelatorioSemana relatorioSemana = null;
        try {
            relatorioSemana = mapper.map(s3InputStream);

            if(relatorioSemana == null){
                return "Não há dados a serem processados na semana!";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Geração do arquivo CSV a partir da lista de Stock usando o CsvWriter
        CsvWriter csvWriter = new CsvWriter();

        ByteArrayOutputStream csvOutputStream = null;
        try {
            csvOutputStream = csvWriter.writeCsv1(relatorioSemana);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Converte o ByteArrayOutputStream para InputStream para enviar ao bucket de destino
        InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

        // Envio do CSV para o bucket de destino
        s3Client.putObject(DESTINATION_BUCKET, "relatorioSemanal.csv", csvInputStream, null);

        return "Arquivo processado com sucesso";
    }
}