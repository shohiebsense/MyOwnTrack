package com.shohiebsense.myowntracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.ui.fragment.CatFragment
import com.shohiebsense.myowntracking.ui.viewmodel.CatViewModel
import com.shohiebsense.myowntracking.utils.Injection
import com.shohiebsense.myowntracking.utils.exts.addFragment

class CatActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)
        addFragment(CatFragment.newInstance(), R.id.frame_fragment, "")


    }
}
