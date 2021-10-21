package InvoiceProject.Project.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Type {
	 @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long typeid;
		private String name;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
		private List<Invoice> invoices;
		
		public Type() {}
		
		public Type(String name) {
			super();
			this.name = name;
		}

		public Long getTypeid() {
			return typeid;
		}

		public void setTypeid(Long typeid) {
			this.typeid = typeid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Invoice> getInvoices() {
			return invoices;
		}

		public void setInvoices(List<Invoice> invoices) {
			this.invoices = invoices;
		}
		
		
			
		}

