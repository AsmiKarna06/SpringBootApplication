package org.impelsys.apigateway;


import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
//@EnableHystrix
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
		log.info("In customRouteLocator");
		return builder.routes()
				.route(r -> r.path("/employee/**")
				//.and().method("POST","PUT","DELETE")
				//.and().host("employee-admin.com")
				.filters(f->f.circuitBreaker(c->c.setName("CircuitBreaker").setFallbackUri("forward:/empServiceFallback")))
				.uri("lb://EMPLOYEE-SERVICE-V2"))
				.route(r -> r.path("department/**")
				.filters(f->f.circuitBreaker(c->c.setName("CircuitBreaker").setFallbackUri("forward:/deptServiceFallback")))
				.uri("lb://DEPARTMENT-SERVICE"))
				//.route(r -> r.path("employee/**")
				//.and().predicate(predicate->)
				//.uri("http://localhost:9091"))
				.build();
	}
//	@Bean
//	public FallbackHeadersGatewayFilterFactory fallbackHeadersGatewayFilterFactory() {
//		return new FallbackHeadersGatewayFilterFactory();
//	}
//	
//	@Bean
//	@ConditionalOnMissingBean(ReactiveCircuitBreakerFactory.class)
//	public ReactiveCircuitBreakerFactory<?, ?> reactiveCircuitBreakerFactory(){
//		ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory();
//		return factory;
//	}
//	
//	@Bean("springCloudCircuitBreakerFilterFactory")
//	public SpringCloudCircuitBreakerFilterFactory resilience4jCircuitBreakerFactory(
//			ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory,
//			ObjectProvider<DispatcherHandler> dispatcherHandlers) {
//		return new SpringCloudCircuitBreakerResilience4JFilterFactory(reactiveCircuitBreakerFactory, dispatcherHandlers);
//	}

//	@Bean
//	public Resilience4JCircuitBreakerFactory factory() {
//		return new Resilience4JCircuitBreakerFactory();
//	}
	
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(){
		log.info("in defaultCutomizer");
		//the circuit breaker should be up if 70% of the last 20 calls failed:
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.slidingWindowType(SlidingWindowType.COUNT_BASED)
				.slidingWindowSize(20)
				.slowCallRateThreshold(70.0f)
				.slowCallDurationThreshold(Duration.ofSeconds(5))
				.minimumNumberOfCalls(10)
				.failureRateThreshold(70.0f) //in percentage
				.build();

		//timebased the circuit breaker should be open if 70% request failed in the last 20 seconds.
//		CircuitBreakerConfig circuitBreakerConfig1 = CircuitBreakerConfig.custom()
//				.slidingWindowType(SlidingWindowType.COUNT_BASED)
//				.slidingWindowSize(20)
//				.failureRateThreshold(50.0f)
//				.minimumNumberOfCalls(12)
//				.build();
		
		//open the circuit breaker if 70% of the calls in the last 15seconds took more than 5 seconds to complete
//		CircuitBreakerConfig circuitBreakerConfig2 = CircuitBreakerConfig.custom()
//				.slidingWindowType(SlidingWindowType.TIME_BASED)
//				.slidingWindowSize(15) //seconds
//				.failureRateThreshold(70.0f)
//				.minimumNumberOfCalls(10)
//				.slowCallDurationThreshold(Duration.ofSeconds(5))
//				.build();
		
//		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.ofDefaults();	
		TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(20)).build();
		/*
		 ReactiveResilience4JCircuitBreakerFactory factory = new Resilience4JConfigBuilder("CircuitBreaker").build();
		 Resilience4JConfigBuilder resilienceConfigBuilder = new Resilience4JConfigBuilder("CircuitBreaker");
		 resilienceConfigBuilder.circuitBreakerConfig(CircuitBreakerConfig);
		 */
		return factory->factory.configureDefault(id->new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(circuitBreakerConfig).timeLimiterConfig(timeLimiterConfig).build());
	}
	
}
