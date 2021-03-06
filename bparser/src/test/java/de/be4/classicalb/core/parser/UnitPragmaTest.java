package de.be4.classicalb.core.parser;

import java.io.PrintWriter;
import java.io.PushbackReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PositionPrinter;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class UnitPragmaTest {

	@Test
	public void testLexer() throws Exception {
		String input = "MACHINE UnitPragmaExpressions1 VARIABLES   lala, /*@ unit \"10**1 * m**1\" */ xx,   /*@ unit \"10**1 * m**1\" */ yy,   /*@ unit \"10**2 * m**2\" */ zz,   test  INVARIANT  /*@ label \"lol\" */  lala = \"trololo\" &xx:NAT &   yy:NAT &   zz:NAT &   test:NAT INITIALISATION xx,yy,zz,test:=1,2,3,4 OPERATIONS   multiply = zz := xx*yy;   add      = xx := yy+1;   sub      = xx := yy-1;   type     = test := yy END";
		// String input =
		// "MACHINE foo  PROPERTIES /*@ label foo */ x = /*@ symbolic */ {y|->z| y < z }  END";

		BLexer lex = new BLexer(
				new PushbackReader(new StringReader(input), 500));
		Token t;
		while (!((t = lex.next()) instanceof EOF)) {
			System.out.print(t.getClass().getSimpleName() + "(" + t.getText()
					+ ")");
			System.out.print(" ");
		}

		BParser p = new BParser();
		Start ast = p.parse(input, false);

		ASTPrinter pr = new ASTPrinter();
		ast.apply(pr);

		System.out.println(printAST(ast));

	}

	private String printAST(final Node node) {
		final StringWriter swriter = new StringWriter();
		NodeIdAssignment nodeids = new NodeIdAssignment();
		node.apply(nodeids);
		IPrologTermOutput pout = new PrologTermOutput(new PrintWriter(swriter),
				false);
		PositionPrinter pprinter = new ClassicalPositionPrinter(nodeids);
		ASTProlog prolog = new ASTProlog(pout, pprinter);
		node.apply(prolog);
		swriter.flush();
		return swriter.toString();
	}

}