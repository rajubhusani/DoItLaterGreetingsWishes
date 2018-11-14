package com.vsshv.doitlater.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.vsshv.doitlater.R
import com.vsshv.doitlater.ui.common.FButton
import com.vsshv.doitlater.ui.common.RotateLoading
import com.vsshv.doitlater.utils.PreferenceHelper
import com.vsshv.doitlater.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

open class LoginFragment: Fragment(){

    @BindView(R.id.userName)
    lateinit var mUserName: TextInputEditText

    @BindView(R.id.password)
    lateinit var mPassword: TextInputEditText

    @BindView(R.id.forgot)
    lateinit var mForgot: TextView

    @BindView(R.id.login)
    lateinit var mLogin: FButton

    @BindView(R.id.fb)
    lateinit var mFB: FButton

    @BindView(R.id.gmail)
    lateinit var mGmail: FButton

    @BindView(R.id.rotateloading)
    lateinit var rotateloading: RotateLoading

    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var callbackManager: CallbackManager

    private lateinit var auth: FirebaseAuth

    companion object {
        private const val TAG = "LoginFragment"
        private const val RC_SIGN_IN = 9001
    }

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View = inflater.inflate(R.layout.activity_login, container, false)

        ButterKnife.bind(this, view)

        auth = FirebaseAuth.getInstance()

        mGmail.setOnClickListener{view -> googleSign()}

        mFB.setOnClickListener{view -> fbSign()}

        mLogin.setOnClickListener{view -> signIn()}

        configureGoogleSignIn()

        configureFB()

        return view
    }

    private fun signIn(){
        rotateloading.start()
        val user = mUserName.text.toString()
        val pass = mPassword.text.toString()
        if(loginViewModel.validateLogin(user, pass)){
            loginUser(user, pass)
        }else{
            //TODO Show error for Invalid Credentials
        }
    }

    private fun configureGoogleSignIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(context!!, gso)
    }


    private fun googleSign(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
                updateUI(null)
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
     //   showProgressDialog()

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Snackbar.make(gmail, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                  //  hideProgressDialog()
                }
    }

    private fun configureFB(){
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }
            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
                updateUI(null)
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
                updateUI(null)
            }
        })
    }

    private fun fbSign(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile", "user_friends"))
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        // showProgressDialog()

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(activity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                    //hideProgressDialog()
                }
    }


    private fun loginUser( email: String, password: String) {
        var user : FirebaseUser? = null
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {task->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(activity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                         updateUI(null)
                        user = null
                    }

                }
    }

    private fun updateUI(user: FirebaseUser?) {
        rotateloading.stop()
        Log.d(TAG, "UID:::"+user?.uid)
        if(null != user){
            loginViewModel.updateUserToFirebase(user)
            val intent = Intent(activity, DashboardActivity::class.java)
            activity?.startActivity(intent)

            val prefHelper: PreferenceHelper? = PreferenceHelper.getInstance(context)
            prefHelper?.setValue("iSLogged", true)
        }

    }

}