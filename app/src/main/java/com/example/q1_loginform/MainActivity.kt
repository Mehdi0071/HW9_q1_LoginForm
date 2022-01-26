package com.example.q1_loginform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.add
import com.example.q1_loginform.databinding.ActivityFragmentFirstBinding
import com.example.q1_loginform.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityMainBinding.inflate(layoutInflater)
        lateinit var username: String
        lateinit var fullname :String
        lateinit var email : String
        lateinit var password : String

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPref = this.getSharedPreferences("userinformation", MODE_PRIVATE)

        supportFragmentManager.commit{
            setReorderingAllowed(true)
            add<FirstFragment>(R.id.container1)
        }

        supportFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->
            if (fragment is FirstFragment) {
                val firstFragment =
                    supportFragmentManager.findFragmentById(R.id.container1) as FirstFragment
                firstFragment.onDataStore = object : FirstFragment.OnDataStore {

                    override fun dataStore(id : List<Int>) {
                        val list = mutableListOf<String>()
                        with(binding) {
                            username = findViewById<TextInputEditText>(id[1]).text.toString()
                            fullname = findViewById<TextInputEditText>(id[0]).text.toString()
                            email = findViewById<TextInputEditText>(id[2]).text.toString()
                            password = findViewById<TextInputEditText>(id[3]).text.toString()
                            list.add(fullname); list.add(username)
                            list.add(email); list.add(password)

                        }
                        supportFragmentManager.commit {
                            val bundle = bundleOf("userinformation" to list)
                            setReorderingAllowed(true)
                            add<SecondFragment>(R.id.container2, args = bundle)
                        }
                        Toast.makeText(this@MainActivity, "Registered", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        supportFragmentManager.addFragmentOnAttachListener { fragmentManager, fragment ->
            if (fragment is SecondFragment) {
                val secondFragment =
                    supportFragmentManager.findFragmentById(R.id.container2) as SecondFragment
                secondFragment.onSaveListener = object : SecondFragment.OnSaveListener {
                    override fun onSave() {
                        val editor = sharedPref.edit()
                        editor.apply {
                            putString("username", username)
                            putString("fullname", fullname)
                            putString("email", email)
                            putString("password", password)
                        }.apply()
                        supportFragmentManager.commit {
                            remove(secondFragment)
                        }
                        Toast.makeText(this@MainActivity, "Information Saved", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }




    }

}