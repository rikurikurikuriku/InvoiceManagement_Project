package InvoiceProject.Project.web;

import java.io.IOException; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List; 
import java.util.Optional;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import InvoiceProject.Project.domain.File;
import InvoiceProject.Project.domain.FileRepository;
import InvoiceProject.Project.domain.Invoice;
import InvoiceProject.Project.domain.InvoiceRepository;
import InvoiceProject.Project.domain.TypeRepository;
import InvoiceProject.Project.domain.UserRepository;



@Controller
public class InvoiceController {
	
	@Value("${upload.path}")
	private String uploadFolder;
	
	@Autowired
	private InvoiceRepository invoicepository;
	
	@Autowired
	private TypeRepository typespository;
	
	@Autowired
	private FileRepository fepository; 
	
	@Autowired
	private UserRepository upository;


	@RequestMapping("/invoices")
	public String allInvoices(Model model) {
		
		model.addAttribute("invoices", invoicepository.findAll());
		model.addAttribute("users", upository.findAll());
		model.addAttribute("standardDate", new Date());
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("timestamp", Instant.now());
		
		return "invoices";
	}
	
	//date format for Java8 dialect via Thymeleaf
	private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
	    SpringTemplateEngine engine = new SpringTemplateEngine();
	    engine.addDialect(new Java8TimeDialect());
	    engine.setTemplateResolver(templateResolver);
	    return engine;
	}
	
	// RESTful to get all invoices
    @RequestMapping(value="/invs", method = RequestMethod.GET)
    public @ResponseBody List<Invoice>invoiceListRest() {	
    	
        return (List<Invoice>) invoicepository.findAll();
    }    
    
 // RESTful to get invoice by id
    @RequestMapping(value="/invs/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Invoice> findInvoiceRest(@PathVariable("id") Long invoiceId) {	
    	return invoicepository.findById(invoiceId);
    }       
	
	@RequestMapping(value = "/add")
	public String addInvoice(Model model) {
		model.addAttribute("invoice", new Invoice());
		model.addAttribute("types", typespository.findAll());
		model.addAttribute("invoices", invoicepository.findAll());
		model.addAttribute("standardDate", new Date());
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("timestamp", Instant.now());
		
		return "addinvoice";
	}
	

	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
	    Invoice invoice = invoicepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("invoices", invoicepository.findAll());
		model.addAttribute("standardDate", new Date());
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("timestamp", Instant.now());
	    model.addAttribute("invoice", invoice);
	    model.addAttribute("types", typespository.findAll());
	    return "edit";
	}
	
	@PostMapping(value = "/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Invoice invoice, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        invoice.setId(id);
	        model.addAttribute("types", typespository.findAll());
	        return "edit";
	    }
	    invoicepository.save(invoice);
	    return "redirect:/invoices";
	    
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveInvoice(@Valid Invoice invoice, BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			model.addAttribute("types", typespository.findAll());
			return "addinvoice";
		}
		invoicepository.save(invoice);
		return "redirect:invoices";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteInvoice(@PathVariable("id") Long id, Model model) {
		invoicepository.deleteById(id);
	        return "redirect:../invoices";
	    }     
	
	@GetMapping("/files")
	public String fileList(Model model) {
	model.addAttribute("files", fepository.findAll());
	return "filelist";
	}
	
	
	
}


