package com.learn.firebase.springbootfirebasedemo.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Service
public class FirebaseIntialization {

	@PostConstruct
	public void intialization() throws IOException {
		FileInputStream serviceAccount = null;
		
		try {
			serviceAccount =  new FileInputStream("./serviceAccountKey.json");
			
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .build();

					FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
				

				

	}
}
