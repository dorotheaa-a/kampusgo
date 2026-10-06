package com.sgu.kampusgo

import android.content.Intent
import android.os.Bundle
import android.content.ActivityNotFoundException
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sgu.kampusgo.ui.theme.KampusGoTheme

import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast


class ProfileXmlActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val spMajor = findViewById<Spinner>(R.id.spMajor)
        val btnSave = findViewById<Button>(R.id.btnSave)

//The choices live in strings.xml, not in the layout. simple_spinner_item is the closed box. The dropdown
//resource is the list that opens.
        ArrayAdapter.createFromResource(
            this,
            R.array.majors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spMajor.adapter = adapter
        }

//TextWatcher has three methods. This form only needs the last one. The same watcher is attached to the
//name and the email, so either keystroke can turn the button on. NPM, major, and angkatan do not gate the
//button.
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val ok = etName.text.toString().isNotBlank() &&
                        etEmail.text.toString().contains("@")
                btnSave.isEnabled = ok
                val email = etEmail.text.toString()
                etEmail.error = if (email.isNotEmpty() && !email.contains("@")) {
                    "Email needs @"
                } else {
                    null
                }
            }
        }
        etName.addTextChangedListener(watcher)
        etEmail.addTextChangedListener(watcher)

//The click runs only if the button is enabled. The Toast is the proof that Save ran. This lab does not write the
//form to a database.
        btnSave.setOnClickListener {
            val major = spMajor.selectedItem.toString()
            Toast.makeText(this, "Saved ($major)", Toast.LENGTH_SHORT).show()
        }
    }
}

