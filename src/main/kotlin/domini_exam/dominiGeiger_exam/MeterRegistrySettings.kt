package domini_exam.dominiGeiger_exam

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("default")
class MeasurementRegistrySettings{

    @Bean
    fun MeterRegistry(): MeterRegistry = SimpleMeterRegistry()
}
