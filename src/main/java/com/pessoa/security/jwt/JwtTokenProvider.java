package com.pessoa.security.jwt;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pessoa.exception.InvalidJwtAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {
	
	
	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
	

	@Value("${security.jwt.token.expire-lenght:3600000}")
	private long validyExpired = 3600000;//uma hora
	
	@Autowired
	private UserDetailsService userDetails;
	
	@PostConstruct
	private void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);//pasando nome do usuario 
		claims.put("roles",roles);//passando as roles do usuario na parte do payload do token
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + validyExpired );
		
		return  Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey)//assinatura
				.compact();	
	}
	
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails  = this.userDetails.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		
	}
	

	public String getUsername(String token) {
		//pega o token decodifica ele , pra pegar o body e o subject que é o nome do usuario
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
		
	}
	
	public String resolveToken(HttpServletRequest req ) {
		String bearerToken =  req.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
			
		}
		return null;
		
	}
	
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims =  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			if(claims.getBody().getExpiration().before(new Date())) {
				return false;
				
			}
			return true;
		} catch (Exception e) {
			throw new InvalidJwtAuthenticationException("Expired token or invalid");
		}
		
	}
	
	

}
