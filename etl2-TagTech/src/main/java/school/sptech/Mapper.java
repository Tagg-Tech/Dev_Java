package school.sptech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Mapper {
    public RelatorioSemana map(InputStream inputStream) throws IOException {
        List<DataFormat> dataFormats = new ArrayList<>();

        List<Double> percCPU = new ArrayList<>();
        List<Double> percRam = new ArrayList<>();
        List<Long> disco = new ArrayList<>();

        LocalDate dataNow = LocalDate.now().minusDays(7);
        String linha;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Pular o cabeçalho
            // System.out.println(br.readLine());
            String header = br.readLine();
            System.out.println("Cabeçalho: " + header);

            // Ler linha por linha
            while ((linha = br.readLine()) != null) {
                DataFormat dataFormat = getDataFormat(linha, separador);
                LocalDate dataAtual = parseDate(dataFormat.getDataHora()).toLocalDate();

                if(dataAtual == null){
                    System.out.println("Problema em conversão de data");
                }

                if( dataNow.isBefore(dataAtual) ){
                    percRam.add(dataFormat.getPercRAM());
                    percCPU.add(dataFormat.getPercCPU());
                    disco.add(dataFormat.getUsedDisc());
                }
                // Adicionar à lista
                dataFormats.add(dataFormat);
            }

            // Verificando se há dados no arquivo de captura
            if(percRam.isEmpty()){ return null; }
            System.out.println(percRam);
        }

        // Coletando métricas
        int divisor = percRam.size();
        System.out.println("Quantidade elementos lista" + divisor);
        Double mediaRam = (percRam.stream().mapToDouble(Double::doubleValue).sum()) / divisor;

        OptionalDouble picoRam = percRam.stream().mapToDouble(Double::doubleValue).max();

        double mediaCpu = (percCPU.stream().mapToDouble(Double::doubleValue).sum()) / divisor;
        OptionalDouble picoCpu = percCPU.stream().mapToDouble(Double::doubleValue).max();

        long mediaDisc = (disco.stream().mapToLong(Long::longValue).sum()) / divisor;

        // Montando objeto para escrita
        RelatorioSemana relatorioSemana = new RelatorioSemana(picoCpu, mediaCpu, picoRam, mediaRam, mediaDisc);

//        System.out.println(relatorioSemana.getMediaRam().toString());
        System.out.println("Relatório da semana\n\n" + relatorioSemana.toString());
        return relatorioSemana;
    }



    private static DataFormat getDataFormat(String linha, String separador) {
        String[] dados = linha.split(separador);

        // Mapear os dados para o objeto DataFormat
        DataFormat dataFormat = new DataFormat();
        dataFormat.setDataHora(dados[7]);
        dataFormat.setPercCPU(Double.parseDouble(dados[5]));
        dataFormat.setPercRAM(Double.parseDouble(dados[1]));
        System.out.println("Tipo de variável: " + dados[3].describeConstable());
        dataFormat.setUsedDisc(Long.parseLong(dados[3]));
        return dataFormat;
    }


    // Interpreta os dois tipos de data: ISO8601 e padrão
    private static LocalDateTime parseDate(String data){
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try{
            return LocalDateTime.parse(data);
        } catch (DateTimeException e1) { // exception 1
            try{
                return LocalDateTime.parse(data, formatoData);
            } catch (DateTimeException e2){
                return null;
            }
        }
    }
}
