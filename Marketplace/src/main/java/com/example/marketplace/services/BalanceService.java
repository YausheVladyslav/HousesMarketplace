package com.example.marketplace.services;

import com.example.marketplace.entities.UserEntity;
import com.example.marketplace.repositories.UserRepository;
import com.example.marketplace.responses.BalanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserRepository userRepository;

    public BalanceResponse getBalance(UserEntity user){
        long dollar = user.getBalance()/100;
        int cent = (int) (user.getBalance() % dollar);
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setDollar(dollar);
        balanceResponse.setCent(cent);
        return balanceResponse;
    }
}
