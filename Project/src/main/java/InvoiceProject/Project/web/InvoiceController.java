package InvoiceProject.Project.web;

import java.util.List; 
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import InvoiceProject.Project.domain.Invoice;
import InvoiceProject.Project.domain.InvoiceRepository;
import InvoiceProject.Project.domain.TypeRepository;



@Controller
public class InvoiceController {
	
	@Autowired
	private InvoiceRepository invoicepository;
	
	@Autowired
	private TypeRepository typespository;

	@RequestMapping("/invoices")
	public String allInvoices(Model model) {
		
		model.addAttribute("invoices", invoicepository.findAll());
		return "invoices";
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
		return "addinvoice";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
	    Invoice invoice = invoicepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
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
}

