package com.javathon.backend.configs;

import com.javathon.backend.security.Interceptors.MainInterceptor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class RestMvcConfig extends RepositoryRestMvcConfiguration {

    private final MainInterceptor mainInterceptor;

    public RestMvcConfig(ApplicationContext context, ObjectFactory<ConversionService> conversionService, MainInterceptor mainInterceptor) {
        super(context, conversionService);
        this.mainInterceptor = mainInterceptor;
    }

    //Добавляем интерсептор для перехвата и ранней инициализации куков
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mainInterceptor);
    }

}
