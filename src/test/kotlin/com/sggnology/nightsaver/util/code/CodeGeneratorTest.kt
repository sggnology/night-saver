package com.sggnology.nightsaver.util.code

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CodeGeneratorTest{

    @Test
    fun testCreate(){
        val code = CodeGenerator.generate()
        assertNotNull(code)
    }

    @Test
    fun testLengthOfCode(){
        val TEST_CODE_LENGTH = 10
        val code = CodeGenerator.generate(length = TEST_CODE_LENGTH)
        assertEquals(TEST_CODE_LENGTH, code.length)
    }

    @Test
    fun testCodeType(){
        val numericCode = CodeGenerator.generate(codeType = CodeGenerator.Companion.CodeType.NUMERIC)
        assertTrue(numericCode.matches(Regex("\\d+")))
        val alphabeticCode = CodeGenerator.generate(codeType = CodeGenerator.Companion.CodeType.ALPHABETIC)
        assertTrue(alphabeticCode.matches(Regex("[a-zA-Z]+")))
        val alphaNumericCode = CodeGenerator.generate(codeType = CodeGenerator.Companion.CodeType.ALPHANUMERIC)
        assertTrue(alphaNumericCode.matches(Regex("[a-zA-Z0-9]+")))
    }
}