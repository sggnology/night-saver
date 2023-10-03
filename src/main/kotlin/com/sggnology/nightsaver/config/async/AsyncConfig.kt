package com.sggnology.nightsaver.config.async

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
class AsyncConfig {

    private val CORE_POOL_SIZE = 2
    private val MAX_POOL_SIZE = 10
    private val QUEUE_CAPACITY = 20

    @Bean(name = ["EMAIL_HANDLER_TASK_EXECUTOR"])
    fun emailHandlerTaskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = CORE_POOL_SIZE
        executor.maxPoolSize = MAX_POOL_SIZE
        executor.queueCapacity = QUEUE_CAPACITY
        executor.setThreadNamePrefix("email-handler-")
        executor.initialize()
        return executor
    }
}