package school.sptech.Dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.DataBaseConnection.DatabaseConfiguration;
import school.sptech.Entity.Componente;

import java.util.List;

public class ComponenteDao {

    DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
    JdbcTemplate template = databaseConfiguration.getTemplate();

    public void buscarDados(){

        String sqlSelect = "SELECT * FROM registros";
        List<Componente> Componentes = template.query(sqlSelect, new BeanPropertyRowMapper<>(Componente.class));
        for (Componente componente : Componentes) {
            System.out.printf(
                    """
                            Id do registro: %d
                            Frequencia CPU: %.2f
                            Percentual Memória: %.2f %%
                            Percentual CPU: %.2f %%
                            Data e Hora do Registro: %s
                            --------------------------------
                            %n""",
                    componente.getIdRegistro(),
                    componente.getFrequenciaCPU(),
                  componente.getPercentualMemoria(),
                  componente.getPercentualCPU(),
                  componente.getDataHora()
    );
        }
    }
}
