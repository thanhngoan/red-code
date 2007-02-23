/*
Copyright (c) 2000 The Regents of the University of California.
All rights reserved.

Permission to use, copy, modify, and distribute this software for any
purpose, without fee, and without written agreement is hereby granted,
provided that the above copyright notice and the following two
paragraphs appear in all copies of this software.

IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
*/

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;


import java_cup.runtime.Symbol;

/** Static semantics driver class */
class Semant {
	
	/**
	 * A poor man's pipe. outputs the contents of istream to ostream
	 * @param istream 
	 * @param ostream
	 */
	public static void connectStreams( InputStream istream, OutputStream ostream)
	{
		try {
			String slurped = slurp(istream);
			ostream.write(slurped.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  from http://snippets.dzone.com/posts/show/555
	 *  reads the contents of an inputstream into a string
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String slurp (InputStream in) throws IOException {
	    StringBuffer out = new StringBuffer();
	    byte[] b = new byte[4096];
	    for (int n; (n = in.read(b)) != -1;) {
	        out.append(new String(b, 0, n));
	    }
	    return out.toString();
	}

	public static final boolean READ_SYNTAX_DIRECTLY = true;

    /** Reads AST from from consosle, and outputs the new AST */
    public static void main(String[] args) {
		args = Flags.handleFlags(args);
		try {
			InputStreamReader reader;
			/*
			 * This allows us to pass a cool file directly to the semantic analyzer.
			 * Pipes lexer output of lexer to parser, and then reads parser's output
			 * as the input to our program.
			 */
			if (args.length == 1 && READ_SYNTAX_DIRECTLY)
			{
				Process lexerproc = Runtime.getRuntime().exec("./lexer " + args[0]);
				Process parserproc = Runtime.getRuntime().exec("./parser");
				connectStreams(lexerproc.getInputStream(), parserproc.getOutputStream());
				parserproc.getOutputStream().close();
				reader = new InputStreamReader(parserproc.getInputStream());
			}
			else
				reader = new InputStreamReader(System.in);
			
		    ASTLexer lexer = new ASTLexer(reader);
		    ASTParser parser = new ASTParser(lexer);
		    Object result = parser.parse().value;
		    StaticEnvironment env = ((Program)result).semant();
		    env.finalizeEnvironment();
		    ((Program)result).dump_with_types(System.out, 0);
		} 
		catch (Exception ex) {
		    ex.printStackTrace(System.err);
		}
    }
}
