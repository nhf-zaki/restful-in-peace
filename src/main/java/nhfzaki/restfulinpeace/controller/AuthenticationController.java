package nhfzaki.restfulinpeace.controller;

import nhfzaki.restfulinpeace.domain.Role;
import nhfzaki.restfulinpeace.domain.User;
import nhfzaki.restfulinpeace.domain.enumeration.RoleName;
import nhfzaki.restfulinpeace.exception.AppException;
import nhfzaki.restfulinpeace.web.rest.*;
import nhfzaki.restfulinpeace.repository.RoleRepository;
import nhfzaki.restfulinpeace.repository.UserRepository;
import nhfzaki.restfulinpeace.security.JwtTokenProvider;
import nhfzaki.restfulinpeace.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * @author nhf-zaki on 12/19/18
 */
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return new ResponseEntity<>(new LoginResponse(StatusTypeConstants.STATUS_LOGGED_IN, userPrincipal.getFirstName(), userPrincipal.getUsername()), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            return new ResponseEntity(new CustomResponse(StatusTypeConstants.STATUS_REJECTED, "Email already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating User's Account
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
                () -> new AppException("User Role not set.")
        );
        User user = new User();
        user.setFirstName(registrationRequest.getFirst_name());
        user.setLastName(registrationRequest.getLast_name());
        user.setEmail(registrationRequest.getEmail());
        user.setMobile(registrationRequest.getMobile());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{email}")
                .buildAndExpand(result.getEmail()).toUri();

        return ResponseEntity.created(location).body(
                new RegistrationResponse(
                        result.getFirstName(),
                        result.getLastName(),
                        result.getEmail(),
                        result.getMobile(),
                        StatusTypeConstants.STATUS_SUCCESS));
    }
}
