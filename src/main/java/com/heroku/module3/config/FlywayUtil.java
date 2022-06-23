package com.heroku.module3.config;

import org.flywaydb.core.Flyway;

public class FlywayUtil {
    public static void migrate() {
        DBCredentials dbCredentials = DBCredentials.getCredentials();
        Flyway flyway = Flyway.configure()
                .dataSource(dbCredentials.getDB_URL(), dbCredentials.getDB_USER(), dbCredentials.getDB_PASSWORD())
                .baselineOnMigrate(true)
                .locations("db/migration")
                .load();
        flyway.clean();
        flyway.migrate();
    }
}
