package other

/*
This code is now unused / obsolete but still keeping it in the repo as an example of code I came up with while learning
(around chapter 1, subchapter 2)

This was needed to run solutions to exercises or pieces of code from the book and structure them
in a way to easily package in well defined concepts / parts of the book to revisit at a future time\

This idea now is obsolete since I think a unit test approach is superior to println
 */

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
        ),

        (
          "_3_Functional_data_structures",

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
