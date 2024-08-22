package com.folksdev.account;

import com.folksdev.account.model.Customer;
import com.folksdev.account.repository.CustomerRepository;
import org.apache.logging.log4j.util.Supplier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.HashSet;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public AccountApplication(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(String...args) throws Exception{
        Customer customer = customerRepository.save(new Customer( "",  "Huseyin",  "Beller", new HashSet<>()));
        System.out.println(customer);
    }

    @ConditionalOnProperty(
            prefix = "command.line.runner",
            value = "enabled",
            havingValue = "true",
            matchIfMissing = true
    )

    @Component
    public static class CommandLineTaskExecutor implements CommandLineRunner{
        @Bean
        public Clock clock(){
            return Clock.systemUTC();
        }

        @Bean
        public Supplier<UUID> uuidSupplier(){
            return UUID::randomUUID;
        }

        @Override
        public void run(String... args) throws Exception {

        }
    }


}
