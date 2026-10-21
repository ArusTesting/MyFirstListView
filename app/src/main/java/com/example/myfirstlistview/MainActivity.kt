package com.example.myfirstlistview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import android.view.GestureDetector
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {
    var data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.addAll(listOf("1", "2", "3", "4", "5"))

        val lvAdapter : ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )

        val _lv1 = findViewById<ListView>(R.id.lv1)
        _lv1.adapter = lvAdapter
        val _btnTambah = findViewById<Button>(R.id.btnTambah)

        _btnTambah.setOnClickListener {
            var dtAkhir = Integer.parseInt(data.get(data.size - 1)) + 1
            data.add("$dtAkhir")
            lvAdapter.notifyDataSetChanged()
        }

        _lv1.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                data[position],
                Toast.LENGTH_SHORT
            ).show()
        }
        val gestureDetector = GestureDetector(
            this,
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    val position = _lv1.pointToPosition(e.x.toInt(), e.y.toInt())
                    if (position != ListView.INVALID_POSITION) {
                        val selectedItem = data[position]
                        showActionDialog(position, selectedItem, data, lvAdapter)
                    }
                    return true
                }
            }
        )
        _lv1.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            false
        }
    }
}