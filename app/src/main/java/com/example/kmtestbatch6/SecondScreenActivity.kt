package com.example.kmtestbatch6

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmtestbatch6.Adapter.UserChoosenAdapter
import com.example.kmtestbatch6.databinding.ActivitySecondScreenBinding
import com.example.kmtestbatch6.viewmodel.UserChooseViewModel

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var adapter: UserChoosenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        sharedPref = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)

        binding.tvName.text = sharedPref.getString("name","")
        binding.btnChooseUser.setOnClickListener {
            startActivity(Intent(this, ThirdScreenActivity::class.java))
        }
        showChoosenUser()
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun showChoosenUser() {
        val viewModel = ViewModelProvider(this)[UserChooseViewModel::class.java]
        viewModel.getUser()?.observe(this) {
            if (it != null) {
                binding.tvSelected.text = ""
                binding.rvUser.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )
                adapter = UserChoosenAdapter(it)
                adapter.notifyDataSetChanged()
                binding.rvUser.adapter = adapter
                binding.rvUser.setHasFixedSize(true)

                adapter.onCardClick = {
                    viewModel.deleteUser(it.id,it.firstName,it.lastName,it.avatar,it.email)
                }
            }
        }

    }
}