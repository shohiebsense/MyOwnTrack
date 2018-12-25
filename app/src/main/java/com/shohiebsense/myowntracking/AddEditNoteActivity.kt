package com.shohiebsense.myowntracking

import android.app.Activity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_edit_note.*
import android.text.TextUtils
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shohiebsense.myowntracking.adapters.CategoryAdapter
import com.shohiebsense.myowntracking.data.model.Category
import com.shohiebsense.myowntracking.utils.formvalidator.ValidateUtils
import com.shohiebsense.myowntracking.utils.formvalidator.Validator
import com.shohiebsense.myowntracking.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.content_add_edit_note.*
import kotlinx.coroutines.runBlocking


class AddEditNoteActivity : AppCompatActivity(), Validator.onErrorValidationListener {

    lateinit var mCategoryViewModel : CategoryViewModel
    lateinit var editTexts : ArrayList<EditText>

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "EXTRA_PRIORITY"
        const val EXTRA_REPLY = "EXTRA_REPLY"
        const val EXTRA_CATEGORYNAME = "EXTRA_CATEGORY"
        const val EXTRA_CATEGORYPRIORITY = "EXTRA_CATEGORY_PRIORITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        setSupportActionBar(toolbar)
        initRecyclerView()

        editTexts = arrayListOf(edit_title,edit_description)
        Validator().initListener(this).init(editTexts)

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



    fun initRecyclerView(){
        recycler_category.layoutManager = LinearLayoutManager(this)
        recycler_category.setHasFixedSize(true)
        var adapter = CategoryAdapter()
        recycler_category.adapter = adapter
        adapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener{
            override fun onItemClick(category: Category) {

            }

        })
        initViewModel(adapter)
    }

    fun initViewModel(adapter : CategoryAdapter){
        mCategoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        mCategoryViewModel.mAllCategories?.observe(this,
            Observer<List<Category>> { t ->
                adapter.submitList(t)
            })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                runBlocking {
                    mCategoryViewModel.remove(adapter.getCategoryAt(viewHolder.adapterPosition))
                }
                Toast.makeText(baseContext, "Note Deleted!", Toast.LENGTH_SHORT).show()
            }
        }
        ).attachToRecyclerView(recycler_category)
    }

    override fun onError(hashCode: Int, formType : Int) {
        ValidateUtils.generateErrorMessage(this, editTexts, formType, hashCode)
    }

}
