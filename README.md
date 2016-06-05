
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
      + [Implementing a List](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L5-L7)
      + [Creating a List literal using variadic apply](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L11-L13)
      + [Pattern matching - implement list operations: tail, setHead, sum, product, drop, append](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L15-L50)
      + [Pattern matching deeper in the type structure](src/test/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/ListTest.scala#L243-L253)
      + [Helping type inference using optional parameter lists](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L52-L60)
      + [HOF: foldRight - sum, product, length, append, concat - generalized implementations](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L64-L89)
      + [HOF: foldLeft - sum, product, length, reverse - generalized implementations](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L93-L114)
      + [foldLeft implemented with foldRight](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L118-L156)
      + [foldRight implemented with foldLeft](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L158-L195)
      + [Naive map, filter, zipWith](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L219-L246)
      + [HOF: map, flatMap, filter, zipWith](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L199-L215)
      + [Scala Collections library examples](src/test/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/ScalaCollectionLibraryExamples.scala)
      + [hasSubsequence: Loss of efficiency when assembling list functions from simpler components](src/main/scala/_1_Introduction_to_functional_programming/_3_Functional_data_structures/List.scala#L248-L260)
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
      + [Making stateful APIs pure, with simple next state return](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/SimpleRNG.scala#L5-L18)
      + [Naive composition of state generating functions](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/SimpleRNG.scala#L22-L67)
      + [Generalizing using State transition type](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/SimpleRNG.scala#L69-L75)
      + [Using combinators to compose State transitions](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/SimpleRNG.scala#L77-L114)
      + [flatMap: A more powerful combinator](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/SimpleRNG.scala#L116-L141)
      + [Generalizing functions: unit, map, map2 flatMap, sequence for a State object](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/State.scala)
      + [Implementing a FSM (Candy Machine) using State object](src/main/scala/_1_Introduction_to_functional_programming/_6_Purely_functional_state/CandyMachine.scala)