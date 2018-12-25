package com.shohiebsense.myowntracking.utils.formvalidator

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import com.shohiebsense.myowntracking.R

fun validate(func : Validator.() -> Unit): Validator = Validator().apply{func()}
class Validator : TextWatcher {

    companion object {
        var FORM_TYPE_EMAIL = 0
        var FORM_TYPE_PHONE = 1
        var FORM_TYPE_BASIC = -1
    }

    var isLengthValid = false
    var STRING_MINIMUM_STANDARD_LENGTH = 5

    var isValidatorValid = false

    //var validateEditTexts = arrayListOf<ValidateEditText>()
    lateinit var listener : onErrorValidationListener

    fun initListener(listener: onErrorValidationListener) : Validator{
        this.listener = listener
        return this
    }

    fun init(editTexts : ArrayList<EditText>) {
        editTexts.forEach { editText ->
            editText.addTextChangedListener(this)
        }
    }


    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        isValidatorValid = onTextChangedValidation(s)
    }

    private fun onTextChangedValidation(str: CharSequence?) : Boolean {
        if(str == null) return false
        initMinimumStandardLength(str.toString())
        if(!isLengthValid){
            listener.onError(str.hashCode(), FORM_TYPE_BASIC)
            return false
        }

        //get OTHER FORM TYPE
        return true
    }

    fun initMinimumStandardLength(strValidate : String) : Boolean  {
        isLengthValid = strValidate.length >= STRING_MINIMUM_STANDARD_LENGTH
        return isLengthValid
    }

    interface onErrorValidationListener{
        fun onError(charSequence : Int, formType: Int)
    }

}