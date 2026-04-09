package com.tinyknotsbyshru.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "User Service API",
                version = "1.0",
                description = "This Service allows you to perform CRUD operations on user data, " +
                        "including creating, retrieving, updating, and deleting user information including user profiles and user addresses.",
                contact = @Contact(
                        name = "Shruti Chintawar",
                        email = "tinyknotsbyshru@gmail.com"

                )
        )
)
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
