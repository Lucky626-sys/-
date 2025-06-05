package com.example.demo.config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.dto.OrderItemDTO;
import com.example.demo.model.entity.OrderItem;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
    	ModelMapper modelMapper = new ModelMapper();
    	
    	/*
    	 * Hibernate 的 PersistentBag 是一種特殊的集合，它實作了 java.util.List 介面，也同時繼承了 java.util.AbstractPersistentCollection。
    	 * 但在 Java 型態繼承結構中，PersistentBag 其實是 java.util.Collection 的子類（因為 List 本來就是 Collection 的子介面）
    	 * 最大兼容性：這樣寫代表「只要來源是任何 Collection（包括 List、Set、PersistentBag、HashSet...），目標是 List 的時候，都自動用 ArrayList 包裝一次」
    	 * Hibernate 的 PersistentBag 雖然是 List，但它本質是 Collection，所以這個 Converter 可以涵蓋所有 Hibernate 的集合型態
    	 * */
    	
    	//型態轉換
    	//全域處理Collection -> ArrayList 轉換
    	modelMapper.addConverter(ctx -> {
    		if(ctx.getSource() == null) return null;
    		return new ArrayList<>(ctx.getSource());
    	}, Collection.class, List.class);
    	
    	//內容mapping
    	//明確定義OrderItem -> OrderItemDTO 的 mapping(解決productId/productName衝突)
    	 modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTO>() {
             @Override
             protected void configure() {
                 map().setProductId(source.getProduct().getId());
                 map().setProductName(source.getProduct().getName());
                 map().setQuantity(source.getQuantity());
                 map().setUnitPrice(source.getUnitPrice());
                 map().setSubtotal(source.getSubtotal());
             }
         });
    	return modelMapper;
    }
    
}
