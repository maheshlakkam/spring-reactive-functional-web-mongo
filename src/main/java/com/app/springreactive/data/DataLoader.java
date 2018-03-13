package com.app.springreactive.data;

import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.app.springreactive.model.Product;
import com.app.springreactive.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {
	ProductRepository repository;
	public DataLoader(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Data Loading has been started.");
		repository.deleteAll()
			.subscribe(null,null,() -> {
				Stream.of(new Product("100","Mobile",100L,10000d),
						new Product("101","Computer",200L,50000d),
						new Product("102","Laptop",300L,40000d))
				.forEach(product ->{
					repository
						.save(product).log()
						.subscribe(null,null,()->log.info("Data Loading has been completed."));
				});
			});
	}
}