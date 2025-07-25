package com.Priyanshu.ainBnb.strategy;

import com.Priyanshu.ainBnb.entity.Inventory;

import java.math.BigDecimal;


public class BasePricingStrategy implements PricingStrategy{


    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
