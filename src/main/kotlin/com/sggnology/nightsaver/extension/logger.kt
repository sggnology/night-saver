import java.util.logging.Logger

inline val <reified T> T.logger: Logger
    get() = Logger.getLogger(T::class.java.name)