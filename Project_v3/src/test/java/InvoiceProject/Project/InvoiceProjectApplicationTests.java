package InvoiceProject.Project;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import InvoiceProject.Project.domain.Invoice;
import InvoiceProject.Project.domain.InvoiceRepository;
import InvoiceProject.Project.domain.TypeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InvoiceProjectApplicationTests {
	
	
	
	@Autowired
	private InvoiceRepository repository;
	
	@Autowired
	private TypeRepository tepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void findByBillerreturnanybiller() {
		List<Invoice> invoices = repository.findByBiller("Moikka Oy");
		System.out.println("TEST: FindByBiller: " + invoices);
		System.out.println("TESTING: FindAll:" +  repository.findAll());
		
		Assertions.assertThat(invoices.get(0).getBiller()).isEqualTo("Moikka Oy");
	}
	
	@Test
	public void createNewInvoice() {
		
		
	Invoice invoice = new Invoice(2, "Heippa Oy", "1234567", new Date(2021-12-12), new Date(2021-12-16), 10.50,
			tepository.findByName("IT").get(0));
	repository.save(invoice);
	Assertions.assertThat(invoice.getId()).isNotNull();
	}

	@Test
	public void BillerReturnSize1() {
		List<Invoice> invoices = repository.findByBiller("Moikka Oy");
		System.out.println("TEST invoices: " + invoices);
		Assertions.assertThat(invoices).hasSize(1);

	}

	
}

