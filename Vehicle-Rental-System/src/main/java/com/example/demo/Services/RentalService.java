package com.example.demo.Services;

import com.example.demo.Model.Rental;
import com.example.demo.Repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public Rental saveOrUpdate(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    public List<Rental> findRentalsByVehicleId(Long vehicleId) {
        return rentalRepository.findByVehicleId(vehicleId);
    }

    public List<Rental> findRentalsByCustomerId(Long customerId) {
        return rentalRepository.findByCustomerId(customerId);
    }

    // Other service methods as needed
}
