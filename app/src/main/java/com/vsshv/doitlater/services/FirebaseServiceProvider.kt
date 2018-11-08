package com.vsshv.doitlater.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.vsshv.doitlater.utils.DBReferences

abstract class FirebaseServiceProvider : FBServices{

    protected constructor(){
        initialize()
    }

    private var mFirebaseAuth: FirebaseAuth? = null

    private var mFirebaseDatabase: FirebaseDatabase? = null

    private var mDatabaseRootRef: DatabaseReference? = null

    private fun initialize(){

        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseDatabase = FirebaseDatabase.getInstance()
    }

    protected fun getRootDatabaseRef(){
        mDatabaseRootRef = mFirebaseDatabase!!.reference.root
    }

    protected fun getUserDatabaseRef() : DatabaseReference{

        return mFirebaseDatabase!!.reference.child(DBReferences.USER_REF)
    }

    protected fun getEventsDatabaseRef() : DatabaseReference{

        return mFirebaseDatabase!!.reference.child(DBReferences.EVENTS_REF)
    }

    protected fun getFirebaseAuth(): FirebaseAuth{
        return mFirebaseAuth!!
    }

}