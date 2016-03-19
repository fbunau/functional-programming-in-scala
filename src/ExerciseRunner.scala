object ExerciseRunner {

  val ChapterNb = 0
  val SubchapterNb = 1
  val ExercisesToRun = 0 to 5

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
        )
      )
    )
  )

  def main(args: Array[String]): Unit = {

    val chapter = BookReferences(ChapterNb)
    val subchapter = chapter.list(SubchapterNb)

    assert(ExercisesToRun.min >= 0)
    assert(ExercisesToRun.max < subchapter.list.size)
    val exercisesToRun = ExercisesToRun.map(i => subchapter.list(i))

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

  implicit def TupleToNamedListWithNamedLists[T](t: (String, List[(String, List[T])])) : NamedListWithNamedLists[T] = new NamedListWithNamedLists(t)
  implicit def TupleToNamedList[T](t: (String, List[T])) : NamedList[T] = new NamedList(t)

}

