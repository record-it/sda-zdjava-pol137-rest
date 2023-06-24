package pl.sda.restspringbooks.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("app")
@Component
public class AppConfiguration {
    //@Value("${app.default-page-size}")
    public int defaultPageSize;

   // @Value("${app.default-page-number}")
    public int defaultPageNumber;
}
