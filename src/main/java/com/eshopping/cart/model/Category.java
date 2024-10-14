package com.eshopping.cart.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false,length = 25)
	private String name;
	@OneToMany(mappedBy = "category")
	private List<Product> product;

	public Category(String name) {
		this.name = name;
	}
}
