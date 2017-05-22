//package ua.com.gup.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.mongodb.core.convert.CustomConversions;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import ua.com.gup.domain.util.JSR310DateConverters;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableMongoRepositories(basePackages = "ua.com.gup.repository")
//public class MongoConfig {
//
//    @Bean
//    public CustomConversions customConversions() {
//        List<Converter<?, ?>> converters = new ArrayList<>();
//        converters.add(JSR310DateConverters.DateToZonedDateTimeConverter.INSTANCE);
//        converters.add(JSR310DateConverters.ZonedDateTimeToDateConverter.INSTANCE);
//        return new CustomConversions(converters);
//    }
//
//}
