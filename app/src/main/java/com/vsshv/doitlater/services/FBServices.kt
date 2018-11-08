package com.vsshv.doitlater.services

interface FBServices: UserService, EventService{

    fun googleSignIn()

    fun facebookSignIn()
}