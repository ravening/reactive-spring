package com.rakesh.reactivespring.customer;

import com.rakesh.reactivespring.customer.api.CustomerApi;
import com.rakesh.reactivespring.customer.model.Customer;
import com.rakesh.reactivespring.customer.model.CustomerFullData;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController  implements CustomerApi {

    AtomicInteger counter = new AtomicInteger();
    Map<Long, CustomerFullData> customerMap = new HashMap<>();

    @Override
    public ResponseEntity<CustomerFullData> createCustomer(Customer customer) {
        CustomerFullData customerFullData = new CustomerFullData();
        customerFullData.setFirstName(customer.getFirstName());
        customerFullData.setLastName(customer.getLastName());
        customerFullData.setCustomerId((long) counter.incrementAndGet());
        customerMap.put((long) counter.get(), customerFullData);

        return ResponseEntity.ok(customerFullData);
    }

    @Override
    public ResponseEntity<CustomerFullData> getCustomer(Long customerId) {
        return ResponseEntity.ok(customerMap.get(customerId));
    }
}
