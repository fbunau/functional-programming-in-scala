
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
      + [Simple polymorphic HOF](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/SimplePolymorphicHOF.scala)
      + [Polymorphic functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/PolymorphicFunctions.scala)
      + [Partial applied functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/PartialAppliedFunctions.scala)
      + [Curry / uncurry functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/CurryFunctions.scala)
      + [Difference between Partial and Curry](src/test/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/CurryVsPartialTest.scala)
      + [Composing functions](src/main/scala/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/ComposeFunctions.scala)
   - Functional data structures
      + [Implementing a List](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L3-L5)
      + [Creating a List literal using variadic apply](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L9-L11)
      + [Pattern matching | implement list operations: tail, sum, product](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L13-L27)
      + [Pattern matching deeper in the type structure](src/test/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/ListTest.scala#L131-L141)
      + [Helping type inference using optional parameter lists](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L29-L37)
      + [HOF: foldRight | sum and product generalized implementations](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L39-L48)
      + [Implementing a Tree](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/Tree.scala#L3-L5)
      + [Operations on tree: size, max, depth, map](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/Tree.scala#L9-L27)
      + [Fold on Tree: Using HOF to implement tree operations](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/Tree.scala#L29-L50)
   - Handling errors without exceptions
      + [Implementing Option](src/main/scala/_1_Introduction_to_functional_programming/_4_Handling_errors_withouth_exceptions/Option.scala)
      + [Example of lifting an existing function to work with Option](src/main/scala/_1_Introduction_to_functional_programming/_4_Handling_errors_withouth_exceptions/InsuranceEngine.scala)
      + [Implementing the map2 lifting function](src/main/scala/_1_Introduction_to_functional_programming/_4_Handling_errors_withouth_exceptions/Option.scala#L46-L51)
      + [Implementing Either](src/main/scala/_1_Introduction_to_functional_programming/_4_Handling_errors_withouth_exceptions/Either.scala)
   - Strictness and laziness
      + [Implementing Stream](src/main/scala/_1_Introduction_to_functional_programming/_5_Strictness_and_laziness/Stream.scala#L44-L45)
      + [Implementing a smart constructor](src/main/scala/_1_Introduction_to_functional_programming/_5_Strictness_and_laziness/Stream.scala#L49-L53)
      + [Generalizing fold for streams](src/main/scala/_1_Introduction_to_functional_programming/_5_Strictness_and_laziness/Stream.scala#L34-L38)
      + [Stream operations, just like List has](src/main/scala/_1_Introduction_to_functional_programming/_5_Strictness_and_laziness/Stream.scala#L6-L42)
      + [Infinite stream generators](src/main/scala/_1_Introduction_to_functional_programming/_5_Strictness_and_laziness/Stream.scala#L66-L68)
   - Purely functional state
      + [Making stateful APIs pure, with simple next state return](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/SimpleRNG.scala)