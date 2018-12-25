package com.shohiebsense.myowntracking

import android.app.Activity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_edit_note.*
import android.text.TextUtils
import android.content.Intent
import kotlinx.android.synthetic.main.content_add_edit_note.*


class AddEditNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.mogalabs.tagnotes.EXTRA_ID"
        const val EXTRA_TITLE = "com.mogalabs.tagnotes.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.mogalabs.tagnotes.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.mogalabs.tagnotes.EXTRA_PRIORITY"
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        setSupportActionBar(toolbar)
        button_action.setOnClickListener { view ->
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_title.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_title.getText().toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
