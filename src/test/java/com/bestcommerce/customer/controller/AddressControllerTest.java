package com.bestcommerce.customer.controller;

import com.bestcommerce.customer.domain.Address;
import com.bestcommerce.customer.dto.AddressDto;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.service.account.AccountService;
import com.bestcommerce.customer.service.address.AddressService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AddressService addressService;

    @DisplayName("주소 저장 테스트")
    @Test
    public void saveAddressTest() throws Exception{

        Long customerId = 1L;
        String addr = "대구광역시 서초구 남천동 네컷아파트 403호";
        Character represent = 'Y';
        String zipcode = "23897";

        AddressDto addressDto = new AddressDto(customerId, addr, represent, zipcode);

        String testUrl = "http://localhost:"+port+"/address/save";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, addressDto, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Address> addressList = addressService.getAllAddressesByCustomer(accountService.getOneCustomerInfo(customerId));

        for(Address address : addressList){
            if(address.getAddr().equals(addr)){
                assertThat(address.getCustomer().getCuId()).isEqualTo(customerId);
                assertThat(address.getRepYn()).isEqualTo(represent);
                assertThat(address.getZipCode()).isEqualTo(zipcode);
                addressService.deleteAddressByAddrId(address.getAddrId());
                break;
            }
        }
    }

    @DisplayName("고객 정보로 주소 가져오는 테스트")
    @Test
    public void getAllAddressByCustomer() throws Exception{

        String customerEmail = "dudtkd0219@gmail.com";

        CustomerDto customerDto = new CustomerDto(customerEmail,"","","","",'0');

        String testUrl = "http://localhost:"+port+"/address/get";

        ResponseEntity<Object> response = restTemplate.postForEntity(testUrl, customerDto, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        System.out.println(response.getBody());
    }
}