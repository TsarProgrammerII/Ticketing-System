package csci360.TicketingSystem;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.*;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream serviceAccountStream = classLoader.getResourceAsStream("static/cofc-ticketing-system-firebase-adminsdk-6hbbf-8cc0040015.json");
        if (serviceAccountStream == null)
        {
            throw new FileNotFoundException("File not found: static/cofc-ticketing-system-firebase-adminsdk-6hbbf-8cc0040015.json");
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance(initializeFirebase());
    }

    public FirebaseToken verifyFirebaseToken(String idToken) throws FirebaseAuthException {
        try {
        return firebaseAuth().verifyIdToken(idToken);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
