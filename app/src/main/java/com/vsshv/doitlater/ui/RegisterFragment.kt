package com.vsshv.doitlater.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.vsshv.doitlater.R
import com.vsshv.doitlater.models.UserModel
import com.vsshv.doitlater.ui.common.FButton
import com.vsshv.doitlater.ui.common.RotateLoading
import com.vsshv.doitlater.utils.PreferenceHelper
import com.vsshv.doitlater.viewmodels.RegisterViewModel

open class RegisterFragment: Fragment(){

    companion object {
        private const val TAG = "RegisterFragment"
    }

    @BindView(R.id.fName)
    lateinit var fName: TextInputEditText

    @BindView(R.id.lName)
    lateinit var lName: TextInputEditText

    @BindView(R.id.email)
    lateinit var email: TextInputEditText

    @BindView(R.id.password)
    lateinit var password: TextInputEditText

    @BindView(R.id.mobile)
    lateinit var mobile: TextInputEditText

    @BindView(R.id.register)
    lateinit var register: FButton

    @BindView(R.id.rotateloading)
    lateinit var rotateloading: RotateLoading

    var model = UserModel()

    val registerVideModel : RegisterViewModel by lazy {
        ViewModelProviders.of(this).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View = inflater.inflate(R.layout.activity_register, container, false)

        ButterKnife.bind(this, view)

        register.setOnClickListener { view -> resgiterUser() }

        return view
    }

    fun resgiterUser(){
        rotateloading.start()
        model.firstName = fName.text.toString()
        model.lastName = lName.text.toString()
        model.emailId = email.text.toString()
        model.password = password.text.toString()
        model.mobileNumber = mobile.text.toString()

        if(registerVideModel.validateRegisterForm(model)){
            registerUser(model.emailId, model.password)
        }
            else
            Toast.makeText(activity, "Please verify fields", Toast.LENGTH_SHORT).show()
    }

    private fun registerUser(email: String, password: String){
        var user : FirebaseUser? = null
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {task->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        user = auth.currentUser
                        updateFirebase(user)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(activity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                        user = null
                        updateFirebase(null)
                    }
                }
    }

    private fun updateUI(user: FirebaseUser?) {
        Log.d(TAG, "UID:::"+user?.uid)
        rotateloading.stop()
        registerVideModel.updateUserToFirebase(user)
    }

    private fun updateFirebase(user: FirebaseUser?) {
        Log.d(TAG, "UID:::"+user?.uid)
        rotateloading.stop()
        if(null != user){
            registerVideModel.updateUserToFirebase(model, user)
            val intent = Intent(activity, DashboardActivity::class.java)
            activity?.startActivity(intent)

            val prefHelper: PreferenceHelper? = PreferenceHelper.getInstance(context)
            prefHelper?.setValue("iSLogged", true)
        }
    }
}