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
        for(index in 0 .. validateEditTexts.lastIndex){
            initMinimumStandardLength(validateEditTexts[index].editText.text.toString())
            when(validateEditTexts[index].formType){
                ValidateEditText.FORM_TYPE_BASIC -> {
                    validateEditTexts[index].passed = setValidation()
                }
                ValidateEditText.FORM_TYPE_EMAIL -> {
                    validateEditTexts[index].passed =   setValidation(Patterns.EMAIL_ADDRESS.matcher(validateEditTexts[index].editText.text.toString()).matches())
                }
                ValidateEditText.FORM_TYPE_PHONE -> {
                    validateEditTexts[index].passed = setValidation(Patterns.PHONE.matcher(validateEditTexts[index].editText.text.toString()).matches())
                }
            }
            if(!validateEditTexts[index].passed) {
                listener.onError(validateEditTexts[index].editText, validateEditTexts[index].error)
                isValidatorValid = false
                break
            }
        }
        isValidatorValid = true
        return isValidatorValid
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