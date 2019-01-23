package com.shohiebsense.myowntracking.utils.exts

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.shohiebsense.myowntracking.R

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag: String? = null) {
    supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String? = null, addToBackStack: Boolean = false) {
    supportFragmentManager.inTransaction{
        if (addToBackStack) {
            addToBackStack(null)
        }
        replace(frameId, fragment, tag)
    }
}

/*fun Activity.createErrorToast(message:String, gravity:Int = Gravity.TOP, duration:Int = Toast.LENGTH_SHORT) {
    val toast = Toast(this)
    val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(R.layout.dialog_error_state, this.findViewById(R.id.containerErrorState))
    layout.findViewById<TextView>(R.id.tvMessage).text = message
    toast.setGravity(gravity, 0, 0)
    toast.duration = duration
    toast.view = layout
    toast.show()
}

fun Activity.createSuccessToast(message:String, gravity:Int = Gravity.TOP, duration:Int = Toast.LENGTH_SHORT){
    val toast = Toast(this)
    val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val layout = inflater.inflate(R.layout.dialog_success_state, this.findViewById(R.id.containerErrorState))
    layout.findViewById<TextView>(R.id.tvMessage).text = message
    toast.setGravity(gravity, 0, 0)
    toast.duration = duration
    toast.view = layout
    toast.show()
}*/


