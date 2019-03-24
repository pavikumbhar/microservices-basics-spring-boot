package com.pavikumbhar.microservices.catalogservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pavikumbhar.microservices.catalogservice.model.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class InventoryServiceClient {
	
	private  RestTemplate restTemplate;
	
	private   InventoryServiceFeignClient inventoryServiceFeignClient;
	
	/**
	 * Returns the comments for the task; note that this applies a circuit
	 * breaker that would return the default value if the inventory-service
	 * was down.
	 * 
	 * Discussion on HystrixProperty
	 * <ol>
	 * <li><b>execution.isolation.strategy:</b> The value of "SEMAPHORE" enables
	 * Hystrix to use the current thread for executing this command. Since we
	 * need to use the parent HttpRequest to pass the OAuth2 access token, we
	 * are constrained in using the calling thread. The number of concurrent
	 * requests that can be made to the command is limited by the semaphore(or
	 * counter) value; default is 10.In a normal scenario, you would use
	 * isolation strategy of "THREAD" that executes the Hystrix command in a
	 * separate thread pool. This is desirable because it doesn't block the
	 * calling thread and lets us handle faulty client libraries, client
	 * performance considerations etc.</li>
	 * 
	 * <li><b>circuitBreaker.requestVolumeThreshold:</b>This property sets the
	 * minimum number of requests in a rolling window that will trip the
	 * circuit. For example, if the value is 20, then if only 19 requests are
	 * received in the rolling window (say a window of 10 seconds) the circuit
	 * will not trip open even if all 19 failed. We have a lower value in this
	 * sample application so as to trip early.</li>
	 * 
	 * <li><b>circuitBreaker.sleepWindowInMilliseconds:</b>This property sets
	 * the amount of time, after tripping the circuit, to reject requests before
	 * allowing attempts again to determine if the circuit should again be
	 * closed. We again have a lower value so that Hystrix tries to pass single
	 * request to the called service quickly.</li>
	 * </ol>
	 * 
	 * @param 
	 * @return List<Product>
	 */

	
	@HystrixCommand(fallbackMethod = "getDefaultfindAllProducts")
	public  List<Product> findAllProducts() {
		log.info("Finding   all product using rest client ");
		return inventoryServiceFeignClient.findAllProducts();
		/*
		 * ResponseEntity<List<Product>> itemResponseEntity =
		 * restTemplate.exchange("http://INVENTORY-SERVICE/api/inventory/products/",
		 * HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() { });
		 * if (itemResponseEntity.getStatusCode() == HttpStatus.OK) { int size =
		 * itemResponseEntity.getBody().size(); log.info("Available products size : " ,
		 * size); return itemResponseEntity.getBody(); } else {
		 * log.error("Unable to findAllProducts"); return new ArrayList<>(); }
		 */
	}
	
	@HystrixCommand(fallbackMethod = "getDefaultProductInventoryByCode", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	public Product findInventoryByProductCode(String productCode) {
		log.info("Finding product using restTemplate ###");
		//return inventoryServiceFeignClient.findInventoryByProductCode(productCode);
		return restTemplate.getForObject("http://INVENTORY-SERVICE/api/inventory/product/" + productCode, Product.class);
	}
	

	
	 
	@SuppressWarnings("unused")
	private List<Product> getDefaultfindAllProducts() {
        log.info("findAllProducts service not available : returning default findAllProducts");
        List<Product> products = new ArrayList<>();
		products.add(new Product(0l, "lap", "default-laptop", "Lenovo", 3.0, true));
		products.add(new Product(0l, "mob", "default-Mobile", "Mobile", 2.0, true));
        return products;
}

	@SuppressWarnings("unused")
	private Product getDefaultProductInventoryByCode(String productCode) {
		log.info("findInventoryByProductCode service not available : returning default ProductInventoryByCode for productCode: {}" , productCode);
		Product product = new Product();
		product.setInStock(true);
		product.setName("default-laptop");
		return product;
	}

}
