package study.khs.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import study.khs.websocket.interceptor.WebSocketInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/queue", "/topic");
		config.setApplicationDestinationPrefixes("/app");
		//config.setUserDestinationPrefix("/user");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/register").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.setInterceptors(webSocketInterceptor());
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		registration.setInterceptors(webSocketInterceptor());
	}

	@Bean
	public WebSocketInterceptor webSocketInterceptor() {
		return new WebSocketInterceptor();
	}
}
