package ma.enset.supplierservice;
import ma.enset.supplierservice.entities.Supplier;
import ma.enset.supplierservice.repositories.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import java.util.stream.Stream;
@SpringBootApplication
public class SupplierServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplierServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner lineRunner(SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration){
        return args -> {
            //ajouter l'id au format json
            restConfiguration.exposeIdsFor(Supplier.class);
            Stream.of("JBOSS","ORACLE","IBM").forEach(n->{
                supplierRepository.save(new Supplier(null,n,n+"@"+n.toLowerCase()+".com"));
            });
        };
    }
}
