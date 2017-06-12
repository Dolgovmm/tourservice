package ru.dolgov.tourservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dolgov.tourservice.gisservice.MultiThreadService;
import ru.dolgov.tourservice.gisservice.MultiThreadServiceImpl;

/**
 * @author M. Dolgov
 *         10.06.2017.
 */
@Configuration
public class ContextConfiguration {

    @Bean
    public MultiThreadService threadService() {
        return new MultiThreadServiceImpl();
    }
}
