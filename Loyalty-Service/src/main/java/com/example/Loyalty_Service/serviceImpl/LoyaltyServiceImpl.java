package com.example.Loyalty_Service.serviceImpl;

import com.example.Loyalty_Service.exception.ResourceNotFoundException;
import com.example.Loyalty_Service.model.Loyalty;
import com.example.Loyalty_Service.repository.LoyaltyRepository;
import com.example.Loyalty_Service.service.LoyaltyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LoyaltyServiceImpl implements LoyaltyService {

    private final LoyaltyRepository repository;

    public LoyaltyServiceImpl(LoyaltyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loyalty create(Loyalty loyalty) {
        Loyalty save = repository.save(loyalty);
        log.info("Loyalty created: {}", save);
        return save;
    }

    @Transactional
    @Override
    public Loyalty getById(Long id) {
        Loyalty loyalty = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loyalty not found with id: " + id));
        log.info("Loyalty retrieved: {}", loyalty);

        return loyalty;
    }


    @Transactional
    @Override
    public Loyalty update(Long id, Loyalty loyalty) {
        Loyalty update = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loyalty not found with id: " + id));

        update.setPoints(loyalty.getPoints());
        update.setDescription(loyalty.getDescription());
        update.setStatus(update.getStatus());
        update.setType(loyalty.getType());

        repository.save(update);
        log.info("Loyalty updated: {}", update);

        return update;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            log.info("Loyalty deleted with id: {}", id);
        } else {
            log.warn("Loyalty not found with id: {}", id);
            throw new ResourceNotFoundException("Loyalty not found with id: " + id);
        }
    }
}
