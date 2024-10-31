package school.sptech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public List<RegisterFormat> map(InputStream inputStream) throws IOException {
        List<RegisterFormat> registerFormats = new ArrayList<>();
        String linha;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Pular o cabeçalho
            br.readLine();

            // Ler linha por linha
            while ((linha = br.readLine()) != null) {
                RegisterFormat registerFormat = getStock(linha, separador);

                // Adicionar à lista
                registerFormats.add(registerFormat);
            }
        }

        return registerFormats;
    }

    private static RegisterFormat getStock(String linha, String separador) {
        String[] dados = linha.split(separador);

        // CONSULTAR CSV PARA ALTERAR IDENTIFICAÇÃO DE CAMPO!!!!

        // Mapear os dados para o objeto registerFormat
        RegisterFormat registerFormat = new RegisterFormat();
        registerFormat.setIdDados(Integer.parseInt(dados[0]));
        registerFormat.setPercRAM(Double.parseDouble(dados[1]));
        registerFormat.setQtdRAM(Double.parseDouble(dados[2]));
        registerFormat.setUsedDisc(Long.parseLong(dados[3]));
        registerFormat.setPercDisc(Double.parseDouble(dados[4]));
        registerFormat.setPercCPU(Double.parseDouble(dados[5]));
        registerFormat.setTempoInativo(Double.parseDouble(dados[6]));
        registerFormat.setDataHora(dados[7]);
        registerFormat.setFkNotebook(Integer.parseInt(dados[8]));
        return registerFormat;
    }
}
