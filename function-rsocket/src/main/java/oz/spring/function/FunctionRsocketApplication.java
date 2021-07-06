package oz.spring.function;

import java.util.function.Function;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.support.MessageBuilder;

import reactor.core.publisher.Flux;


@SpringBootApplication
public class FunctionRsocketApplication {

	/*
	 * You can autowire is like here or you can also inject it into `function(RSocketRequester.Builder rsocketRequesterBuilder)` factory method.
	 */
	@Autowired
	private RSocketRequester.Builder rsocketRequesterBuilder;

	public static void main(String[] args) {
		SpringApplication.run(FunctionRsocketApplication.class, args);
	}


	@Bean
	public Function<Flux<Message<String>>, Flux<Message<String>>> function() {
		return flux -> flux.<Message<String>>map(message -> {
			String uri = (String) message.getHeaders().get("uri");
			/*
			 * - The message's payload is the body of HTTP request and its headers are HttpHeaders plus URI (see above),
			 *   so you have access to all of that.
			 * - Also, from the code below you can see that you can send and receive and instanceof Message.
			 * - For the `route(..)` call you typically provide the value of function definition you are attempting to invoke.
			 *   However, if utilizing function routing features by for example providing system property or header `spring.cloud.function.routing-expression`,
			 *   (e.g., --spring.cloud.function.routing-expression=headers.func_name) the value you put into `route(..)` is meaningless as it will fall back
			 *   on definition extracted from `routing-expression`, effectively giving you a more dynamic way to delegate to many different functions.
			 */

//			Mono<Message<String>> retrieveMono = rsocketRequesterBuilder.tcp("", 222)
//				.route("function definition")
//				.data(message)
//				.retrieveMono(new ParameterizedTypeReference<Message<String>>() {
//				});

			// This return is temporary. Replace it when actual rsocket call is made (see above commented code)
			return MessageBuilder.withPayload("Hello").build();
		});

	}

}
