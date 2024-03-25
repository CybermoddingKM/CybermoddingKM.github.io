package com.example.anispark

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Likedquotes : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var likedQuotesList: ArrayList<String>
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_likedquotes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        prefs = getSharedPreferences("Likedquotes", MODE_PRIVATE)
        val likedQuotesSet = prefs.getStringSet("quotes", setOf()) ?: setOf()

        likedQuotesList = ArrayList(likedQuotesSet)
        val listView = findViewById<ListView>(R.id.LikedquotesListView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, likedQuotesList)
        listView.adapter = adapter

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val quote = adapter.getItem(position)
            AlertDialog.Builder(this)
                .setTitle("Remove Quote")
                .setMessage("Are you sure you want to remove this quote?")
                .setPositiveButton("Yes") { dialog, which ->
                    // Remove the quote
                    likedQuotesList.remove(quote)
                    adapter.notifyDataSetChanged()
                    saveQuotes()
                    Toast.makeText(this, "Quote removed", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }

    private fun saveQuotes() {
        // Save the updated list back to SharedPreferences
        with(prefs.edit()) {
            putStringSet("quotes", likedQuotesList.toSet())
            apply()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}


