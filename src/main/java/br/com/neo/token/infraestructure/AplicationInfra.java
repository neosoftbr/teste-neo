package br.com.neo.token.infraestructure;

import org.springframework.boot.SpringApplication;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AplicationInfra extends SpringApplication {

    private AplicationInfra(){
        throw new IllegalStateException("Utility class");
    }


    public static void run(Class mainClass){

        String propertiesLocationArg = "spring.config.location";
        String propertiesLocation = System.getProperty(propertiesLocationArg);
        if (propertiesLocation == null){
            String localProperties = System.getProperty("user.dir") + "/configmap/local/application.properties";
            String propArg = String.format("--%s=file:%s", propertiesLocationArg, localProperties);
            SpringApplication.run(mainClass, propArg);
            
            log.warn(String.format("Arquivo de propiedades não parametrizado em '%s'.", propertiesLocationArg));
            log.info(String.format("Usando o arquivo de propiedades: '%s'.", localProperties));
        } else {
            SpringApplication.run(mainClass);
            log.info(String.format("Utilizando arquivo de propiedades parametrizado em '%s' ('%s').", propertiesLocationArg, propertiesLocation));
        }
    }
}
