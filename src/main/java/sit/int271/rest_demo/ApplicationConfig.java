package sit.int271.rest_demo;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper(); // สั่งให้ Spring สร้าง Object ModelMapper เตรียมไว้ให้
    }
}