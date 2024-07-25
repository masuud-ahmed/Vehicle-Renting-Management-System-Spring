package com.example.demo.Repository;

import com.example.demo.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByVehicleId(Long vehicleId);

    List<Rental> findByCustomerId(Long customerId);
    // Define custom query methods if needed
}
