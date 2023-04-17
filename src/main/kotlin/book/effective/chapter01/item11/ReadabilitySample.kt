package book.effective.chapter01.item11

class Person() {
    val isAdult: Boolean
        get() = true
}


fun main() {
    val person: Person? = Person()
    if (person != null && person.isAdult) {
        showPerson(person)
    } else {
        showError()
    }

    person?.takeIf { it.isAdult }
        ?.let { showPerson(it) }
        ?: showError()
}

fun showError() = Unit

fun showPerson(person: Person) = Unit
