
Code has been organized in packages according to the chapter in which the concept or exercise was introduced.

The repo contains solved exercises, code from the book examples or original code that exemplifies the concept presented.

There is an [ExerciseRunner](src/ExerciseRunner.scala) in the source root, which holds an index of all chapters, subchapters and exercises implemented
and a mechanism to run them.

You can select which ones to run by modifying the values

```
val ChapterNb = 0
val SubchapterNb = 1
val ExercisesToRun = 0 to 5
```

1. Introduction to functional programming
   - What is functional programming?
      + [CoffeeShop](src/_1_Introduction_to_functional_programming/_1_What_is_functional_programming/CoffeeShop.scala)
   - Getting started with functional programming in Scala
      + [FibonacciWithTailRecursion](src/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/FibonacciWithTailRecursion.scala)
      + [PolymorphicFunctions](src/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/PolymorphicFunctions.scala)
      + [PartialFunctions](src/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/PartialFunctions.scala)
      + [CurryFunctions](src/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/CurryFunctions.scala)
      + [CurryVsPartial](src/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/CurryVsPartial.scala)
      + [ComposeFunctions](src/_1_Introduction_to_functional_programming/_2_Getting_started_with_functional_programming_in_Scala/ComposeFunctions.scala)
