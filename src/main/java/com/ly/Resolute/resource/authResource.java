package com.ly.Resolute.resource;

import com.ly.Resolute.model.User;
import com.ly.Resolute.resource.dto.LoginRequest;
import com.ly.Resolute.resource.dto.LoginResponse;
import com.ly.Resolute.resource.dto.RegisterDTO;
import com.ly.Resolute.service.RegisterService;
import com.ly.Resolute.service.UserService;
import com.ly.Resolute.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping("api/v1/auth")
public class authResource {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();
            User AuthorityFromUser = userService.findUserById(user.getId());
            user.setAuthorities((List<GrantedAuthority>) AuthorityFromUser.getAuthorities());
            LoginResponse Response = new LoginResponse(user, jwtUtil.generateToken(user));
            return new ResponseEntity<>(Response, HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO request) {
        try {
            registerService.Register(request.getUsername(), request.getPassword(), request.getFullName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
