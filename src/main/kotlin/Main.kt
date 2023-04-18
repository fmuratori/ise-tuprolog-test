import it.unibo.tuprolog.core.parsing.TermParser
import it.unibo.tuprolog.solve.Solver
import it.unibo.tuprolog.theory.parsing.ClausesParser

fun main() {
    val stringTheory = """
        increment(A, B, C) :-
            C is A + B.
    """
    val clauseReader = ClausesParser.withDefaultOperators()
    val theory = clauseReader.parseTheory(stringTheory)
    val solver = Solver.prolog.mutableSolverWithDefaultBuiltins(staticKb = theory)

    var queryParser = TermParser.withDefaultOperators()
    val query = queryParser.parseStruct("increment(1,2,X).")

    for (solution in solver.solve(query)) {
        if (solution.isYes) {
            val value = solution.substitution.getByName("X")
            val valueAsBigInteger = value?.asInteger()?.value
            val actualValue = valueAsBigInteger?.toInt()

            println(solution.query)
            println(solution.isYes)
            println(solution.substitution)
        }
    }

    /*
    val prolog = Solver.prolog.solverWithDefaultBuiltins(
        staticKb = Theory.of(
            Fact.of(Struct.of("f", Atom.of("a"))),
            Fact.of(Struct.of("f", Atom.of("b"))),
            Fact.of(Struct.of("f", Atom.of("c")))
        )
    )

    val goal = Struct.of("f", Var.of("X"))

    val solutions: List<Solution> = prolog.solveList(goal)

    println(solutions.size) // 3
    println(solutions)
    // [Yes(query=f(X_2), substitution={X_2=a}), Yes(query=f(X_2), substitution={X_2=b}) Yes(query=f(X_2), substitution={X_2=c})]

    for (solution in solutions) {
        println(solution.query) // f(X_2), f(X_2), f(X_2)
        println(solution.isYes) // true, true, true
        println(solution.substitution) // {X_2=a}, {X_2=b}, {X_2=c}
    }
    */
}

