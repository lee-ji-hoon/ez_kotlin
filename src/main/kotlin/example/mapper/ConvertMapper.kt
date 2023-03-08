package example.mapper

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

private typealias Mapper<I, O> = (I) -> O

class ConvertMapper<I : Any, O : Any>(
    private val inType: KClass<I>,
    private val outType: KClass<O>
) : Mapper<I, O> {

    private val outConstructor = outType.primaryConstructor!!
    private val inPropertiesByName by lazy { inType.memberProperties.associateBy { it.name } }

    private fun argFor(parameter: KParameter, data: I): Any? {
        val name = inPropertiesByName[parameter.name]?.get(data)
        println("========================")
        println("name -> $name")
        println("out -> $outConstructor")
        println("in -> $inPropertiesByName")
        println("========================")
        return name
    }

    override fun invoke(data: I): O = with(outConstructor) {
        val result = callBy(parameters.associateWith {
            argFor(it, data)
        })
        println("inType -> ${inType.primaryConstructor} / outType -> $outType")
        println("arg result -> $result")
        result
    }

    companion object {
        inline operator fun <reified I : Any, reified O : Any> invoke() =
            ConvertMapper(I::class, O::class)
    }
}
