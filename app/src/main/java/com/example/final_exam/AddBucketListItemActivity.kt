package com.example.final_exam

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddBucketListItemActivity : AppCompatActivity() {

    private lateinit var destinationNameEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var rankingSpinner: Spinner

    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_bucket_list_item)

        destinationNameEditText = findViewById(R.id.destination_name_edit_text)
        descriptionEditText = findViewById(R.id.description_edit_text)
        rankingSpinner = findViewById(R.id.ranking_spinner)

        // Populate the Spinner with ranking options
        val rankingOptions = arrayOf("1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th")
        val rankingAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, rankingOptions)
        rankingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rankingSpinner.adapter = rankingAdapter

        val addButton: Button = findViewById(R.id.add_button)
        addButton.setOnClickListener {
            addBucketListItem()
        }
    }

    private fun addBucketListItem() {
        val destinationName = destinationNameEditText.text.toString().trim()
        val description = descriptionEditText.text.toString().trim()
        val ranking = rankingSpinner.selectedItem.toString()

        if (destinationName.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "All fields need to be populated", Toast.LENGTH_SHORT).show()
            return
        }

        val data = hashMapOf(
            "destinationName" to destinationName,
            "description" to description,
            "ranking" to ranking
        )

        firestore.collection("bucketListItems")
            .add(data)
            .addOnSuccessListener {
                Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to add item: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
