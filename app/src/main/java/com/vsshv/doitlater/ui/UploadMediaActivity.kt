package com.vsshv.doitlater.ui

import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import butterknife.BindView
import com.vsshv.doitlater.R
import com.vsshv.doitlater.ui.common.FButton

class UploadMediaActivity: AppCompatActivity(){

    @BindView(R.id.addImages)
    lateinit var mAddImages: Button

    @BindView(R.id.addVideo)
    lateinit var mAddVideo: Button

    @BindView(R.id.addAudio)
    lateinit var mAudio: Button

    @BindView(R.id.addGif)
    lateinit var mGif: Button

    @BindView(R.id.upload)
    lateinit var mUpload: FButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_upload)

    }

}