package com.sggnology.nightsaver.util.code

import com.sggnology.nightsaver.exception.NotFoundException

class CodeFactory {
    companion object {
        private val codeMap: Map<CodeGenerator.Companion.CodeType, ICode> = mapOf(
            CodeGenerator.Companion.CodeType.NUMERIC to NumericCode(),
            CodeGenerator.Companion.CodeType.ALPHABETIC to AlphabeticCode(),
            CodeGenerator.Companion.CodeType.ALPHANUMERIC to AlphaNumericCode()
        )

        fun getInstance(codeType: CodeGenerator.Companion.CodeType): ICode {
            return codeMap[codeType] ?: throw NotFoundException(errMsg = "존재하지 않는 코드 타입입니다.")
        }
    }
}