# Spring Boot + Spring Security + JWT from scratch
- How to JWT authorization in Spring Security from scratch
- authentication api endpoint
- examine every incoming request for valid JWT and authorize

### Create App and add Security Config
This is the new set up the WebSecurityConfigurer is now deprecated
https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

```java

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

```
The configureGlobal function is used because I have added @AutoWired. It then refers to the MyUserDetailsService annotated
 with @Service:

```java
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("foo", "foo", Collections.emptyList());
    }
}
```
This implements the Spring UserDetailsService and returns a dummy user for testing. We would use a database connection to
load the user in a real application. We have also added a JWTUtil class with functions to generate and authenticate the jwt token:
```java


@Service
public class JWTUtil {
    //...
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.ES256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
```

# Next steps
### Step 1.
We now add a /authenticate API endpoint:
- accepts user ID and password
- returns JWT as response
First create authenticate endpoint to which we post the username and password. This then returns the jwt in the payload.
The client then uses the jwt in future requests as part of the header of requests to the server.

