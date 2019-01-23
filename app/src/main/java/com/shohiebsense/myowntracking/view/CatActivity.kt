package com.shohiebsense.myowntracking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shohiebsense.myowntracking.R
import com.shohiebsense.myowntracking.view.fragment.CatFragment
import com.shohiebsense.myowntracking.utils.exts.addFragment

class CatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)

        addFragment(CatFragment.newInstance(), R.id.frame_fragment, "")
    }
}
