package school.sptech;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvWriter {

    // Geração de arquivo caso não exista no bucket trusted

    public ByteArrayOutputStream writeCsv1(RelatorioSemana relatorioSemana) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // Especificamente ByteArrayOutputStream
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("MediaDeCPU", "PicoDeCPU", "MediaDeRAM", "PicoDeRAM", "MediaDeDisco"));

        // Utilizado para transforma optionalDouble em double, assim evitando que na escrita do arquivo saia o optional double
        double valorPicoCpu = relatorioSemana.getPicoCpu().isPresent() ? relatorioSemana.getPicoCpu().getAsDouble() : 0.0;
        double valorPicoRam = relatorioSemana.getPicoRam().isPresent() ? relatorioSemana.getPicoRam().getAsDouble() : 0.0;

        // Escreve os dados do relatorio no CSV
        csvPrinter.printRecord(
                relatorioSemana.getMediaCpu(),
                valorPicoCpu,
                relatorioSemana.getMediaRam(),
                valorPicoRam,
                relatorioSemana.getMediaDisc()
        );

        System.out.println("CSV gerado com sucesso.");

        // Garantir que todos os dados sejam escritos e fechados
        csvPrinter.flush();
        writer.close();

        // Retornar o ByteArrayOutputStream que contém o CSV gerado
        return outputStream;
    }


}
