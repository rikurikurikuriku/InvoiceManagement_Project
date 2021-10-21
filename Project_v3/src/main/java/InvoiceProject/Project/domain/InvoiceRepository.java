package InvoiceProject.Project.domain;

import java.util.List;  

import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>  {

	List<Invoice> findByBiller(String biller);


}
