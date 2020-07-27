package br.com.apigetway.jwt;

import br.com.apigetway.jwt.model.JwtRequest;
import br.com.apigetway.jwt.model.JwtResponse;
import br.com.apigetway.jwt.util.JwtTokenUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@Data
@RequestMapping(value = "/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtTokenUtil jwtToken;

    @Autowired
    private UserDetailsService jwtUserDataService;



    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest request)
            throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = jwtUserDataService
                .loadUserByUsername(request.getUsername());

        final String token = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate (String user, String pass) throws Exception {
        Objects.requireNonNull(user);
        Objects.requireNonNull(pass);

        try {
            auth.authenticate(new UsernamePasswordAuthenticationToken(user, pass));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESABILITADO ", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIAIS INVALIDAS", e);
        }
    }

}
