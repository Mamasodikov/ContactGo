package com.mamasodikov.contactgo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.mamasodikov.contactgo.databinding.ActivityMainBinding
import com.mamasodikov.contactgo.main_fragments.PrivateCards
import com.mamasodikov.contactgo.main_fragments.PublicCards


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)

    lateinit var b: ActivityMainBinding

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val pref:SharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        val currentUser: String? = pref.getString("current_user","user1")
        val editor: SharedPreferences.Editor = pref.edit()

        /***DAnger ZOne****/                    /***DAnger ZOne****/      /***Tegma****/      /***DAnger ZOne****/
            FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        /***DAnger ZOne****/    /******Boshqa joyga ko'chirma*****/         /***DAnger ZOne****/            /***DAnger ZOne****/

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
/***********Setting up Nav Bar username ****************************/
        val header: View = b.navigationView.getHeaderView(0);
        val username =  header.findViewById(R.id.username) as TextView
        username.setText(currentUser);
/************************************************************************/
        val drawerToggle = ActionBarDrawerToggle(this, b.drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        b.drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()



        b.navigationView.setNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.exit -> {
                    editor.putString("status","unlogged").apply()
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("Akkountdan chiqasizmi?")
                        .setCancelable(false)
                        .setPositiveButton("Ha") { dialog, id ->

                            Handler(Looper.getMainLooper()).postDelayed({
                                // Your Code
                            }, 300)

                            val intent = Intent(this, LoginScreen::class.java)
                            startActivity(intent)

                            Process.killProcess(Process.myPid())

                        }
                        .setNegativeButton("Yo'q") { dialog, id ->
                            // Bekor qilish
                            editor.putString("status","logged").apply()
                            dialog.dismiss()
                        }
                    val alert = builder.create()
                    alert.show()
                }

                R.id.about->{
                    val intent = Intent(this, AboutUs::class.java)
                    startActivity(intent)
                }
                R.id.nav_share->
                {
                    val intent = Intent(this, PromoteActivity::class.java)
                    startActivity(intent)

                }
                R.id.nav_contact->
                {
                    val address = "support@contactgo.uz"
                    composeEmail(address,"Ticket N#00001")
                }
            }
            true
        }


        // Write a message to the database

        val prCards= PrivateCards()
        val pbCards= PublicCards()

        setCurrentFragment(prCards)

        b.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.private_item->setCurrentFragment(prCards)
                R.id.public_item->setCurrentFragment(pbCards)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commitNow()
        }

    @SuppressLint("QueryPermissionsNeeded")
    fun composeEmail(address:String, subject: String?, addresses: ArrayList<String> = ArrayList()) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:" + address) // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (b.drawer.isDrawerOpen(GravityCompat.START)) {
            b.drawer.closeDrawer(GravityCompat.START)
        } else {
            Process.killProcess(Process.myPid())
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            android.R.id.home -> {
                b.drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
