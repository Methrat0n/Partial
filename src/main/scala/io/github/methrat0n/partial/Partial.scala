package io.github.methrat0n.partial

import scala.annotation.StaticAnnotation
import scala.reflect.macros.blackbox

class Partial extends StaticAnnotation {
  import language.experimental.macros
  def macroTransform(annottees: Any*): Any = macro PartialImpl.partialImpl
}

object PartialImpl {
  def partialImpl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._

      def optionize(name: TypeName, params: Seq[ValDef]) =
        c.Expr[Any]({
          val optionalParams = params.map { valDef =>
              q"${valDef.name}: Option[${valDef.tpe}]"
          }
          val optionizedCaseClass = TypeName(s"Partial$name")
          q"""
           final case class $name(..$params)
           final case class $optionizedCaseClass(..$optionalParams)
        """
        })

    annottees.map(_.tree) match {
      case List(q"case class $name(..$params)")       => optionize(name, params)
      case List(q"final case class $name(..$params)") => optionize(name, params)
      case _ => c.abort(c.enclosingPosition, "selection is not a simple POJO")
    }
  }

}
