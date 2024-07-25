package com.example.demo.Controller;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Rental;
import com.example.demo.Model.Vehicle;
import com.example.demo.Services.CustomerService;
import com.example.demo.Services.RentalService;
import com.example.demo.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;

    // Constructor injection
    public RentalController(RentalService rentalService, VehicleService vehicleService, CustomerService customerService) {
        this.rentalService = rentalService;
        this.vehicleService = vehicleService;
        this.customerService = customerService;
    }
     //Display a list of all rentals
    @GetMapping
    public String getAllRentals(Model model) {
        List<Rental> rentals = rentalService.getAllRentals();
        model.addAttribute("rentals", rentals);
        return "rentals/Rentals";
    }

    // Show form for adding a new rental
    @GetMapping("/new")
    public String showNewRentalForm(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("rental", new Rental());
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("customers", customers);
        return "rentals/rental-form";
    }

    // Show form for editing an existing rental
    @GetMapping("/{id}/edit")
    public String showEditRentalForm(@PathVariable Long id, Model model) {
        Optional<Rental> rentalOptional = rentalService.getRentalById(id);
        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            model.addAttribute("rental", rental);
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            model.addAttribute("customers", customerService.getAllCustomers());
            return "rentals/rental-form"; // Use the same form for editing
        } else {
            return "redirect:/rentals";
        }
    }

    // Save or update a rental
    @PostMapping("/save")
    public String saveOrUpdateRental(@ModelAttribute("rental") Rental rental,
                                     @RequestParam("vehicleId") Long vehicleId,
                                     @RequestParam("customerId") Long customerId,
                                     @RequestParam("rentalStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalStartDate,
                                     @RequestParam("rentalEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate rentalEndDate) {
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId).orElse(null);
        Customer customer = customerService.getCustomerById(customerId).orElse(null);

        rental.setVehicle(vehicle);
        rental.setCustomer(customer);
        rental.setRentalStartDate(rentalStartDate);
        rental.setRentalEndDate(rentalEndDate);
        rentalService.saveOrUpdate(rental);
        return "redirect:/rentals";
    }

    // View details of a specific rental
    @GetMapping("/{id}")
    public String viewRentalDetails(@PathVariable Long id, Model model) {
        Optional<Rental> rentalOptional = rentalService.getRentalById(id);
        if (rentalOptional.isPresent()) {
            model.addAttribute("rental", rentalOptional.get());
            return "rentals/details";
        } else {
            return "redirect:/rentals";
        }
    }

    // Delete a rental
    @GetMapping("/{id}/delete")
    public String deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return "redirect:/rentals";
    }
}
