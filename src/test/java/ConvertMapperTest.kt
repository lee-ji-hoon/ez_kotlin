import example.mapper.ConvertMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.security.DomainCombiner
import kotlin.test.assertEquals

data class DataType(
    val name: String = "Data",
    val age: Int = 1
)

data class DomainType(
    val name: String = "Domain",
    val age: Int = 2
)

data class PresentationType(
    val name: String = "Presentation",
    val age: Int = 3
)

data class DataContainer(
    val name: String = "Container",
    val dataList: List<DataType> = listOf()
)

data class DomainContainer(
    val name: String = "Container",
    val dataList: List<DomainType> = listOf()
)

class ConvertMapperTest {

    private lateinit var dataType: DataType
    private lateinit var domainType: DomainType
    private lateinit var presentationType: PresentationType
    private lateinit var dataContainer: DataContainer
    private lateinit var domainContainer: DomainContainer

    @BeforeEach
    fun init() {
        dataType = DataType()
        domainType = DomainType()
        presentationType = PresentationType()
        dataContainer = DataContainer(
            dataList = listOf(DataType(), DataType())
        )
        domainContainer = DomainContainer(
            dataList = listOf(DomainType(), DomainType())
        )
    }

    @Test
    @DisplayName("DomainType을 PresentationType으로 Convert할 때 성공 테스트")
    fun convertDomainModelToPresentation() {
        // given
        domainType
        // when
        val mapper = ConvertMapper<DomainType, PresentationType>()
        val result = mapper(domainType)
        // then
        assertAll(
            { assertEquals(result::class.java, PresentationType::class.java) },
            { assertEquals(result.name, "Domain") }
        )
    }

    @Test
    @DisplayName("Container로 감싸져 있는 경우 성공 테스트")
    fun convertContainerConvert() {
        // given
        dataContainer
        // when
        val mapper = ConvertMapper<DataType, DomainType>()
        val result = dataContainer.dataList.map { mapper(it) }
        // then
        result.forEach {
            assertEquals(it::class.java, DomainType::class.java)
        }
    }

    @Test
    @DisplayName("Container를 그대로 넣었을 때 성공 테스트")
    fun convertContainerOrigin() {
        // given
        dataContainer
        domainContainer
        // when
        val mapper = ConvertMapper<DataContainer, DomainContainer>()
        val result = mapper(dataContainer)

        println("result -> ${result}")
        // then
        assertAll(
            { assertEquals(result.name, "Container") },
            /*{
                result.dataList.forEach {
                    println("it -> $it")
                    assertEquals(it::class.java, DomainType::class.java)
                }
            }*/
        )
    }
}