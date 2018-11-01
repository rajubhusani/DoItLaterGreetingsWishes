package com.vsshv.doitlater.base.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment

abstract class BaseDialogView : DialogFragment(), DialogMVPView {

    private var parentActivity : BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showProgress(){

    }

    override fun hideProgress(){

    }
}