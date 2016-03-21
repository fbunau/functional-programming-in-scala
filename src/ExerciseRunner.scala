object ExerciseRunner {

  val ChapterNb = 0
  val SubchapterNb = 2
  val ExercisesToRun = List(-1)//(0 to 1).toList

  val BookReferences = List(
    (
      "_1_Introduction_to_functional_programming",

      List
      (
        (
          "_1_What_is_functional_programming",

          List("CoffeeShop")
        ),

        (
          "_2_Getting_started_with_functional_programming_in_Scala",

          List(
            "FibonacciWithTailRecursion",
            "PolymorphicFunctions",
            "PartialFunctions",
            "CurryFunctions",
            "CurryVsPartial",
            "ComposeFunctions")
          // todo: ex 2.1 isSorted, according to given comparison function
          // todo: ex 2.4 uncurry
        ),

        (
          "_3_Functional_data_structures",

          /*
          todo ex:
            3.2 tail (removal)
            3.3 setHead
            3.4 drop ( n elements )

            // append example

            3.6 init ( new list all but the last elements )
            3.7 foldRight with short-circuiting
            3.8 passing Nil and Cons to foldRight
            3.9 length of list with foldRight
            3.10 tail recursive foldLeft
            3.11 sum and product using foldLeft
            3.12 reverse of a list using fold
            3.13 foldLeft using foldRight, and foldRight using foldLeft, making a tail recursive foldRight
            3.14 append using either foldLeft or foldRight
            3.15 concatenate a list of list into a single list

            3.16 transform list of integers by adding 1 to each element
            3.17 List[Double] to List[String]
            3.18 implement map
            3.19 implement filter
            3.20 implement flatMap
            3.21 use flatmap to implement filter
            3.22 function that accepts two lists and constructs a new list by adding the corresponding elements
            3.23 generalize to zipWith, so that it's not specific to integers or addition

            // example of usage with other List functions defined in the Scala lib

            3.24 implement hasSubsequence to check if a list is contained as subsequence in another list
           */
          List(
            "SingleLinkedList",
            "CreatingListLiteralWithVariadicApply",
            "PatternMatchingToCreateListOperations",
            "PatternMatchingDeeperInTheTypeStructure",

            "HelpingTypeInferenceWithOptionalParameterList",
            "GeneralizingToHOFSumAndProductWithFoldRight")
        )
      )
    )
  )

  def main(args: Array[String]): Unit = {

    val chapter = BookReferences(ChapterNb)
    val subchapter = chapter.list(SubchapterNb)

    assert(ExercisesToRun.max < subchapter.list.size)

    val exercisesToRun = ExercisesToRun match {
      case List(x) if x < 0 =>  subchapter.list
      case _ => ExercisesToRun.map(i => subchapter.list(i))
    }

    def decodeBookReference(reference: Named) = {
      reference.name.replaceFirst("_", "")
          .replaceFirst("_", ". ")
          .replaceAll("_", " ")
    }

    println("Chapter: " + decodeBookReference(chapter))
    println("Subchapter: " + decodeBookReference(subchapter))

    exercisesToRun
      .map(exerciseName => (exerciseName, s"${chapter.name}.${subchapter.name}.$exerciseName"))
      .foreach(exampleToRun => {
        val shortName = exampleToRun._1
        val fullName = exampleToRun._2

        println("=======================================")
        println(s"Running: $shortName")
        println

        createExercise(fullName).run()

        println
      })

  }

  def createExercise(exerciseClassName: String) = {
    Class.forName(exerciseClassName).newInstance.asInstanceOf[{ def run(): Unit }]
  }

  sealed trait Named {
    def name: String
  }

  case class NamedListWithNamedLists[T](t: (String, List[(String, List[T])])) extends Named  {
    def name = { t._1 }
    def list = { t._2 }
  }

  case class NamedList[T](t: (String, List[T])) extends Named {
    def name = { t._1 }
    def list = { t._2 }
  }

  implicit def tupleToNamedListWithNamedLists[T](t: (String, List[(String, List[T])])) : NamedListWithNamedLists[T] = new NamedListWithNamedLists(t)
  implicit def tupleToNamedList[T](t: (String, List[T])) : NamedList[T] = new NamedList(t)

}

