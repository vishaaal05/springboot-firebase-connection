package com.learn.firebase.springbootfirebasedemo.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.learn.firebase.springbootfirebasedemo.entities.Product;

@Service
public class ProductService {
	
	private static final String COLLECTION_NAME = "products";

// post product details
	public String saveProduct(Product product) throws ExecutionException, InterruptedException {
	Firestore dbFirestore = FirestoreClient.getFirestore();
	
    ApiFuture<WriteResult> collectionApiFuture =  dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);

	return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	
//get products 
	public Product getProductDetails(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		
		DocumentSnapshot document = future.get();
		
		Product product = null;
		
		if (document.exists()) {
			product = document.toObject(Product.class);
			
			return product;
		}else {
			return null;
		}
		
		
	}
// update products
	public String updateProduct(Product product) throws ExecutionException, InterruptedException {
	Firestore dbFirestore = FirestoreClient.getFirestore();
	
    ApiFuture<WriteResult> collectionApiFuture =  dbFirestore.collection(COLLECTION_NAME).document(product.getName()).set(product);

	return collectionApiFuture.get().getUpdateTime().toString();
	}

// delete products
	public String deleteProduct(String name) throws ExecutionException, InterruptedException {
	Firestore dbFirestore = FirestoreClient.getFirestore();
	
    ApiFuture<WriteResult> collectionApiFuture =  dbFirestore.collection(COLLECTION_NAME).document(name).delete();

	return "Document with Product ID" + name + "has been deleted successfully";
	}
	
}
