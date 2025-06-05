package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Order;
import com.example.demo.service.PrintService;
@Service
public class PrintServiceImpl implements PrintService{

	@Override
	public void printOrder(Order order) {
		// 這裡示範簡單印出訂單資訊，實際可串接出單機API或硬體
		System.out.println("開始列印訂單: " + order.getId());
        System.out.println("顧客: " + order.getCustomerName());
        System.out.println("訂單時間: " + order.getOrderTime());
        System.out.println("訂單明細:");
        
        order.getItems().forEach(item -> {
        	System.out.println("- " + item.getProduct().getName()+ " x" + item.getQuantity() + " 單價:" + item.getUnitPrice());
        });
        
        System.out.println("總金額: " + order.getTotalAmount());
        System.out.println("列印完成");
		
	}

}
