package com.mamasodikov.contactgo

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.mamasodikov.contactgo.databinding.ActivityLoginScreenBinding

class LoginScreen : AppCompatActivity() {
    lateinit var b: ActivityLoginScreenBinding
    var isPersistanceEnabled:Boolean=false

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(b.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val pref:SharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        val editor: SharedPreferences.Editor = pref.edit()


        if (pref.getString("status","unlogged")=="logged"){
            b.user1.visibility= View.GONE
            b.user2.visibility= View.GONE
            b.loading.visibility= View.VISIBLE
            Glide.with(this).load(R.drawable.loading_anim_water).into(b.loading)

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@LoginScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)

        }

        else
        {
            b.user1.visibility= View.VISIBLE
            b.user2.visibility= View.VISIBLE
            b.loading.visibility= View.GONE
        }

        b.user1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                editor.putString("current_user", "user1")
                editor.putString("status","logged")
                editor.apply()

                val intent = Intent(this@LoginScreen, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        })


        b.user2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                editor.putString("current_user", "user2")
                editor.putString("status","logged")
                editor.apply()

                val intent = Intent(this@LoginScreen, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        })
    }
}