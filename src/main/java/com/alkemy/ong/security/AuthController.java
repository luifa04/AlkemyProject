package com.alkemy.ong.security;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.model.User;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO login, BindingResult bindingResult) {

		try {
			if (bindingResult.hasErrors())
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

			return new ResponseEntity<User>(authenticationServiceImpl.signInAndReturnJWT(login), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("{ok = false }" + "error: " + e.getLocalizedMessage(), HttpStatus.CONFLICT);
		}

	}

}
