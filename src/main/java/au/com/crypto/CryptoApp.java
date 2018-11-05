package au.com.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;


@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = {"au.com.crypto.repository"})
@Configuration
@ComponentScan(basePackages = { "au.com.crypto.*"})
public class CryptoApp extends SpringBootServletInitializer{

    @Value("${mongo.db.name}")
    private String dbName;

    @Value("${mongo.host.name}")
    private String hostName;

    @Value("${mongo.port}")
    private Integer dbPort;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }

   @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), dbName);
        return mongoTemplate;
    }

    @Bean
    public MongoClient mongoClient() throws IOException{
        return new MongoClient(new ServerAddress(hostName,dbPort));
    }


    @Bean
    public Mongobee mongobee() {
        Mongobee mongobee = new Mongobee();
        mongobee.setDbName(dbName);
        mongobee.setChangeLogsScanPackage("au.com.crypto.flyway");
        return mongobee;
    }
    public static void main(String[] args) {
        SpringApplication.run(CryptoApp.class, args);
    }
}
