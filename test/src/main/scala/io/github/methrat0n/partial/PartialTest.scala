package io.github.methrat0n.partial

object PartialTest extends App {
  @Partial
  case class User(name: String)

  @Partial
  final case class User2(name2: String)

  @Partial
  case class User3(name3: String, age: Int)

  case class MetaUser(user: User, partial: PartialUser)

  println(User2("merlin"))
  println(PartialUser(None))
}
