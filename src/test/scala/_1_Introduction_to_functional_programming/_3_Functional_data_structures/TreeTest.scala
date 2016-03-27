package _1_Introduction_to_functional_programming._3_Functional_data_structures

import org.scalatest.{FreeSpec, Matchers}

class TreeTest extends FreeSpec with Matchers {

  "Creation of leaf" in {
    Leaf("a")
  }

  "Creation of simple leafed Branch" in {
    Branch(Leaf("a"), Leaf("b"))
  }

  "Creation of deeper multi-branched Tree" in {
    Branch(
      Branch(Leaf("a"), Leaf("b")),
      Branch(Leaf("c"), Leaf("d"))
    )
  }

  "Naively implemented tree operations" - {

    "Size of tree (number of nodes including leaves and branches)" - {

      "Size of a single leafe" in {
        val tree = Leaf("a")

        Tree.sizeNaive(tree) shouldBe 1
      }

      "Size of a simple two leaf tree" in {
        val tree = Branch(Leaf("a"), Leaf("b"))

        Tree.sizeNaive(tree) shouldBe 3
      }

      "Size of multi-branched Tree" in {
        Tree.sizeNaive(makeComplicatedIntTree()) shouldBe 9
      }

    }

    "Maximum element of an Integer typed tree" in {
      Tree.maxNaive(makeComplicatedIntTree()) shouldBe 11
    }

    "Depth of a tree" in {
      Tree.depthNaive(makeComplicatedIntTree()) shouldBe 4
    }

    "Map on a tree" in {
      val startTree = makeComplicatedIntTree()
      val expectedStringTree = Branch(
        Branch(Leaf("5"), Leaf("2")),
        Branch(
          Branch(
            Leaf("7"),
            Leaf("12")
          ),
          Leaf("3")
        )
      )

      val addOne = (x: Int) => x + 1
      val toString = (x: Int) => x.toString
      val transform = addOne.andThen(toString)

      Tree.mapNaive(startTree, transform) shouldBe expectedStringTree
    }

  }

  "Higher order function implementation for tree operations" - {
    "Size of tree (number of nodes including leaves and branches)" - {

      "Size of a single leaf" in {
        val tree = Leaf("a")

        Tree.size(tree) shouldBe 1
      }

      "Size of a simple two leaf tree" in {
        val tree = Branch(Leaf("a"), Leaf("b"))

        Tree.size(tree) shouldBe 3
      }

      "Size of multi-branched Tree" in {
        Tree.size(makeComplicatedIntTree()) shouldBe 9
      }

    }

    "Maximum element of an Integer typed tree" in {
      Tree.max(makeComplicatedIntTree()) shouldBe 11
    }

    "Depth of a tree" in {
      Tree.depth(makeComplicatedIntTree()) shouldBe 4
    }

    "Map on a tree" in {
      val startTree = makeComplicatedIntTree()
      val expectedStringTree = Branch(
        Branch(Leaf("5"), Leaf("2")),
        Branch(
          Branch(
            Leaf("7"),
            Leaf("12")
          ),
          Leaf("3")
        )
      )

      val addOne = (x: Int) => x + 1
      val toString = (x: Int) => x.toString
      val transform = addOne.andThen(toString)

      Tree.map(startTree, transform) shouldBe expectedStringTree
    }
  }

  def makeComplicatedIntTree() : Tree[Int] = {
    Branch(
      Branch(Leaf(4), Leaf(1)),
      Branch(
        Branch(
          Leaf(6),
          Leaf(11)
        ),
        Leaf(2)
      )
    )
  }
}
