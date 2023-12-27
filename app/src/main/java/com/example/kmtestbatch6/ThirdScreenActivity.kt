package com.example.kmtestbatch6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kmtestbatch6.Adapter.UserAdapter
import com.example.kmtestbatch6.databinding.ActivityThirdScreenBinding
import com.example.kmtestbatch6.model.UserData
import com.example.kmtestbatch6.viewmodel.UserChooseViewModel
import com.example.kmtestbatch6.viewmodel.UserViewModel

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        showDataUser()
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, SecondScreenActivity::class.java))
        }
    }

    private fun showDataUser() {
        var page = 1
        val viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val viewModelDb = ViewModelProvider(this)[UserChooseViewModel::class.java]
        viewModel.getUserData().observe(this) {
            if (it != null) {
                binding.rvUser.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )
                binding.rvUser.setHasFixedSize(true)
                val filterUser: MutableList<UserData> = ArrayList()
                for (i in it.data) {
                    filterUser.add(i)
                }
                adapter = UserAdapter(filterUser)
                binding.rvUser.adapter = adapter
                adapter.notifyDataSetChanged()

                adapter.onCardClick = {
                    viewModelDb.addUser(it.id,it.firstName,it.lastName,it.avatar,it.email)
                    Toast.makeText(this, "Success Add User ${it.firstName} ${it.lastName}", Toast.LENGTH_SHORT).show()
                }

            }
        }
        binding.swipeRefresh.setOnRefreshListener {
            if (page != 2) {
                page += 1
                viewModel.callUserData(page)
                binding.swipeRefresh.isRefreshing = false
            } else {
                page = 1
                viewModel.callUserData(page)
                binding.swipeRefresh.isRefreshing = false
            }
        }
        viewModel.callUserData(page)
    }
}