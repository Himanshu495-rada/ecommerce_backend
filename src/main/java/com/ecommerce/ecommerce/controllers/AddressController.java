package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.AddressDTO;
import com.ecommerce.ecommerce.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping //http://localhost:8083/api/address?userId=4
    @PreAuthorize("hashRole('CUSTOMER')")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(@RequestParam Long userId) {
        List<AddressDTO> addresses = addressService.getUserAddresses(userId);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping //http://localhost:8083/api/address
    @PreAuthorize("hashRole('CUSTOMER')")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        AddressDTO savedAddressDTO = addressService.createAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddressDTO);
    }

    @PutMapping("/{addressId}") //http://localhost:8083/api/address/1
    @PreAuthorize("hashRole('CUSTOMER')")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO) {
        addressDTO.setAddressId(addressId);
        AddressDTO updatedAddressDTO = addressService.updateAddress(addressId, addressDTO);
        return ResponseEntity.ok(updatedAddressDTO);
    }

    @DeleteMapping("/{addressId}") //http://localhost:8083/api/address/1
    @PreAuthorize("hashRole('CUSTOMER')")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }

}
