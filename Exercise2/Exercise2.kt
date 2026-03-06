<<<<<<< HEAD
fun main() {

    // Sample data from the lecture
    val words = listOf("apple", "cat", "banana", "dog", "elephant")

    // STEP 1: Create a map where
    //         key   = the word
    //         value = the length of the word
    val wordLengths = mutableMapOf<String, Int>()

    for (word in words) {
        wordLengths[word] = word.length
    }

    println("All words and lengths:")
    println(wordLengths)
    println()

    // STEP 2: Filter entries where length > 4
    //         and print them
    println("Words with length greater than 4:")
    wordLengths.forEach { (word, length) ->
        if (length > 4) {
            println("$word has length $length")
        }
    }

    println()

    // STEP 3: Same thing using associateWith
    //         (the hint from the lecture)
    println("Using associateWith:")
    val wordLengths2 = words.associateWith { it.length }

    wordLengths2
        .filter { (_, length) -> length > 4 }
        .forEach { (word, length) ->
            println("$word has length $length")
        }
=======
fun main() {

    // Sample data from the lecture
    val words = listOf("apple", "cat", "banana", "dog", "elephant")

    // STEP 1: Create a map where
    //         key   = the word
    //         value = the length of the word
    val wordLengths = mutableMapOf<String, Int>()

    for (word in words) {
        wordLengths[word] = word.length
    }

    println("All words and lengths:")
    println(wordLengths)
    println()

    // STEP 2: Filter entries where length > 4
    //         and print them
    println("Words with length greater than 4:")
    wordLengths.forEach { (word, length) ->
        if (length > 4) {
            println("$word has length $length")
        }
    }

    println()

    // STEP 3: Same thing using associateWith
    //         (the hint from the lecture)
    println("Using associateWith:")
    val wordLengths2 = words.associateWith { it.length }

    wordLengths2
        .filter { (_, length) -> length > 4 }
        .forEach { (word, length) ->
            println("$word has length $length")
        }
>>>>>>> 127fc1dbde3f2c51989e06d7c77b26fc99d09337
}