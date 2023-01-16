package com.codewithosm.foodapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codewithosm.foodapp.adapter.MenuAdapter
import com.codewithosm.foodapp.adapter.ResturantAdapter
import com.codewithosm.foodapp.databinding.ActivityResturantBinding
import com.codewithosm.foodapp.mdel.Menu
import com.codewithosm.foodapp.mdel.ResturantResponseItem

class ResturantActivity : AppCompatActivity(), MenuAdapter.Action {

    private lateinit var binding: ActivityResturantBinding
    private lateinit var menuAdapter: MenuAdapter
    private var itemTotalCountInCart = 0
    private var itemMenuList = ArrayList<Menu>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResturantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resturantmodel = intent.getParcelableExtra<ResturantResponseItem>("resturantmodel")
        val actionbar = supportActionBar
        actionbar?.subtitle = resturantmodel?.address
        actionbar?.title = resturantmodel?.name
        actionbar?.setDisplayHomeAsUpEnabled(true)
        initiRecylerview(resturantmodel!!.menus)
        initButton(resturantmodel)
    }

    private fun initButton(model: ResturantResponseItem) {

        binding.tvCheck.setOnClickListener {
            if (itemMenuList != null && itemMenuList.size <= 0) {
                Toast.makeText(this, "Please add some items ", Toast.LENGTH_LONG).show()
            } else {
                model.menus = itemMenuList
                val intent = Intent(this, PlaceyourOrderActivity::class.java)
                intent.putExtra("model", model)
                startActivity(intent)
            }
        }
    }


    private fun initiRecylerview(menu: List<Menu>) {
        menuAdapter = MenuAdapter(this)
        binding.rvMenu.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = menuAdapter
            setHasFixedSize(true)
        }
        menuAdapter.submitList(menu)
        menuAdapter.notifyDataSetChanged()
    }


    @SuppressLint("SetTextI18n")
    override fun onItemClickADD(menu: Menu) {
        itemMenuList.add(menu)
        itemTotalCountInCart = 0
        for (menu in itemMenuList) {
            itemTotalCountInCart += menu.totalCount
        }
        Log.e("", "${itemTotalCountInCart}")
        binding.tvCheck.text = "Check out($itemTotalCountInCart)"
    }

    override fun onItemClickRemove(menu: Menu) {
        if (itemMenuList.contains(menu)) {
            itemMenuList.remove(menu)
            itemTotalCountInCart = 0
            for (menu in itemMenuList) {
                itemTotalCountInCart += menu.totalCount
            }
            Log.e("", "${itemTotalCountInCart}")
            binding.tvCheck.text = "Check out(" + itemTotalCountInCart + ")"
        }
    }

    override fun onItemClickUpdate(menu: Menu) {
        val index = itemMenuList.indexOf(menu)
        itemMenuList.removeAt(index)
        itemMenuList.add(menu)
        itemTotalCountInCart = 0
        for (menu in itemMenuList) {
            itemTotalCountInCart += menu.totalCount
        }
        Log.e("", "${itemTotalCountInCart}")
        binding.tvCheck.text = "Check out(" + itemTotalCountInCart + ")"
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}