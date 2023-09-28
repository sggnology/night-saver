package com.sggnology.nightsaver.util.code

import kotlin.random.Random

class AlphabeticCode: ICode {

    override fun generate(length: Int): String {
        val code = StringBuilder()

        for(i in 0 until length){
            val randomIndex = Random.nextInt(2)
            when(randomIndex){
                0 -> code.append((65 + Random.nextInt(26)).toChar())
                1 -> code.append((97 + Random.nextInt(26)).toChar())
            }
        }

        return code.toString()
    }
}