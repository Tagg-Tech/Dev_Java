package school.sptech;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvWriter {
    // Geração de arquivo caso não exista no bucket trusted
    public ByteArrayOutputStream writeCsv(List<RegisterFormat> registerFormats) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("idDados", "dataHora", "percCPU", "tempoInvativo", "percRAM", "percDisc", "usedDisc", "fkNotebook"));

        // Processar e escrever cada objeto no CSV em linha
        for (RegisterFormat registerFormat : registerFormats) {
            csvPrinter.printRecord(
                    registerFormat.getIdDados(),
                    registerFormat.getDataHora(),
                    registerFormat.getPercCPU(),
                    registerFormat.getTempoInativo(),
                    registerFormat.getPercRAM(),
                    registerFormat.getPercDisc(),
                    registerFormat.getUsedDisc(),
                    registerFormat.getFkNotebook()
            );
        }

        // Fechar o CSV para garantir que todos os dados sejam escritos
        csvPrinter.flush();
        writer.close();

        // Retornar o ByteArrayOutputStream que contém o CSV gerado
        return outputStream;
    }


    // Sobrecarga de méto-do para geração de arquivo caso exista no bucket trusted
    public ByteArrayOutputStream writeCsv(List<RegisterFormat> registerFormats, List<RegisterFormat> stocks1) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("idDados", "dataHora", "percCPU", "tempoInvativo", "percRAM", "percDisc", "usedDisc", "fkNotebook"));
        // Pegando último id gerado para mesclagem
        RegisterFormat lastObject = registerFormats.get(registerFormats.size() - 1);
        Integer lastId = lastObject.getIdDados();

        // Processar e escrever cada objeto no CSV em linha
        for (RegisterFormat registerFormat : registerFormats) {
            csvPrinter.printRecord(
                    registerFormat.getIdDados(),
                    registerFormat.getDataHora(),
                    registerFormat.getPercCPU(),
                    registerFormat.getTempoInativo(),
                    registerFormat.getPercRAM(),
                    registerFormat.getPercDisc(),
                    registerFormat.getUsedDisc(),
                    registerFormat.getFkNotebook()
            );
        }

        // Processar e escrever objetos de stocks1, incrementando o idDados
        for (RegisterFormat registerFormat : stocks1) {
            csvPrinter.printRecord(
                    registerFormat.getIdDados() + lastId,
                    registerFormat.getDataHora(),
                    registerFormat.getPercCPU(),
                    registerFormat.getTempoInativo(),
                    registerFormat.getPercRAM(),
                    registerFormat.getPercDisc(),
                    registerFormat.getUsedDisc(),
                    registerFormat.getFkNotebook()
            );
        }


        // Fechar o CSV para garantir que todos os dados sejam escritos
        csvPrinter.flush();
        writer.close();

        // Retornar o ByteArrayOutputStream que contém o CSV gerado
        return outputStream;
    }
}
