package school.sptech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
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
            br.readLine();

            // Ler linha por linha
            while ((linha = br.readLine()) != null) {
                DataFormat dataFormat = getDataFormat(linha, separador);
                LocalDate dataAtual = LocalDate.parse(dataFormat.getDataHora().substring(0, 10));

                if( dataNow.isBefore(dataAtual) ){
                    percRam.add(dataFormat.getPercRAM());
                    percCPU.add(dataFormat.getPercCPU());
                    disco.add(dataFormat.getUsedDisc());
                }
                // Adicionar à lista
                dataFormats.add(dataFormat);
            }
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
        dataFormat.setDataHora(dados[1]);
        dataFormat.setPercCPU(Double.parseDouble(dados[2]));
        dataFormat.setPercRAM(Double.parseDouble(dados[4]));
        dataFormat.setUsedDisc(Long.parseLong(dados[6]));
        return dataFormat;
    }
}
