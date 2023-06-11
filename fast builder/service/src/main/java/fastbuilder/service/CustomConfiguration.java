package fastbuilder.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"fastbuilder.controller", "fastbuilder.app", "fastbuilder.services"})
public class CustomConfiguration {
    // Additional configuration if needed
}
