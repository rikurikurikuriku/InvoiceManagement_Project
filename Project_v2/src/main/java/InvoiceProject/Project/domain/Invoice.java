package InvoiceProject.Project.domain;

import java.util.Date;

import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Field is mandatory")
	@Size(min=2, max=30)
	private String biller, product;
	@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date date, duedate;
	@NotNull
	private double amount;
	
	@ManyToOne
	@JoinColumn(name = "typeid")
	@JsonIgnore
	private Type type;
	


	public Invoice() {}



	public Invoice(long id, String biller, String product, Date date, Date duedate, double amount, Type type) {
		super();
		this.id = id;
		this.biller = biller;
		this.product = product;
		this.date = date;
		this.duedate = duedate;
		this.amount = amount;
		this.type = type;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getBiller() {
		return biller;
	}



	public void setBiller(String biller) {
		this.biller = biller;
	}



	public String getProduct() {
		return product;
	}



	public void setProduct(String product) {
		this.product = product;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Date getDuedate() {
		return duedate;
	}



	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}






	@Override
	public String toString() {
		return "Invoice [id=" + id + ", biller=" + biller + ", product=" + product + ", date=" + date + ", duedate="
				+ duedate + ", amount=" + amount + ", type=" + type + "]";
	}



	
	

}
