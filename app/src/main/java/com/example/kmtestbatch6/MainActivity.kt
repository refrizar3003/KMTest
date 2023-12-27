package com.example.kmtestbatch6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.kmtestbatch6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)

        supportActionBar?.hide()

        binding.btnCheck.setOnClickListener {
            val palindrome = binding.edtPalindrome.text.toString()

            if (isPalindromeString(palindrome) == true) {
                Toast.makeText(this, "is a Palindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "not a Palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnNext.setOnClickListener {
            if (findViewById<EditText>(R.id.edt_name).text.toString().isEmpty()) {
                Toast.makeText(this, "Please input your name", Toast.LENGTH_SHORT).show()
            } else {
                val name = binding.edtName.text.toString()
                val save = sharedPref.edit()
                save.putString("name", name)
                save.apply()
                startActivity(Intent(this, SecondScreenActivity::class.java))
            }
        }

    }

    fun isPalindromeString(inputStr: String): Boolean {
        val sb = StringBuilder(inputStr)
        val reverseStr = sb.reverse().toString()
        return inputStr.equals(reverseStr, ignoreCase = true)
    }
}