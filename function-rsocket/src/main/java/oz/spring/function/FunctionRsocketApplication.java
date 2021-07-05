package oz.spring.function;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.support.MessageBuilder;


@SpringBootApplication
public class FunctionRsocketApplication {

	@Autowired
	private RSocketRequester.Builder rsocketRequesterBuilder;

	public static void main(String[] args) {
		SpringApplication.run(FunctionRsocketApplication.class, args);
	}


	@Bean
	public Function<Message<String>, Message<String>> function() {
		return message -> {
			/*
			 *
			 */
			RSocketRequester.Builder builder = rsocketRequesterBuilder;
			return MessageBuilder.withPayload("Successfully processed '" + message.getPayload() + "'").build();
		};
	}

}
