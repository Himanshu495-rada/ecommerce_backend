package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.AddressDTO;
import com.ecommerce.ecommerce.entities.Address;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.AddressRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AddressDTO> getUserAddresses(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User with ID" + userId + "not found"));
        List<Address> addresses = addressRepository.findByUser(user);
        return addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();
    }

    public AddressDTO getAddressById(Long addressId){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address with ID " + addressId + " not found"));
        return modelMapper.map(address, AddressDTO.class);
    }

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = modelMapper.map(addressDTO, Address.class);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address with ID " + addressId + " not found"));

        addressDTO.setAddressId(addressId); // Ensure ID matches path variable
        modelMapper.map(addressDTO, existingAddress); // Update existing object with non-null DTO fields
        Address savedAddress = addressRepository.save(existingAddress);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

}
