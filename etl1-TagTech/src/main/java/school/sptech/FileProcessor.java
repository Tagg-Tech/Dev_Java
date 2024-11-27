package school.sptech;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class FileProcessor {
    abstract List<RegisterFormat> map(InputStream inputStream) throws IOException;
    abstract RegisterFormat getData(String linha, String separador);
}
