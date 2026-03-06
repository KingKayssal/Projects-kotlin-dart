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
