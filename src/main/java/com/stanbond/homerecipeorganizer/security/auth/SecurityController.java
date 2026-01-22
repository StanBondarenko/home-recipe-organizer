package com.stanbond.homerecipeorganizer.security.auth;

import com.stanbond.homerecipeorganizer.DAO.interfaces.DaoUserRole;
import com.stanbond.homerecipeorganizer.security.token.JwtCore;
import com.stanbond.homerecipeorganizer.security.token.JwtResponse;
import com.stanbond.homerecipeorganizer.security.user.User;
import com.stanbond.homerecipeorganizer.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest) {
        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setBirth(signUpRequest.getBirth());
        userService.register(user);
        userService.setRoleToUser(user.getUserId(),"USER");
        return ResponseEntity.status(HttpStatus.CREATED).body("User was created");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getIdentifier(),
                            signInRequest.getPassword()
                    )
            );

            String token = jwtCore.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(token));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
