void main() {

  List<int> nums = [1, 2, 3, 4, 5, 6];

  // Test 1: Get even numbers
  List<int> even = processList(nums, (n) => n % 2 == 0);
  print("Even numbers:   $even");

  // Test 2: Get numbers greater than 3
  List<int> greaterThan3 = processList(nums, (n) => n > 3);
  print("Greater than 3: $greaterThan3");

  // Test 3: Get odd numbers
  List<int> odd = processList(nums, (n) => n % 2 != 0);
  print("Odd numbers:    $odd");

  // Test 4: Get numbers less than 4
  List<int> lessThan4 = processList(nums, (n) => n < 4);
  print("Less than 4:    $lessThan4");
}

// =============================================
// THE FUNCTION
// =============================================

List<int> processList(
  List<int> numbers,        // the list of integers
  bool Function(int) predicate  // the lambda (rule to apply)
) {

  List<int> result = [];    // empty list to store matches

  for (int number in numbers) {
    if (predicate(number)) {  // if number passes the rule
      result.add(number);     // add it to result
    }
  }

  return result;
}