<<<<<<< HEAD
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
=======
void main() {

  List<int> nums = [1, 2, 3, 4, 5, 6];

  List<int> even = processList(nums, (n) => n % 2 == 0);
  print("Even numbers:   $even");

  List<int> greaterThan3 = processList(nums, (n) => n > 3);
  print("Greater than 3: $greaterThan3");

  List<int> odd = processList(nums, (n) => n % 2 != 0);
  print("Odd numbers:    $odd");

    List<int> lessThan4 = processList(nums, (n) => n < 4);
  print("Less than 4:    $lessThan4");
}

List<int> processList(
  List<int> numbers,        
  bool Function(int) predicate  
) {

  List<int> result = [];    

  for (int number in numbers) {
    if (predicate(number)) {  
      result.add(number);     
    }
  }

  return result;

}
>>>>>>> 127fc1dbde3f2c51989e06d7c77b26fc99d09337
