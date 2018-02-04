package cn.gukeer.common.db;

import org.flywaydb.core.Flyway;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by conn on 2016/8/2.
 */
public class DBMigrate {

    public static void main(String args[]) {

        try {

            String filename = DBMigrate.class.getResource("/db.properties").getPath();
            File file = new File(filename);
            Properties props = new Properties();
            InputStream in = new FileInputStream(file);
            props.load(in);
            String url = props.getProperty("jdbc.url").trim();
            String username = props.getProperty("jdbc.username").trim();
            String password = props.getProperty("jdbc.password").trim();
            String scriptPath = props.getProperty("jdbc.script").trim();
            String dbSchema = props.getProperty("jdbc.schema").trim();

            Flyway flyway = new Flyway();
            flyway.setDataSource(url, username, password);

            flyway.setSchemas(dbSchema);
            flyway.setTable("db_versions");
            flyway.setLocations(scriptPath);
            flyway.setEncoding("UTF-8");
            flyway.setPlaceholderPrefix("#(");
            flyway.setPlaceholderSuffix(")");
            flyway.repair();
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
