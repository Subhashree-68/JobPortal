package com.example.jobPortal.Config;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	public static String secret="";
	
	public JwtService() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator=KeyGenerator.getInstance("hmacSHA256");
		SecretKey auth=keyGenerator.generateKey();
		secret=Base64.getEncoder().encodeToString(auth.getEncoded());
	}

	public String generateToken(String email) {
		Map<String ,String> claims=new HashMap();
		return createToken(claims,email);
	}

	private String createToken(Map<String, String> claims, String email) {
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(email)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60))
				.and()
				.signWith(getKey())
				.compact();
	}

	private SecretKey getKey() {
		byte[] keyByte=Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyByte);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		 String userName=extractUsername(token);
		return (userName.equals(userDetails.getUsername() ) && !isTokenExpired(token));
	
	}
	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date(System.currentTimeMillis()));
	}

	private Date extractExpiration(String token) {		
		return extractClaims(token, Claims::getExpiration);
	}

	public String extractUsername(String token) {
		return extractClaims(token,Claims::getSubject);
	}

	private <T>T extractClaims(String token, Function<Claims,T> ClaimsResolver) {
		final Claims claims=extractAllClaims(token);
		return ClaimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	
}
