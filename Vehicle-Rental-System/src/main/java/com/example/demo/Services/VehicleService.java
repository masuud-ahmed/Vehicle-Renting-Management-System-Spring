package com.example.demo.Services;

import com.example.demo.Model.Vehicle;
import com.example.demo.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle saveOrUpdate(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public List<Vehicle> findVehiclesByMake(String make) {
        return vehicleRepository.findByMake(make);
    }

    public List<Vehicle> findVehiclesByModel(String model) {
        return vehicleRepository.findByModel(model);
    }

    public List<Vehicle> findVehiclesByType(String type) {
        return vehicleRepository.findByType(type);
    }

    public List<Vehicle> findVehiclesByYear(int year) {
        return vehicleRepository.findByYear(year);
    }

    public List<Vehicle> findVehiclesByPricePerDayLessThan(double price) {
        return vehicleRepository.findByPricePerDayLessThan(price);
    }

    public List<Vehicle> findVehiclesByPricePerDayGreaterThan(double price) {
        return vehicleRepository.findByPricePerDayGreaterThan(price);
    }

    public void deleteVehicleById(Long id) {
    }

    // Other service methods as needed
}
