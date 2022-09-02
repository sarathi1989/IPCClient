package com.sf.ipcclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.sf.ipcserver.IMyAidlInterface

class MainActivity : AppCompatActivity() , View.OnClickListener {

     var imyAidlInterface : IMyAidlInterface? = null

    var isBound = false


    var txtFetchDetails : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBindService = findViewById<Button>(R.id.btnBindSerivce)
        val btnUnBindService = findViewById<Button>(R.id.btnUnBindService)
        val btnFetchDetails = findViewById<Button>(R.id.btnFetchDetails)
         txtFetchDetails = findViewById<TextView>(R.id.txtFetch)

        btnBindService.setOnClickListener(this)
        btnUnBindService.setOnClickListener(this)
        btnFetchDetails.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when(view?.id){

            R.id.btnBindSerivce -> callBindService()
            R.id.btnUnBindService -> callUnBindService()
            R.id.btnFetchDetails -> callFetchDetails()

        }
    }

    val conn : ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {

            Log.v("Sarathi","ServiceCOnnected")

            isBound = true

            imyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {

            isBound = false
            Log.v("Sarathi","ServiceDisCOnnected")
        }

    }

    private fun callFetchDetails() {

        txtFetchDetails?.text=""
        txtFetchDetails?.text="addintion of two number : ${imyAidlInterface?.getAddition(10,20)} :: getMessage : ${imyAidlInterface?.getMessage("Sarathi")}"


        Log.v("Sarathi","addintion of two number : ${imyAidlInterface?.getAddition(10,20)} :: getMessage : ${imyAidlInterface?.getMessage("Sarathi")}")

    }

    private fun callUnBindService() {
        if(isBound) {
            unbindService(conn)
            isBound=false
        }
    }

    private fun callBindService() {

        Intent("com.sf.ipcserver.MyService").setPackage("com.sf.ipcserver").also {
            intent ->
            bindService(intent,conn, BIND_AUTO_CREATE)
        }


       /*Intent("myservice").also { intent ->
           bindService(intent,conn, BIND_AUTO_CREATE)
       }*/

    }
}