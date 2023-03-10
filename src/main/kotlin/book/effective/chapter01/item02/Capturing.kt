package book.effective.chapter01.item02

fun main() {
    noCapturing01()
    noCapturing02()
    capturing()
}

fun noCapturing01() {
    var numbers = (2..100).toList()
    val primes = mutableListOf<Int>()

    while (numbers.isNotEmpty()) {
        val prime = numbers.first()
        primes.add(prime)
        numbers = numbers.filter { it % prime != 0 }
    }
    println(primes) // [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
}

fun noCapturing02() {
    val primes: Sequence<Int> = sequence {
        var numbers = generateSequence(2) { it + 1 } // 기본 seed 값을 2로 주고 it+1을 해서 2부터 n값만큼 리스트를 만든다.

        while (true) {
            val prime = numbers.first()
            yield(prime) // Sequence 안에서 값을 반환하면서도 상태를 유지하게 한다.
            numbers = numbers
                .drop(1)
                .filter { it % prime != 0 }
        }
    }
    println(primes.take(10).toList()) // [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
}

fun capturing() {
    val primes: Sequence<Int> = sequence {
        var numbers = generateSequence(2) { it + 1 }

        var prime: Int // prime 값을 캡쳐
        while (true) {
            prime = numbers.first()
            yield(prime)
            numbers = numbers
                .drop(1)
                // 시퀀스를 활용하고 있으므로 필터링이 지연된다.
                // 최종적인 prime값으로만 필터링이 된 것이다.
                .filter { it % prime != 0 }
        }
    }
    println(primes.take(10).toList()) // [2, 3, 5, 6, 7, 8, 9, 10, 11, 12]
}




