
Book : https://www.manning.com/books/functional-programming-in-scala

Code has been organized in packages according to the chapter in which the concept or exercise was introduced.

The repo contains solved exercises, code from the book examples or original code that exemplifies the concept presented.

Unit tests are used to verify and run the implementation
(todo some code needs to be migrated to unit tests)

Bellow is a useful listing to help navigate the repo and revisit parts by jumping straight to the code from here

1. Introduction to functional programming
   - What is functional programming?
      + [Example of real-world software without mutable state: CoffeeShop](src/main/scala/_1_Introduction_to_functional_programming/_1_What_is_functional_programming/CoffeeShop.scala)
   - Getting started with functional programming in Scala
      + [Tail recursion: Fibonacci](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/FibonacciWithTailRecursion.scala)
      + [Polymorphic functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/PolymorphicFunctions.scala)
      + [Partial functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/PartialFunctions.scala)
      + [Curry functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/CurryFunctions.scala)
      + [Difference between Partial and Curry](src/test/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/CurryVsPartialTest.scala)
      + [Composing functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/ComposeFunctions.scala)
   - Functional data structures
      + [Implementing a List](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L3-L5)
      + [Creating a List literal using variadic apply](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L9-L11)
      + [Using pattern matching to create list operations as functions in a companion object](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L13-L22)
      + [Pattern matching deeper in the type structure](src/test/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/ListTest.scala#L131-L141)
      + [Helping type inference using optional parameter list](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L24-L32)
      + [Sum and product generalized by implementing using higher order function: foldRight](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L34-L43)

