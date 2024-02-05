package com.nomnom.popupwindow

import android.os.Bundle
import android.widget.ListPopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nomnom.popupwindow.databinding.ActivityMainBinding
import com.nomnom.popupwindow.util.ListPopupWindowUtil

class MainActivity : AppCompatActivity() {


    private lateinit var listPopupWindow: ListPopupWindow
    private lateinit var binding: ActivityMainBinding
    private var list: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()


    }

    private fun initComponents() {


        initList()
        initClicks()
        initPopWindow()


    }

    private fun initPopWindow() {


        listPopupWindow = ListPopupWindowUtil.getPlainListPopUpWindow(
            this, list,
            binding.btnShowPopup,
            R.drawable.background_tool_tip,
            R.layout.layout_item_option,
        ) { parent, view, position, id ->
            Toast.makeText(this,  list.get(position) + " Clicked ", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initClicks() {


        binding.btnShowPopup.setOnClickListener {
            showPopUp()
        }
    }

    private fun showPopUp() {

        listPopupWindow.show()

    }

    private fun initList() {


        list = ArrayList()
        list.add("Profile")
        list.add("Settings")
        list.add("Notification")
        list.add("Logout")


    }


}