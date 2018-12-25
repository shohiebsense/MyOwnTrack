package com.shohiebsense.myowntracking.utils.formvalidator

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import com.shohiebsense.myowntracking.R

fun validate(func : Validator.() -> Unit): Validator = Validator().apply{func()}
class Validator : TextWatcher {

    var isLengthValid = false
    var STRING_MINIMUM_STANDARD_LENGTH = 5

    var isValidatorValid = false

    var validateEditTexts = arrayListOf<ValidateEditText>()
    lateinit var listener : onErrorValidationListener

    fun init(editTexts : ArrayList<EditText>) {
        editTexts.forEach { editText ->
            validateEditTexts.add(ValidateEditText(editText))
            editText.addTextChangedListener(this)
        }
    }


    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        isValidatorValid = onTextChangedValidation()
    }

    fun onTextChangedValidation() : Boolean {
        validateEditTexts.forEach {it ->
            initMinimumStandardLength(it.editText.text.toString())
            when(it.formType){
                ValidateEditText.FORM_TYPE_BASIC -> {
                    it.passed = setValidation()
                }
                ValidateEditText.FORM_TYPE_EMAIL -> {
                    it.passed =   setValidation(Patterns.EMAIL_ADDRESS.matcher(it.editText.text.toString()).matches())
                }
                ValidateEditText.FORM_TYPE_PHONE -> {
                    it.passed = setValidation(Patterns.PHONE.matcher(it.editText.text.toString()).matches())
                }
            }
            if(!it.passed) {
                listener.onError(it.editText, it.error)
                return false
            }
        }
        return true
    }


    fun setValidation() : Boolean {
        return isLengthValid
    }

    fun setValidation(isValid : Boolean) : Boolean {
        return isValid && isLengthValid
    }


    fun initMinimumStandardLength(strValidate : String) : Boolean  {
        isLengthValid = strValidate.length >= STRING_MINIMUM_STANDARD_LENGTH
        return isLengthValid
    }

    interface onErrorValidationListener{
        fun onError(editText : EditText, errorMessage : String)
    }

}