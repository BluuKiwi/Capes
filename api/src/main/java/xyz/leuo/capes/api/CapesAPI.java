package xyz.leuo.capes.api;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import xyz.leuo.capes.api.config.Config;
import xyz.leuo.capes.shared.capes.CapeManager;
import xyz.leuo.capes.shared.mongo.MongoManager;
import xyz.leuo.capes.shared.profiles.ProfileManager;

@SpringBootApplication
@EnableScheduling
@RestController
public class CapesAPI{

    public static CapesAPI instance;

    private @Getter Config config;

    public CapesAPI() {
        this.config = new Config();

        new CapeManager();
        new ProfileManager();
        new MongoManager(config.getUri(), config.getDatabase());
    }

    public static void main(String[] args) {
        SpringApplication.run(CapesAPI.class, args);
        instance = new CapesAPI();
    }
}
