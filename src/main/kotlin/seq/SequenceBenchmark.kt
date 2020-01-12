package seq

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

var ran = Random(123)
var INTS_100  = (1..100).map { ran.nextInt() }

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
open class SequenceBenchmark {

    @Benchmark
    fun oneStepSequence(): List<*> {
        return INTS_100.asSequence().map { it.toString() }.toList()
    }

    @Benchmark
    fun oneStepChain(): List<*> {
        return INTS_100.map { it.toString() }
    }

    @Benchmark
    fun multiStepSequence(): List<*> {
        return INTS_100.asSequence().filter { it > 10 }.map { it.toString() }.toList()
    }

    @Benchmark
    fun multiStepChain(): List<*> {
        return INTS_100.filter { it > 10 }.map { it.toString() }
    }
}
