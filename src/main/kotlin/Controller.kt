import it.unibo.tuprolog.core.parsing.TermParser
import it.unibo.tuprolog.solve.Solver
import it.unibo.tuprolog.theory.parsing.ClausesParser

class Controller {

    fun solve(stringTheory: String, query:String): List<String> {
//        val stringTheory = """
//            increment(A, B, C) :-
//                C is A + B.
//        """
//        val query = queryParser.parseStruct("increment(1,2,X).")

        val clauseReader = ClausesParser.withDefaultOperators()
        val theory = clauseReader.parseTheory(stringTheory)
        val solver = Solver.prolog.mutableSolverWithDefaultBuiltins(staticKb = theory)

        var queryParser = TermParser.withDefaultOperators()
        var queryStruct = queryParser.parseStruct(query)

        for (solution in solver.solve(queryStruct)) {
            if (solution.isYes) {
                val value = solution.substitution.getByName("X")
                val valueAsBigInteger = value?.asInteger()?.value
                val actualValue = valueAsBigInteger?.toInt()

                println(solution.query)
                println(solution.isYes)
                println(solution.substitution)
            }
        }
        return ArrayList()
    }
}