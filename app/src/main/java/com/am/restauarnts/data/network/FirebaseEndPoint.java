package com.am.restauarnts.data.network;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirebaseEndPoint {

    public final static CollectionReference RESTAURANTS = FirebaseFirestore.getInstance().collection("restaurants");
    public final static CollectionReference FOOD = FirebaseFirestore.getInstance().collection("food");
    public final static CollectionReference TOP_FOOD = FirebaseFirestore.getInstance().collection("topFood");
    public final static CollectionReference ORDERS = FirebaseFirestore.getInstance().collection("orders");

    public static CollectionReference getCatRef(String rId){
        return FirebaseFirestore.getInstance().collection("restaurants").document(rId).collection("categories");
    }
    public static final CollectionReference CHAT = FirebaseFirestore.getInstance().collection("chat");
    public static final CollectionReference MESSAGES = FirebaseFirestore.getInstance().collection("messages");
    public static Query getRestaurantChats(String rId){
        return CHAT.whereEqualTo("restaurant_id",rId);
    }
    public static CollectionReference getChatMessages(String chatId){
        return MESSAGES.document(chatId).collection("messages");
    }
}
