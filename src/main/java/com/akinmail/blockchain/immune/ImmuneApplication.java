package com.akinmail.blockchain.immune;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImmuneApplication implements CommandLineRunner {
    @Autowired
    Web3jSampleService web3jSampleService;

    public static void main(String[] args) {
        SpringApplication.run(ImmuneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(web3jSampleService.getClientVersion());

    }
}
