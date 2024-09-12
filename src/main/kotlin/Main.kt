package bo.ardadev
import org.matheclipse.core.eval.ExprEvaluator
import org.matheclipse.core.interfaces.IExpr
import kotlin.math.absoluteValue

fun main() {
    val funcion: String = " x^3-2*x-5";
    val funcion2: String = "x^3-2*x+1";

//    val porNewtonRaphson: Double = newtonRaphson(funcion, 1.0);
//    val porBiseccion: Double = bisectionMethod(funcion, 1.0, 5.0);
//
//    println("Resultado f1 por Newton-Raphson: " + porNewtonRaphson);
//    println("Resultado f1 por Biseccion: " + porBiseccion);

    val porNewtonRaphson2: Double = newtonRaphson(funcion2, -1.5);
    val porBiseccion2: Double = bisectionMethod(funcion2, -5.0, 0.0);

    println("Resultado f2 por Newton-Raphson: " + porNewtonRaphson2);
    println("Resultado f2 por Biseccion: " + porBiseccion2);
}

//fun newtonRaphson(expression: String, initialGuess: Double): Double {
//    val evaluator = ExprEvaluator()
//    var x: Double = initialGuess
//    var fx: IExpr
//    var fpx: IExpr
//    var iteration = 0
//
//    do {
//        fx = evaluator.eval(expression.replace("x", x.toString()))
//        println("Valor de fx: " + fx.toString())
//
//        // Evaluar la derivada correctamente
//        val derivativeExpression = evaluator.eval("D($expression, x)")
//        fpx = evaluator.eval(derivativeExpression.toString().replace("x", x.toString()))
//        println("Valor de fpx: " + fpx.toString())
//
//        // Verificar si fx y fpx son números
//        if (fx.isNumber && fpx.isNumber) {
//            val fxDouble = fx.toDoubleDefault()
//            val fpxDouble = fpx.toDoubleDefault()
//            println("fxDouble: $fxDouble, fpxDouble: $fpxDouble")
//            x -= fxDouble / fpxDouble
//        } else {
//            println("Error: La evaluación de la función o su derivada no es un número.")
//            println("fx: " + fx.toString() + ", fpx: " + fpx.toString())
//            throw IllegalArgumentException("La evaluación de la función o su derivada no es un número.")
//            //println("La evaluación de la función o su derivada no es un número.")
//        }
//
//        iteration++
//    } while (fx.toDoubleDefault().absoluteValue > 1e-7 && iteration < 100)
//
//    return x
//}

fun newtonRaphson(expression: String, initialGuess: Double): Double {
    val evaluator = ExprEvaluator()
    var x: Double = initialGuess
    var fx: IExpr
    var fpx: IExpr
    var iteration = 0

    do {
        fx = evaluator.eval(expression.replace("x", x.toString()))
        println("Valor de fx: " + fx.toString())

        // Evaluar la derivada correctamente
        val derivativeExpression = evaluator.eval("D($expression, x)")
        fpx = evaluator.eval(derivativeExpression.toString().replace("x", x.toString()))
        println("Valor de fpx: " + fpx.toString())

        // Verificar si fx y fpx son números
        if (fx.isNumber && fpx.isNumber) {
            val fxDouble = fx.toDoubleDefault()
            val fpxDouble = fpx.toDoubleDefault()
            println("fxDouble: $fxDouble, fpxDouble: $fpxDouble")
            if (fpxDouble == 0.0) {
                throw IllegalArgumentException("La derivada es cero, no se puede continuar.")
            }
            x -= fxDouble / fpxDouble
        } else {
            println("Error: La evaluación de la función o su derivada no es un número.")
            println("fx: " + fx.toString() + ", fpx: " + fpx.toString())
            throw IllegalArgumentException("La evaluación de la función o su derivada no es un número.")
        }

        iteration++
    } while (fx.toDoubleDefault().absoluteValue > 1e-7 && iteration < 100)

    return x
}


fun bisectionMethod(expression: String, a: Double, b: Double): Double {
    val evaluator = ExprEvaluator()
    var left = a
    var right = b
    var mid: Double
    var fLeft: IExpr
    var fMid: IExpr

    do {
        mid = (left + right) / 2
        fLeft = evaluator.eval("$expression".replace("x", left.toString()))
        fMid = evaluator.eval("$expression".replace("x", mid.toString()))

        if (fMid.toDoubleDefault() == 0.0) {
            return mid
        } else if (fLeft.toDoubleDefault() * fMid.toDoubleDefault() < 0) {
            right = mid
        } else {
            left = mid
        }
    } while ((right - left) / 2 > 1e-7)

    return mid
}

