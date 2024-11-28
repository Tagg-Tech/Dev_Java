package school.sptech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MapperJson extends FileProcessor{
    public static int tentativa = 0;
    public List<RegisterFormat> map(InputStream inputStream) throws IOException {
        // Verificando chamada de função:
        tentativa++;
        System.out.println("Tentativa: " + tentativa);

        // Biblioteca Jackson:
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("MapperJson acessado!");
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        //return objectMapper.readValue(inputStream,
        //        new TypeReference<List<RegisterFormat>>(){}); // Este método indentifica que os dados recebidos no inputStream são do formato especificado


        // Verificando conteúdo recebido:
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonContent = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }

        System.out.println("JSON recebido: " + jsonContent.toString());

        // Tentative de geração de arquivo:
        try {
            // A variável consumida pelo object mapper foi armazenada em memória na variável jsonContent
            return objectMapper.readValue(jsonContent.toString(), new TypeReference<List<RegisterFormat>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Erro ao processar JSON: " + e.getMessage());
            throw e;
        }

    }

    //  Essa classe não é necessária, o Jackson ja consegue setar um objeto Java automaticamente!
    @Override
    public RegisterFormat getData(String linha, String separador) {
        return null;
    }
}
