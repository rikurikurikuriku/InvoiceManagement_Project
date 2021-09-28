package InvoiceProject.Project;

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
		
		repository.save(new Invoice(1, "Test Company Oy", "Monthly food order", 28092021, 28102021, 90.50, repository1.findByName("Office Management").get(0)));	
	 
		User riku = new User("riku", "$2a$10$PfsP09QvIUHfh3EAJYRKlOYvNVBKdY5XWlZLudfb2SxhNBP/.tuM.", "USER");
		User admin = new User("admin", "$2a$10$OY1LKyCwlsNUPJsl7OX0Wu4Mg.dP5qfkKZeCXpT3GzXNRJsh5uKfS", "ADMIN");
		upository.save(riku);
		upository.save(admin);
	};
	}
	
}
