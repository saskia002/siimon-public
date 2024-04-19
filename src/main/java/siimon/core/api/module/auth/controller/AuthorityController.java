package siimon.core.api.module.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/v2/auth/authority")
public class AuthorityController {

	@GetMapping
	public String getAllAuthorities() {
		return "all Auth";
	}

	@GetMapping("/{id}")
	public String getAuthority(@PathVariable("id") Integer id) {
		return "Auth" + id;
	}

	@PostMapping
	public String addNewAuthority() {
		return "new auth";
	}

	@DeleteMapping("/{id}")
	public String deleteAuthority(@PathVariable("id") Integer id) {
		return "deleted auth" + id;
	}

	@PutMapping("/{id}")
	public String updateAuthority(@PathVariable("id") Integer id) {
		return "updated auth" + id;
	}

}
