package com.example.demo.Controller;

import com.example.demo.Model.Vehicle;
import com.example.demo.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Display a list of all vehicles
    @GetMapping
    public String getAllVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "Vehicles/vehicles";
    }

    @GetMapping("/new")
    public String showAddVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "Vehicles/vehicle-form";
    }


    // Show form for editing an existing vehicle
    @GetMapping("/{id}/edit")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicleOptional = vehicleService.getVehicleById(id);
        if (vehicleOptional.isPresent()) {
            model.addAttribute("vehicle", vehicleOptional.get());
            return "Vehicles/vehicle-form";
        } else {
            return "redirect:/vehicles";
        }
    }

    // Save or update a vehicle
    @PostMapping("/save")
    public String saveOrUpdateVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.saveOrUpdate(vehicle);
        return "redirect:/vehicles";
    }

    // View details of a specific vehicle
    @GetMapping("/{id}")
    public String viewVehicleDetails(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicleOptional = vehicleService.getVehicleById(id);
        if (vehicleOptional.isPresent()) {
            model.addAttribute("vehicle", vehicleOptional.get());
            return "vehicles/details";
        } else {
            return "redirect:/vehicles";
        }
    }

    // Delete a vehicle
    @GetMapping("/{id}/delete")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }
}