package com.example.newkotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var pass : EditText
    private lateinit var forgot : TextView
    private lateinit var login_button : Button
    lateinit var progerssProgressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.email)
        pass = findViewById(R.id.password)
        forgot = findViewById(R.id.forgot_password)
        login_button = findViewById(R.id.login_button)


        progerssProgressDialog= ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)


        login_button.setOnClickListener(View.OnClickListener {
            val mUsername: String = email.getText().toString().trim()
            val password: String = pass.getText().toString().trim()
            when {
                mUsername.isEmpty() -> {
                    email.setError("Please enter email")
                }
                password.isEmpty() -> {
                    pass.setError("Please enter password")
                }
                else -> {
                loginApiCall(mUsername,password)
                }
            }
        })
    }

    private fun loginApiCall( username: String, upass: String) {
        progerssProgressDialog.show()
        val call: Call<DataModel> = ApiClient.getClient.getPhotos(username,upass,"passenger","23615","android")
        call.enqueue(object : Callback<DataModel> {

            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                progerssProgressDialog.dismiss()
                Log.e("response",""+response)
                if (response != null) {
                    if (response.isSuccessful){
                        val msg = response.body()?.message
                        val sts = response.body()?.status
                        var  email = response.body()?.data?.email_address
                        Log.e("response","Om Namaha Shivayha:"+sts+msg+",email: "+email)
                    }
                }
            }

            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                progerssProgressDialog.dismiss()
            }

        })
    }
}