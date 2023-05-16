package book.effective.chapter03.item23

sealed interface Tree {

    val age: Int

    data class Birch(
        override val age: Int = 0
    ) : Tree

    data class Squrce(
        override val age: Int = 0
    ) : Tree
}

class Forest<T : Tree>() {
    fun addTree(tree: T) = Unit
}

fun main() {
    val forest = Forest<Tree.Birch>()

    forest.addTree(Tree.Birch())
//    forest.addTree(Tree.Squrce()) 오류 발생
}