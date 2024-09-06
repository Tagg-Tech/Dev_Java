package school.sptech.DataBaseConnection;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseConfiguration {
    private JdbcTemplate template;

    public DatabaseConfiguration() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testeAutomacao");
        dataSource.setUsername("enzo");
        dataSource.setPassword("Ensunel@2006");
        this.template = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }
}
