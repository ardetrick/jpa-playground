import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.*

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern="[%date{HH:mm:ss}] %-28.-28logger{0}  %.-1level   %message%n"
    }
}

logger("com.sevenlist", DEBUG)

// logger("org.hibernate.type", TRACE)

root(ERROR, ["STDOUT"])