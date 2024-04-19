package siimon.core.api.module.auth.service.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import siimon.core.api.module.auth.model.AuthorityModel;
import siimon.core.api.module.auth.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserAuthDetails implements UserDetails {

	private final UserModel userModel;

	public UserAuthDetails(UserModel userModel) {
		this.userModel = userModel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (AuthorityModel auth : userModel.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuthority().toUpperCase()));
		}
		return authorities;
	}


	@Override public boolean isEnabled() { return !userModel.getDeleted(); }
	@JsonIgnore
	@Override public String getPassword() { return userModel.getPassword(); }
	@Override public String getUsername() { return userModel.getUsername().toLowerCase(); }
	@Override public boolean isCredentialsNonExpired() { return true; }
	@Override public boolean isAccountNonExpired() { return true; }
	@Override public boolean isAccountNonLocked() { return true; }

}
