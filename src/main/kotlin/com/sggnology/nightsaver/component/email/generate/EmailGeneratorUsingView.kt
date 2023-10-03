package com.sggnology.nightsaver.component.email.generate

import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine

abstract class EmailGeneratorUsingView(
    private val springTemplateEngine: SpringTemplateEngine
) {

    abstract fun generate(
        to: String,
        title: String,
        variableMap: Map<String, Any>,
        isMultipart: Boolean = false
    ): MimeMessagePreparator

    protected fun generateTemplateMethod(
        to: String,
        title: String,
        viewPath: String,
        variableMap: Map<String, Any>,
        isMultipart: Boolean = false
    ): MimeMessagePreparator{
        return MimeMessagePreparator { mimeMessage ->
            MimeMessageHelper(mimeMessage, isMultipart).apply {
                setTo(to)
                setSubject(title)
                setText(springTemplateEngine.process(viewPath, mapToContext(variableMap)), true)
            }
        }
    }

    private fun mapToContext(variableMap: Map<String, Any>): Context {
        val ctx = Context()

        variableMap.entries.forEach {
            ctx.setVariable(it.key, it.value)
        }

        return ctx
    }
}