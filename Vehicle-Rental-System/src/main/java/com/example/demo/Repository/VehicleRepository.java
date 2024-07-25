package com.example.demo.Repository;

import com.example.demo.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByMake(String make);
    List<Vehicle> findByModel(String model);
    List<Vehicle> findByType(String type);
    List<Vehicle> findByYear(int year);
    List<Vehicle> findByPricePerDayLessThan(double price);
    List<Vehicle> findByPricePerDayGreaterThan(double price);
}
