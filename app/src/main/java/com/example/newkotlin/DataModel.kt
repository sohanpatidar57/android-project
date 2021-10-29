package com.example.newkotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataModel( var status: String, var message: String,var data:Data)
class Data(var user_id:String,var email_address:String,var mobile_no:String)