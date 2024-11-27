package school.sptech;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MapperJson extends FileProcessor{
    public List<RegisterFormat> map(InputStream inputStream) throws IOException {
        // Biblioteca Jackson:
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        return objectMapper.readValue(inputStream,
                new TypeReference<List<RegisterFormat>>(){}); // Este método indentifica que os dados recebidos no inputStream são do formato especificado
    }

    //  Essa classe não é necessária, o Jackson ja consegue setar um objeto Java automaticamente!
    @Override
    public RegisterFormat getData(String linha, String separador) {
        return null;
    }
}
