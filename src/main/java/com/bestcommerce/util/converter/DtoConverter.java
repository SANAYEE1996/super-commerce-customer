package com.bestcommerce.util.converter;

import com.bestcommerce.address.entity.Address;
import com.bestcommerce.address.dto.AddressDto;
import com.bestcommerce.cart.entity.Cart;
import com.bestcommerce.cart.dto.CartDto;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoConverter {

    public AddressDto toAddressDto(Address address){
        return new AddressDto(address.getAddrId(), address.getCustomer().getCuId(), address.getAddr(), address.getRepYn(), address.getZipCode());
    }

    public List<AddressDto> toAddressDtoList(List<Address> addressList){
        List<AddressDto> addressDtoList = new ArrayList<>();
        for(Address address : addressList){
            addressDtoList.add(toAddressDto(address));
        }
        return addressDtoList;
    }

    public CartDto toCartDto(Cart cart){
        return new CartDto(cart.getProductCount(), cart.getCartKey().getCustomerId(), cart.getCartKey().getQuantityId());
    }


    public CustomerDto toCustomerDto(Customer customer, String customerEmail){
        return new CustomerDto(customer.getCuId(), customer.getCuName(), customerEmail, "", customer.getCuTelNumber(), customer.getBirthdate(), customer.getRegisterDate(), customer.getModifyDate());
    }

}
