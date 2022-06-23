package com.heroku.module3.config;

import org.flywaydb.core.Flyway;

public class FlywayUtil {
    public static void migrate() {
        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "root";
        Flyway flyway = Flyway.configure()
                .dataSource(dbUrl, username, password)
                .baselineOnMigrate(true)
                .locations("db/migration")
//                .loadDefaultConfigurationFiles()     //default file name 'flyway.conf'
                .load();
        flyway.clean();
        flyway.migrate();
    }
}
