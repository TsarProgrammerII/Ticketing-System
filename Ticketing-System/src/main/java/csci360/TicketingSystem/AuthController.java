package csci360.TicketingSystem;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody String idToken) {
        try {
            FirebaseToken firebaseToken = new FirebaseConfig().verifyFirebaseToken(idToken);
            // Authentication successful, you can now use firebaseToken to get user information
            String uid = firebaseToken.getUid();
            // Perform additional logic as needed
            return "Authentication successful for UID: " + uid;
        } catch (FirebaseAuthException e) {
            // Authentication failed, handle the exception
            e.printStackTrace();
            return "Authentication failed";
        }
    }
}
