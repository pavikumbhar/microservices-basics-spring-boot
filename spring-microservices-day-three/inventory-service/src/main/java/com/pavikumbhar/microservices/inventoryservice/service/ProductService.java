package com.pavikumbhar.microservices.inventoryservice.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.pavikumbhar.microservices.inventoryservice.entity.Product;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pavikumbhar
 *
 */
@Service
@Slf4j
public class ProductService {

	private static final AtomicLong counter = new AtomicLong();
	private static List<Product> products;

	static {
		products = populateDummyproducts();
	}

	private static List<Product> populateDummyproducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(counter.incrementAndGet(), "lap", "laptop", "Lenovo", 3.0, true));
		products.add(new Product(counter.incrementAndGet(), "mob", "Mobile", "Mobile", 2.0, true));
		return products;
	}

	public List<Product> findAllProducts() {
		return products;
	}

	public Optional<Product> findProductByCode(String code) {
		log.info("Finding PRODUCT by code : {}",  code);
		return products.stream().filter(product -> product.getCode().equals(code)).findFirst();

	}

	public void saveProduct(Product product) {
		product.setId(counter.incrementAndGet());
		products.add(product);
	}

	public void updateProduct(Product product) {
		int index = products.indexOf(product);
		products.set(index, product);
	}

	public void deleteProductById(long id) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getId() == id) {
				iterator.remove();
			}
		}
	}

}
