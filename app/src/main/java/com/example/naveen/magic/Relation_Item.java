package com.example.naveen.magic;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Naveen on 20-10-2015.
 */
public class Relation_Item implements Serializable {
    private int _id;
    private Uri relationImageUri;
    private String relationName;

    public Relation_Item(int _id) {
        this._id = _id;
    }

    public Uri getRelationImageUri() {
        return relationImageUri;
    }

    public void setRelationImageUri(Uri relationImageUri) {
        this.relationImageUri = relationImageUri;
    }

    public int get_id() {
        return _id;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }
}
