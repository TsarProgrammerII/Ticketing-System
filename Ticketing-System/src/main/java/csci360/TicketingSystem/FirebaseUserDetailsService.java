package csci360.TicketingSystem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class FirebaseUserDetailsService implements UserDetailsService {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            UserRecord userRecord = firebaseAuth.getUserByEmail(username);
            return User.withUsername(userRecord.getEmail())
                       .password("FirebasePasswordPlaceholder") // Firebase doesn't expose the password directly
                       .roles("USER")
                       .build();
        } catch (FirebaseAuthException e) {
            // Handle exception, user not found or other authentication issues
            throw new RuntimeException("Error fetching user from Firebase", e);
        }
    }
}
