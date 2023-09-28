package com.sggnology.nightsaver.util.code

class CodeGenerator {

    companion object {

        private val DEFAULT_CODE_LENGTH = 6

        fun create(
            length: Int = DEFAULT_CODE_LENGTH,
            codeType: CodeType = CodeType.NUMERIC
        ): String {
            return CodeFactory.getInstance(codeType).generate(length)
        }

        enum class CodeType {
            NUMERIC,
            ALPHABETIC,
            ALPHANUMERIC
        }
    }
}