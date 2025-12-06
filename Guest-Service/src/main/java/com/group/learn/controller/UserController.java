package com.group.learn.controller;

import com.group.learn.dto.LoyaltyDTO;
import com.group.learn.entity.LoyaltyTransaction;
import com.group.learn.service.UserService;
import jakarta.ws.rs.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/loyalty")
    public Mono<LoyaltyTransaction> createLoyalty(@RequestBody LoyaltyDTO dto){
        return userService.loyaltyTransactionMono(dto);
    }

    @GetMapping("/loyalty/{id}")
    public Mono<LoyaltyTransaction> getLoyaltyById(@PathVariable Long id){
        return userService.getLoyaltyById(id);
    }

}
