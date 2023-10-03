package com.sggnology.nightsaver.extension

import com.sggnology.nightsaver.exception.CustomAssertException

fun customAssert(value: Boolean, errMsg: String) {
    if(!value) {
        throw CustomAssertException(errMsg = errMsg)
    }
}