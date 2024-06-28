package com.example.cobafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        intent = intent
        firebaseUser = FirebaseAuth.getInstance().currentUser

        send_button.setOnclickListener{
            val message = text_message.text.toString()
            if (message == "") {
                Toast.makeText(this@ChatActivity, "Please write a message first... ", Toast.LENGTH_SHORT).show()

            }
            else {
                sendMessageToUser(firebaseUser!!)
            }
        }
    }
}