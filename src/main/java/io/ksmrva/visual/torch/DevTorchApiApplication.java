package io.ksmrva.visual.torch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({
        "classpath:${spring.profiles.active:dev}.properties"
})
public class DevTorchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevTorchApiApplication.class, args);
    }

}
