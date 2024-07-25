package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Display a list of all customers
    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "Customer/customers";
    }


    // Show form for adding a new customer
    @GetMapping("/new")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer/customer-form";
    }

    // Show form for editing an existing customer
    @GetMapping("/{id}/edit")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Optional<Customer> customerOptional = customerService.getCustomerById(id);
        if (customerOptional.isPresent()) {
            model.addAttribute("customer", customerOptional.get());
            return "Customer/customer-form";
        } else {
            return "redirect:/customers";
        }
    }

    // Save or update a customer
    @PostMapping("/save")
    public String saveOrUpdateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveOrUpdate(customer);
        return "redirect:/customers";
    }

    // View details of a specific customer
    @GetMapping("/{id}")
    public String viewCustomerDetails(@PathVariable Long id, Model model) {
        Optional<Customer> customerOptional = customerService.getCustomerById(id);
        if (customerOptional.isPresent()) {
            model.addAttribute("customer", customerOptional.get());
            return "Customer/details";
        } else {
            return "redirect:/customers";
        }
    }

    // Delete a customer
    @GetMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
