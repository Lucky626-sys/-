//自動統計每日營業額的服務層
/*package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.model.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class AutoSalesService {

    @Autowired
    private OrderItemRepository orderItemRepo;
    //統計某一天銷售數據的方法
    public List<DailySalesDTO> getDailySalesByDate(LocalDate date) {
    	//查詢當天訂單明細
        List<OrderItem> items = orderItemRepo.findByOrders_Date(date);
        //用來統計每一個商品名稱（String name）對應的銷售資訊（DailySalesDTO）
        Map<String, DailySalesDTO> map = new HashMap<>();
        //迴圈統計每筆訂單明細
        for (OrderItem item : items) {
            String name = item.getProduct().getName();
            int quantity = item.getQuantity();
            //item是一筆OrderItem訂單明細，getSubtotal是商品的銷售金額(商品售價*數量)
            BigDecimal revenue = BigDecimal.valueOf(item.getSubtotal());
            //
            BigDecimal cost = item.getProduct().getCost().multiply(BigDecimal.valueOf(quantity));
            BigDecimal profit = revenue.subtract(cost);

            //累加統計
            //key是name, value是封裝統計結果的物件
            //map.merge -> 如果map中還沒有該商品，加入new DailySalesDTO,如果map中已有該商品，執行mergeFunction
            map.merge(name,
                new DailySalesDTO(name, 
			                	  quantity, 
			                	  revenue.intValue(),
			                      cost.intValue(),
			                      profit.intValue()),
                (oldVal, newVal) -> {
                    oldVal.setQuantity(oldVal.getQuantity() + newVal.getQuantity());
                    oldVal.setRevenue(oldVal.getRevenue() + newVal.getRevenue());
                    oldVal.setCost(oldVal.getCost() + newVal.getCost());
                    oldVal.setProfit(oldVal.getRevenue() - oldVal.getCost());
                    return oldVal;
                });
        }
        return new ArrayList<>(map.values());
    }
}*/
