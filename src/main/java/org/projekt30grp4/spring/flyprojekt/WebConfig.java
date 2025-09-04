package org.projekt30grp4.spring.flyprojekt;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.projekt30grp4.spring.flyprojekt")
public class WebConfig {
    // Optional: ViewResolver, MultipartResolver etc.
}
