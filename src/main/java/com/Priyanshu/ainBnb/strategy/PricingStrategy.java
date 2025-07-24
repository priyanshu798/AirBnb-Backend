package com.Priyanshu.ainBnb.strategy;

import com.Priyanshu.ainBnb.entity.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);

}
