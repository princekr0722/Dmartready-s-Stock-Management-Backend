package com.dmart.controller;

import java.time.Duration;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin("*")
public class TestController {

	@GetMapping("/hello")
	public Flux<String> sayHelloThenHi() {
		Flux<Long> delayFlux = Flux.interval(Duration.ofSeconds(5));

		return Flux.concat(Mono.just("Hello"), delayFlux.map(i -> "<h3>Hello</h3>"));
	}

}
