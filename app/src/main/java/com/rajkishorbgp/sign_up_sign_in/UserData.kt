package com.rajkishorbgp.sign_up_sign_in

import android.provider.ContactsContract.CommonDataKinds.Email

data class User(val firstName : String? = null,val lastName : String? = null,
                val age : String? = null,val userName : String? = null, val email: String? =null){
}