package com.vsshv.doitlater.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseService{

    private var mFirebaseAuth: FirebaseAuth? = null

    private var mFirebaseDatabase: FirebaseDatabase? = null

    private var mDatabaseRef: DatabaseReference? = null

    private fun initialize(){

        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseDatabase = FirebaseDatabase.getInstance()
    }

}