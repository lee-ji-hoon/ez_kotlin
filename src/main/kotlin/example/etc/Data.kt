package example.etc

data class Data(
    val name1: String = "",
    val name2: String = "",
    val name3: String = "",
) {

    companion object {
        class DataBuilder {

            private var name1: String = ""
            private var name2: String = ""
            private var name3: String = ""

            fun setName1(data: String): DataBuilder = apply { name1 = data }

            fun setName2(data: String): DataBuilder = apply { name2 = data }

            fun setName3(data: String): DataBuilder = apply { name3 = data }

            fun build(): Data {
                return Data(name1, name2, name3)
            }
        }
    }
}