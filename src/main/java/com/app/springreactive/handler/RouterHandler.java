package com.app.springreactive.handler;

import java.time.Duration;
import java.util.stream.Stream;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.app.springreactive.model.Product;
import com.app.springreactive.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class RouterHandler {
	private ProductRepository productRepository;
	public RouterHandler(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Mono<ServerResponse> fetchAll(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productRepository.findAll(),Product.class);
	}

	public Mono<ServerResponse> insertProduct(ServerRequest request){
		Mono<Product> product = request.bodyToMono(Product.class);
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(productRepository.insert(product), Product.class);
	}
	
	public Mono<ServerResponse> deleteProductById(ServerRequest request){
		String product_id = request.pathVariable("productId");
		return productRepository.deleteById(product_id)
                .flatMap(
                		strMono -> 
                			ServerResponse
                			.ok()
                			.contentType(MediaType.TEXT_PLAIN)
                			.body(
                					fromObject(
                							Mono.just("Deleted")
                							)
                					)
                		);
	}
	
	public Mono<ServerResponse> fetchProductEvents(ServerRequest request){
		String product_id = request.pathVariable("productId");
		return ServerResponse
        			.ok()
        			.contentType(MediaType.TEXT_EVENT_STREAM)
        			.body(
        				productRepository.findById(product_id)
                        	.flatMapMany(product -> {
                           	 Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                           	 Flux<Product> productEventFlux =
                                    Flux.fromStream(
                                            Stream.generate(() -> product)
                                    );
                            return Flux.zip(interval, productEventFlux).map(Tuple2::getT2);
                        	}), Product.class
        		);
		}
}