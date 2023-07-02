package telran.java47.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class UserProfile extends User {

	private static final long serialVersionUID = -2361082017889073956L;
	private boolean passwordNotExpired;

	public UserProfile(String username, String password, Collection<? extends GrantedAuthority> authorities,
			boolean passwordNotExpired) {
		super(username, password, authorities);
		this.passwordNotExpired = passwordNotExpired;
	}	

}
