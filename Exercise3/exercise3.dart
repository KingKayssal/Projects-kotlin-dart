class Person {
  final String name;
  final int age;

  Person(this.name, this.age);

  // Makes printing look nice
  @override
  String toString() => "${name}(${age})";
}

void main() {

  // Sample data from the lecture
  List<Person> people = [
    Person("Alice",   25),
    Person("Bob",     30),
    Person("Charlie", 35),
    Person("Anna",    22),
    Person("Ben",     28),
  ];

  print("All people:");
  people.forEach((p) => print("  ${p.name} - age ${p.age}"));
  print("");

  // STEP 1: Filter people whose name starts with A or B
  List<Person> filtered = people
      .where((p) =>
          p.name.startsWith("A") ||
          p.name.startsWith("B"))
      .toList();

  print("Filtered people (A or B):");
  print("  $filtered");
  print("");

  // STEP 2: Extract ages
  List<int> ages = filtered.map((p) => p.age).toList();
  print("Their ages: $ages");
  print("");

  // STEP 3: Calculate average
  double average = ages.reduce((a, b) => a + b) / ages.length;

  // STEP 4: Print rounded to 1 decimal place
  print("Average age: ${average.toStringAsFixed(1)}");
}