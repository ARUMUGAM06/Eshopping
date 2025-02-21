package com.eshopping.cart.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final int id;
	@Column(nullable = false,length = 30)
	private final String name;
	@Column(length = 30)
	private final String brand;
	@Column(length = 30)
	private final int inventory;
	private final BigDecimal price;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id",nullable = false)
	private final Category category;
//	public Product(int id,String name,String brand,int inventory,BigDecimal price,Category category) {
//		this.id=id;
//		this.name=name;
//		this.brand=brand;
//		this.inventory=inventory;
//		this.price=price;
//		this.category=category;
//	}
	@OneToMany(mappedBy = "product",orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Image> image;	
}
