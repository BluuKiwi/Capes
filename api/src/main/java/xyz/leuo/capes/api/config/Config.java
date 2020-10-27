package xyz.leuo.capes.api.config;

import lombok.Data;

import java.io.*;
import java.util.Properties;

@Data
public class Config {

    private String uri, database;

    public Config() {
        File file = new File("config.properties");

        if(!file.exists()) {
            try{
                FileOutputStream output = new FileOutputStream(file);

                output.write("mongo-uri=<uri here>\n".getBytes());
                output.write("mongo-database=customcapes".getBytes());
                output.flush();
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(file);
            properties.load(input);

            this.uri = ((String) properties.get("mongo-uri"));
            this.database = ((String) properties.get("mongo-database"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
