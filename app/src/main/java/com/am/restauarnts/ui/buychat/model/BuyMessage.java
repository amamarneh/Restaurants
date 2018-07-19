package com.am.restauarnts.ui.buychat.model;


import com.google.firebase.firestore.DocumentReference;

import java.util.Date;

public class BuyMessage {
    public static final int TYPE_NON = 0;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_CART = 2;
    public static final int TYPE_LOADING = 3;
    public static final int TYPE_ACCEPT= 4;
    public static final int TYPE_REJECT = 5;
    public static final int TYPE_REPORT = 6;
    public static final int TYPE_ITEM_ADDED = 7;
    public static final int TYPE_ITEM_DELETED = 8;
    public static final int TYPE_ITEM_CHANGED = 9;

    public int getType(){return type;}

    protected int type;

    public void setType(int type) {
        this.type = type;
    }

    public Object getCreated() {
        return created;
    }

    public void setCreated(Object created) {
        this.created = created;
    }

    private Object created;
    private String sender_id;
    private String sender_name;
    private String id;
    private DocumentReference reference;

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }



    public DocumentReference getReference() {
        return reference;
    }

    public void setReference(DocumentReference reference) {
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public BuyMessage() {
        created = new Date();
    }
}
