package com.dmart.config.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//import com.practice.websocket.exception.UserException;
//import com.practice.websocket.securityconfig.JwtTokenValidationFilter;
//import com.practice.websocket.securityconfig.SpringSecurityConstants;

import org.springframework.messaging.support.ChannelInterceptor;

//@Component
public class AuthChannelInterceptorAdapter 
//implements ChannelInterceptor 
{

//	@Override
//	public Message<?> preSend(final Message<?> message, final MessageChannel channel) throws AuthenticationException {
//		final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//
////		String jwt = accessor.getFirstNativeHeader(SpringSecurityConstants.JWT_HEADER);
//		if (StompCommand.CONNECT == accessor.getCommand()) {
//
////			if (jwt != null) {
////				final UsernamePasswordAuthenticationToken user = jwtTokenValidationFilter
////						.getUsernamePasswordAuthenticationToken(jwt);
////				accessor.setUser(user);	
////			}
//
//		} else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
////			String destination = accessor.getDestination();
////			String username = destination.substring(destination.lastIndexOf("/") + 1);
////
////			if(jwt != null) {
////				
////				final UsernamePasswordAuthenticationToken user = jwtTokenValidationFilter
////						.getUsernamePasswordAuthenticationToken(jwt);
////				
////				if(username.equals(user.getName())) {
////					accessor.setUser(user);
////				} else {
////					throw new UserException("User is trying to subscribe another user's queue.");
////				}
////			}
//		} else if(StompCommand.SEND == accessor.getCommand()) {
////			String destination = accessor.getDestination();
////			String username = destination.substring(destination.lastIndexOf("/") + 1);
////			
////			System.out.println("\n"+destination);
////			System.out.println(jwt);
////			System.out.println(username);
//			
//		}
//		
////		TODO remove it later
//		accessor.setUser(new UsernamePasswordAuthenticationToken(null, null));
//		return message;
//	}
}