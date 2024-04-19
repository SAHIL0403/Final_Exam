package com.example.final_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the app name and "Bucket List" text views
        val appNameTextView = findViewById<TextView>(R.id.app_name_text_view)
        val bucketListTextView = findViewById<TextView>(R.id.bucket_list_text_view)

        appNameTextView.text = "Sahil Chawla"
        bucketListTextView.text = "Bucket List"

        // Add New Bucket List Item Button Click Listener
        val addNewItemButton = findViewById<Button>(R.id.add_new_item_button)
        addNewItemButton.setOnClickListener {
            // Start AddBucketListItemActivity
            startActivity(Intent(this, AddBucketListItemActivity::class.java))
        }
    }
}
