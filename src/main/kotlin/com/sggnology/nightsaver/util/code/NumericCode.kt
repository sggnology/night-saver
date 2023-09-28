package com.sggnology.nightsaver.util.code

import java.lang.StringBuilder
import kotlin.random.Random

class NumericCode : ICode {

    override fun generate(length: Int): String {
        val code = StringBuilder()
        for(i in 0 until length){
            code.append(Random.nextInt(10))
        }
        return code.toString()
    }
}