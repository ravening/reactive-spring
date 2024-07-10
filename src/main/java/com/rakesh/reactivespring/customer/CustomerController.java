package com.rakesh.reactivespring.customer;

import com.rakesh.reactivespring.customer.api.CustomerApi;
import com.rakesh.reactivespring.customer.model.Customer;
import com.rakesh.reactivespring.customer.model.CustomerFullData;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController  implements CustomerApi {

    AtomicInteger counter = new AtomicInteger();
    Map<Long, CustomerFullData> customerMap = new HashMap<>();

    @Override
    public Mono<ResponseEntity<CustomerFullData>> createCustomer(Mono<Customer> customer, ServerWebExchange exchange) {
        CustomerFullData customerFullData = new CustomerFullData();
        customerFullData.setFirstName(String.valueOf(customer.subscribe(Customer::getFirstName)));
        customerFullData.setLastName(String.valueOf(customer.map(Customer::getLastName)));
        customerFullData.setCustomerId((long) counter.incrementAndGet());
        customerMap.put((long) counter.get(), customerFullData);

        return Mono.just(ResponseEntity.ok(customerFullData));
    }

    @Override
    public Mono<ResponseEntity<CustomerFullData>> getCustomer(Long customerId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(customerMap.get(customerId)));
    }
}
