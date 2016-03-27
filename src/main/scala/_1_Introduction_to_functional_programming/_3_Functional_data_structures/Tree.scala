package _1_Introduction_to_functional_programming._3_Functional_data_structures

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def sizeNaive[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l: Tree[A], r: Tree[A]) => 1 + Tree.sizeNaive(l) + Tree.sizeNaive(r)
  }

  def maxNaive(t: Tree[Int]): Int = t match {
    case Leaf(x) => x
    case Branch(l: Tree[Int], r: Tree[Int]) => Tree.maxNaive(l) max Tree.maxNaive(r)
  }

  def depthNaive[A](t: Tree[A]): Int = t match {
    case Leaf(x) => 1
    case Branch(l: Tree[A], r: Tree[A]) => (Tree.depthNaive(l) + 1) max (Tree.depthNaive(r) + 1)
  }

  def mapNaive[A, B](t: Tree[A], f: A => B): Tree[B] = t match {
    case Leaf(x) => Leaf(f(x))
    case Branch(l: Tree[A], r: Tree[A]) => Branch(mapNaive(l, f), mapNaive(r, f))
  }

  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(x) => f(x)
    case Branch(l: Tree[A], r: Tree[A]) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def size[A](t: Tree[A]): Int = {
    fold(t)((x: A) => 1)(_ + _ + 1)
  }

  def max(t: Tree[Int]): Int = {
    fold(t)((x: Int) => x)(_ max _)
  }

  def depth[A](t: Tree[A]): Int = {
    fold(t)((x: A) => 1)((r: Int, l: Int) => (r max l) + 1)
  }

  // !!! : Tree[B] Type annotation required because Scala inference assumes result is : Leaf[B].
  // Inference needs helping here
  def map[A, B](t: Tree[A], f: A => B): Tree[B] = {
    fold(t)((x: A) => Leaf(f(x)): Tree[B])(Branch(_, _))
  }

}