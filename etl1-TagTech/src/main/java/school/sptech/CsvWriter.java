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
                    registerFormat.getPercRAM(),
                    registerFormat.getQtdRAM(),
                    registerFormat.getUsedDisc(),
                    registerFormat.getPercDisc(),
                    registerFormat.getPercCPU(),
                    registerFormat.getTempoInativo(),
                    registerFormat.getDataHora(),
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
    public ByteArrayOutputStream writeCsv(List<RegisterFormat> registerFormats, List<RegisterFormat> registerFormats1) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("idDados", "percentualMemoria", "gigaBytesMemoria", "qtdUtilizadaDisco", "percentualDisco", "percentualCPU", "frequenciaCPU", "dataHora", "fkMaquina"));
        // Pegando último 'id' gerado para mesclagem
        RegisterFormat lastObject = registerFormats.get(registerFormats.size() - 1);
        Integer lastId = lastObject.getIdDados();

        // Processar e escrever cada objeto no CSV em linha
        for (RegisterFormat registerFormat : registerFormats) {
            csvPrinter.printRecord(
                    registerFormat.getIdDados(),
                    registerFormat.getPercRAM(),
                    registerFormat.getQtdRAM(),
                    registerFormat.getUsedDisc(),
                    registerFormat.getPercDisc(),
                    registerFormat.getPercCPU(),
                    registerFormat.getTempoInativo(),
                    registerFormat.getDataHora(),
                    registerFormat.getFkNotebook()
            );
        }

        // Processar e escrever objetos de stocks1, incrementando o idDados
        for (RegisterFormat registerFormat : registerFormats1) {
            csvPrinter.printRecord(
                    registerFormat.getIdDados() + lastId,
                    registerFormat.getPercRAM(),
                    registerFormat.getQtdRAM(),
                    registerFormat.getUsedDisc(),
                    registerFormat.getPercDisc(),
                    registerFormat.getPercCPU(),
                    registerFormat.getTempoInativo(),
                    registerFormat.getDataHora(),
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
