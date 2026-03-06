// =============================================
// SE 3242 - Week 2, Session 1
// Exercise 2: Transforming Between Collection Types
// Language: Dart
// =============================================

void main() {

  // Sample data from the lecture
  List<String> words = ["apple", "cat", "banana", "dog", "elephant"];

  // STEP 1: Create a map where
  //         key   = the word
  //         value = the length of the word
  Map<String, int> wordLengths = {};

  for (String word in words) {
    wordLengths[word] = word.length;
  }

  print("All words and lengths:");
  print(wordLengths);
  print("");

  // STEP 2: Filter entries where length > 4
  //         and print them
  print("Words with length greater than 4:");
  wordLengths.forEach((word, length) {
    if (length > 4) {
      print("$word has length $length");
    }
  });

  print("");

  // STEP 3: Same thing using associateWith
  //         (the hint from the lecture)
  print("Using associateWith:");
  Map<String, int> wordLengths2 = words.asMap().map(
    (index, word) => MapEntry(word, word.length)
  );

  wordLengths2.entries
    .where((entry) => entry.value > 4)
    .forEach((entry) {
      print("${entry.key} has length ${entry.value}");
    });
}