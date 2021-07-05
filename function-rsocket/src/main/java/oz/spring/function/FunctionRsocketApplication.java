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

	@Autowired
	private RSocketRequester.Builder rsocketRequesterBuilder;

	public static void main(String[] args) {
		SpringApplication.run(FunctionRsocketApplication.class, args);
	}


	@Bean
	public Function<Flux<Message<String>>, Flux<Message<String>>> function() {
		return flux -> flux.<Message<String>>map(message -> {
			String uri = (String) message.getHeaders().get("uri");
//			Mono<Message<String>> retrieveMono = rsocketRequesterBuilder.tcp("", 222)
//				.route("pojoToString")
//				.data(message)
//				.retrieveMono(new ParameterizedTypeReference<Message<String>>() {
//				});
			return MessageBuilder.withPayload("Hello").build(); // temporary, replace when actual rsocket call is made (see above commented code)
		});

	}

}
