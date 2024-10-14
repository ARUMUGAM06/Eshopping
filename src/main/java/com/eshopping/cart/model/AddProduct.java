package com.eshopping.cart.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AddProduct {
	int id;
	String name;
	String brand;
	int inventory;
	BigDecimal price;
	Category category;
}
