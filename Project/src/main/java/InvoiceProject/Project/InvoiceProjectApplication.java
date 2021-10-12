package InvoiceProject.Project;

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
		repository1.save(new Type("It"));
		
		upository.save(new User("riku", "$2a$10$KaLs67SscV5FeBSfEL0uzOWrYOSn8mIjHDsvOz92qUojbbF8e5Teq", "USER"));
		upository.save(new User("admin", "$2a$10$1fc1xCJrKIdtGmh8WQZwdegzxQPEkSLHUdkg8tWW4hKW5lQ1TijWC", "ADMIN"));
		
		
		
	
	};
	
	
	}
}
