// folksdev test videolarına bak.
// testsiz hiçbir kod yazma.
// hangi tip metotların testi yazılmaz?
package com.folksdev.account.service;

import com.folksdev.account.dto.CustomerDto;
import com.folksdev.account.dto.CustomerDtoConverter;
import com.folksdev.account.exception.CustomerNotFoundException;
import com.folksdev.account.model.Customer;
import com.folksdev.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest {

    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    @BeforeEach

    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository, converter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("id"));
    }

    @Test
    public void testGetCustomerByID_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        CustomerDto customerDto = new CustomerDto("id", "name", "surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);
        Customer result = service.findCustomerById("id");
        assertEquals(result,
                customerDto);
    }
    @Test
        public void testGetCustomerByID_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){

            Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
            service.getCustomerById("id");
            assertThrows(CustomerNotFoundException.class,
                    () -> service.getCustomerById("id"));

            Mockito.verifyNoInteractions(converter);

    }
}
