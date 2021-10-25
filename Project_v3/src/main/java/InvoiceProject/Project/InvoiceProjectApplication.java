package InvoiceProject.Project;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;     
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import InvoiceProject.Project.domain.TypeRepository;
import InvoiceProject.Project.domain.Invoice;
import InvoiceProject.Project.domain.InvoiceRepository;
import InvoiceProject.Project.domain.UserRepository;
import InvoiceProject.Project.domain.User;
import InvoiceProject.Project.domain.Type;






@SpringBootApplication
public class InvoiceProjectApplication {
	
	


	public static void main(String[] args) {
		SpringApplication.run(InvoiceProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TypeRepository repository1, InvoiceRepository repository, UserRepository upository) {
	return (args) -> {
		
		repository1.save(new Type("No Type"));
		repository1.save(new Type("Business"));
		repository1.save(new Type("Sales"));
		repository1.save(new Type("HR"));
		repository1.save(new Type("Office Management"));
		repository1.save(new Type("IT"));
		
		repository.save(new Invoice(1, "Moikka Oy", "1234567", new Date(2021-12-12), new Date(2021-12-16), 10.50,
			repository1.findByName("IT").get(0)));
		
		upository.save(new User("riku", "$2a$10$KaLs67SscV5FeBSfEL0uzOWrYOSn8mIjHDsvOz92qUojbbF8e5Teq", "USER"));
		upository.save(new User("admin", "$2a$10$.hNvlY5bSglHlmVzQxHOTuCFkWHP.xvNUMLGTHchpNroK7VFHmq/q", "ADMIN"));
		
		
		
	
	};
	
	
	}
}
