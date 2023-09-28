package com.sggnology.nightsaver.util.code

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CodeGeneratorTest{

    @Test
    fun testCreate(){
        val code = CodeGenerator.create()
        assertNotNull(code)
    }

    @Test
    fun testLengthOfCode(){
        val TEST_CODE_LENGTH = 10
        val code = CodeGenerator.create(length = TEST_CODE_LENGTH)
        assertEquals(TEST_CODE_LENGTH, code.length)
    }

    @Test
    fun testCodeType(){
        val numericCode = CodeGenerator.create(codeType = CodeGenerator.Companion.CodeType.NUMERIC)
        assertTrue(numericCode.matches(Regex("\\d+")))
        val alphabeticCode = CodeGenerator.create(codeType = CodeGenerator.Companion.CodeType.ALPHABETIC)
        assertTrue(alphabeticCode.matches(Regex("[a-zA-Z]+")))
        val alphaNumericCode = CodeGenerator.create(codeType = CodeGenerator.Companion.CodeType.ALPHANUMERIC)
        assertTrue(alphaNumericCode.matches(Regex("[a-zA-Z0-9]+")))
    }
}