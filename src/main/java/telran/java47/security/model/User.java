package telran.java47.security.model;

import java.security.Principal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User implements Principal {	
	String username;
	@Getter
	Set<Role> roles;
	

	@Override
	public String getName() {
		return username;
	}

}
