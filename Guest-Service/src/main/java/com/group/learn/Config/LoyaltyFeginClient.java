package com.group.learn.Config;

import com.group.learn.dto.LoyaltyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "LOYALTY-SERVICE")
public interface LoyaltyFeginClient {

    LoyaltyDTO create(@RequestBody LoyaltyDTO loyaltyDTO);

    LoyaltyDTO findById(@PathVariable Long id);

    LoyaltyDTO update(@PathVariable Long id, @RequestBody LoyaltyDTO loyaltyDTO);
}
