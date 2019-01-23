package com.shohiebsense.myowntracking.utils.exts

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo
import com.shohiebsense.myowntracking.R


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Button.active(){
    isEnabled = true
    //no color just yet
   // background = ContextCompat.getDrawable(context, R.drawable.circle_green)
   // setTextColor(ContextCompat.getColor(context,R.color.white_light))
}

fun Button.inactive(){
    isEnabled = true
    //no color just yet
    //background = ContextCompat.getDrawable(context, R.drawable.circle_white)
    //setTextColor(ContextCompat.getColor(context,R.color.green_light))
}

fun Button.disable() {
    isEnabled = false
    //no color just yet
   // background = ContextCompat.getDrawable(context, R.drawable.circle_grey)
    //setTextColor(ContextCompat.getColor(context, R.color.grey))
}

fun AppCompatEditText.hideKeyboard(){
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun AppCompatEditText.showKeyboard(){
    requestFocus()
}

fun TextView.setTextFromHtml(bodyData: String){
    val html4 = bodyData.replace("[/caption]", "")
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        text = Html.fromHtml(html4, Html.FROM_HTML_MODE_COMPACT)
    } else {
        //textView.setText(Html.fromHtml(Html.fromHtml(html).toString(), new UrlImageParser(textView, context), null));
        text = Html.fromHtml(html4)
    }
}

fun updateViewSize(imageInfo: ImageInfo?, draweeView: SimpleDraweeView) {
    if (imageInfo != null) {
        draweeView.getLayoutParams().width = imageInfo.getWidth()
        draweeView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT
        draweeView.setAspectRatio(imageInfo.getWidth().toFloat() / imageInfo.getHeight())
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun Toolbar.setNoShadow(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        elevation = 0f
    }
}
