// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////



import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.PrintStream;
import java.util.Vector;


/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();
    public abstract void cgen(PrintStream s);

}


/** Defines simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract AbstractSymbol getName();
    public abstract AbstractSymbol getParent();
    public abstract AbstractSymbol getFilename();
    public abstract Features getFeatures();

}


/** Defines list phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Classes" list */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Class_" element to this list */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Features" list */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Feature" element to this list */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }

    
    public Collection<Feature> asCollection()
    {
    	Collection<Feature> coll = new LinkedList<Feature>();
    	
    	Enumeration enumer = getElements();
    	while (enumer.hasMoreElements())
    		coll.add( (Feature)enumer.nextElement());
    	return coll;
    }
    

    public Collection<method> getMethods()
    {
    	Collection<method> coll = new LinkedList<method>();
    	
    	for (Feature feat : asCollection())
    		if (feat instanceof method)
    			coll.add((method)feat);
    	return coll;
    }
    

    
    public Collection<attr> getAttributes()
    {
    	Collection<attr> coll = new LinkedList<attr>();
    	
    	for (Feature feat : asCollection())
    		if (feat instanceof attr)
    			coll.add((attr)feat);
    	return coll;
    }
}


/** Defines simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Formals" list */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Formal" element to this list */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
    
    public Collection<formal> asCollection()
    {
    	Collection<formal> coll = new LinkedList<formal>();
    	
    	Enumeration enumer = getElements();
    	while (enumer.hasMoreElements())
    		coll.add( (formal)enumer.nextElement());
    	return coll;
    }
}


/** Defines simple phylum Expression */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    private AbstractSymbol type = null;                                 
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }
    public abstract void code(PrintStream s);
	public String getCode(CompliationEnvironment env) {
		// TODO Auto-generated method stub
		return " # ERROR -- some undefined expression " + this.getClass().toString() + "\n";
	}

}


/** Defines list phylum Expressions
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Expressions" list */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Expression" element to this list */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
    

    public Collection<Expression> asCollection()
    {
    	Collection<Expression> coll = new LinkedList<Expression>();
    	
    	Enumeration enumer = getElements();
    	while (enumer.hasMoreElements())
    		coll.add( (Expression)enumer.nextElement());
    	return coll;
    }
}


/** Defines simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Cases" list */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Case" element to this list */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
    
    public Collection<branch> getBranches()
    {
    	Collection<branch> coll = new LinkedList<branch>();
    	
    	Enumeration enumer = getElements();
    	while (enumer.hasMoreElements())
    		coll.add( (branch)enumer.nextElement());
    	return coll;
    }
}


/** Defines AST constructor 'program'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class program extends Program {
    public Classes classes;
    /** Creates "program" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    public program(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new program(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "program\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
	    ((Class_)e.nextElement()).dump_with_types(out, n + 1);
        }
    }
    /** This method is the entry point to the semantic checker.  You will
        need to complete it in programming assignment 4.
	<p>
        Your checker should do the following two things:
	<ol>
	<li>Check that the program is semantically correct
	<li>Decorate the abstract syntax tree with type information
        by setting the type field in each Expression node.
        (see tree.h)
	</ol>
	<p>
	You are free to first do (1) and make sure you catch all semantic
    	errors. Part (2) can be done in a second stage when you want
	to test the complete compiler.
    */
    public void semant() {
	/* ClassTable constructor may do some semantic analysis */
	ClassTable classTable = new ClassTable(classes);
	
	/* some semantic analysis code may go here */

	if (classTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
	}
    }
    /** This method is the entry point to the code generator.  All of the work
      * of the code generator takes place within CgenClassTable constructor.
      * @param s the output stream 
      * @see CgenClassTable
      * */
    public void cgen(PrintStream s) 
    {
        // spim wants comments to start with '#'
        s.print("# start of generated code\n");

		CgenClassTable codegen_classtable = new CgenClassTable(classes, s);
	
		s.print("\n# end of generated code\n");
    }

}


/** Defines AST constructor 'class_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_ extends Class_ {
    public AbstractSymbol name;
    public AbstractSymbol parent;
    public Features features;
    public AbstractSymbol filename;
    /** Creates "class_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
    }
    public TreeNode copy() {
        return new class_(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }
    public AbstractSymbol getFilename() { return filename; }
    public Features getFeatures()       { return features; }

}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    public AbstractSymbol name;
    public Formals formals;
    public AbstractSymbol return_type;
    public Expression expr;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
        	((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
        expr.dump_with_types(out, n + 2);
    }
    
    /**
     * Function calling uses the following conventions.  They CALLER has
     * the responsibility of decrementing the stack and pushing the arguments onto it.
     * (Each argument is a one-word pointer.)
     * The CALLEE has the responsibility of incrementing the stack after the
     * method call has completed, as per the built-in methods.
     * Activation record looks like this:
     * previous frame pointer 
     * @param ctable
     * @param context class in which the method is defined
     * @return
     */
	public String getCode(CompliationEnvironment env) {
		String result = "";
		
		int frameSize = 8 + getArity()*4;
		//labeling
		String label = env.classdef.name + "." + this.name;
		result += CgenSupport.GLOBAL + label + "\n";
		result += label + CgenSupport.LABEL;
		
		int nonArgumentFrameBytes = 4;
		int popStackAmount = nonArgumentFrameBytes + formals.getLength() * 4;

		result += "\t addi $fp, $sp, " + ( frameSize + 4 )+ "\n";
    	result += env.codeStackPush(nonArgumentFrameBytes);
		result += "\t sw $ra,  4($sp) # store ra on stack\n"; 
		
		result += "\t move $s0, $a0 \n";
		

    	//evaluate body
    	result += expr.getCode(env);

		//pop frame
    	result += "\t lw $ra,  4($sp) # load back ra \n";
    	result += env.codeStackPop(popStackAmount); //(popStackAmount);
    	result += "\t jr $ra\n";
    	
		/*
		
		result += "\t addi $sp, $sp, -4 # push stack to store $ra\n";
		result += "\t sw $ra, 4($sp)    # store ra \n";
		
		result += expr.getCode(env);
		result += "\t lw $ra, 4($sp)    # load back ra after method\n";
		result += "\t addi $sp, $sp, " + frameSize + "  # pop stack back up on return\n";
		//le code: standard
		
		// DEBUG
		

		result += "\t" + "la $t0, str_const1\n";
		result += "\t" + "move   $s3, $sp\n";
		result += "\t" + "addi   $sp, $sp, -12\n";
		result += "\t" + "sw     $ra, 8($sp)\n";
    	result += "\t" + "sw     $a0, 4($sp)\n";
    	result += "\t" + "move   $s2, $ra\n";
    	result += "\t" + "jal    IO.out_int\n";
    	result += "\t" + "lw     $ra, 8($sp)\n";
    	result += "\t" + "move   $ra, $s2\n";
    	result += "\t" + "addi   $sp, $sp, 8\n";
		
		result += "\t jr $ra \n";
		*/
		return result;
		/* 
		int nonArgumentFrameBytes = 8;
		int stackSize = canonicalAttributesList().size() * 4 + nonArgumentFrameBytes;
    	str.println("\t sw $fp,  -4($sp) # store frame pointer in top-most portion of stack");
    	str.println("\t move $sp,   $fp");
    	str.println("\t addi $sp, $sp, -" + stackSize);
		env.enterFrame();
		env.codeStackPush(stackSize);
		for (int i=0; i < canonicalAttributesList().size(); i++) {
	    	str.println("\t lw $t0, $sp, -" + stackSize);
			// bind all attributes
	    	str.print(env.codeAndBind(
	    			i *-4  - nonArgumentFrameBytes ,
	    			canonicalAttributesList().get(i).name,
	    			"$fp"));
		}
		*/
	}
	
	public int getArity() {
		return this.formals.getLength();
	}
}


/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    public AbstractSymbol name;
    public AbstractSymbol type_decl;
    public Expression init;
    /** Creates "attr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }
    
    /**
     * 
     * @param ctable
     * @return
     */
    public String defaultWordValue(CgenClassTable ctable) {
    	
    	if (type_decl == TreeConstants.prim_slot)
    	{
    		return "0 # primary slot -> 0 (for each bool, int, and string)";
    	}
    	else
    	{
        	CgenNode classNode = ctable.getClassNode(type_decl);
        	if (classNode == null)
        	{
        		return "0 # no class - " + type_decl.toString();
        	}
        	else
        	{
        		return classNode.getDefaultWordValue(ctable);
        	}
        	//return ctable.getClassNode(type_decl).getDefaultWordValue();
    	}
    }

}


/** Defines AST constructor 'formal'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formal extends Formal {
    public AbstractSymbol name;
    public AbstractSymbol type_decl;
    /** Creates "formal" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      */
    public formal(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formal(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formal\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }

}


/** Defines AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    public AbstractSymbol name;
    public AbstractSymbol type_decl;
    public Expression expr;
    /** Creates "branch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    public AbstractSymbol name;
    public Expression expr;
    /** Creates "assign" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    


    public String getCode(CompliationEnvironment env) {
    	String result = "";
    	
    	result += expr.getCode(env);
    	
    	CompliationEnvironment.Binding localBinding = env.getBinding(name);
    	if (localBinding != null)
    	{
    		result += "\t#Local binding for " + name + " set to something else \n";
    		result += "\t sw $a0, " + localBinding.fpOffset + "($fp)\n";
    	}
    	else {
    		int attribOffset = env.classdef.getAttributeOffset(name);
    		if (attribOffset >= 0) {
        		result += "\t#Attribute binding for " + name + " \n";
        		result += "\t sw $a0, " + attribOffset + "($s0)\n";
    		} else {
        		result += "\t#ERROR NO BINDING for " + name + " \n";
    		}
    	}
    	return result;
	}

}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    public Expression expr;
    public AbstractSymbol type_name;
    public AbstractSymbol name;
    public Expressions actual;
    /** Creates "static_dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for type_name
      * @param a2 initial value for name
      * @param a3 initial value for actual
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    

    
    /**
     * Function calling uses the following conventions.  They CALLER has
     * the responsibility of decrementing the stack and pushing the arguments onto it.
     * (Each argument is a one-word pointer.)
     * The CALLEE has the responsibility of incrementing the stack after the
     * method call has completed, as per the built-in methods.
     * Activation record looks like this:
     * previous frame pointer 
     * @param ctable
     * @param context class in which the method is defined
     * @return
     */
	public String getCode(CompliationEnvironment env) {
		String result = "";
		Collection<Expression> expressions = actual.asCollection();
		method meth = env.ctable.getClassNode(type_name).getMethodByName(name);
		Collection<formal> formals = meth.formals.asCollection();
		String dispatchLabel = type_name + "." + name;
		//labeling
		
		
		int nonArgumentFrameBytes = 8;
		int pushAmount = nonArgumentFrameBytes + expressions.size()*4;
    	
		//result += "\t sw $ra,  0($sp) # store ra on stack\n";
    	result += "\t sw $fp, 0($sp) # store FP at the tip top of the Activation Record \n";
    	result += "\t sw $s0, -4($sp) # store $ra so in AR at fp - 4 \n";
		env.enterFrame();
    	result += env.codeStackPush(pushAmount);
    	//result += "\t move  $fp,  $sp # set fp for the sake of the dispatch \n";

    	//add formals bindings
    	int offsetFromFP = -8;
    	int offsetFromSP = formals.size() * 4;
    	Iterator<formal> iformal = formals.iterator();
    	for (Expression arg : expressions) {
    		formal frm = iformal.next();
    		//formal arg = (formal) formals.getNth(i);
	    	//str.println("\t lw $t0, $sp, -" + stackSize);
			// bind all attributes
        	result += arg.getCode(env);
        	result += "\t sw $a0,  " + offsetFromSP + "($sp) # store evaluated parameter on stack \n";
	    	env.pushLocalBinding( offsetFromFP , frm.name);
	    	//each parameter gets 4 bytes closer to FP and 4 bytes away from SP
	    	offsetFromFP += 4;
	    	offsetFromSP -= 4;
		}

		result += expr.getCode(env);

    	result += "\t move $s0, $a0 \n";
    	
    	result += "\t jal " + dispatchLabel + " # dispatch the method \n";

    	result += "\t lw $s0,    4($sp) # load back ra \n";
    	result += "\t lw $fp,    8($sp) # load back fp \n"; //TODO
    	result += env.codeStackPop(nonArgumentFrameBytes);
    	
		return result;
	}


}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    public Expression expr;
    public AbstractSymbol name;
    public Expressions actual;
    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    
    /*
    

	public String getCode(CompliationEnvironment env) {
		String result = "";
		Collection<Expression> expressions = actual.asCollection();
		method meth = env.ctable.getClassNode(type_name).getMethodByName(name);
		Collection<formal> formals = meth.formals.asCollection();
		String dispatchLabel = type_name + "." + name;
		//labeling
		
		int nonArgumentFrameBytes = 8;
		int pushAmount = nonArgumentFrameBytes + expressions.size()*4;
    	
		//result += "\t sw $ra,  0($sp) # store ra on stack\n";
    	result += "\t sw $fp, 0($sp) # store FP at the tip top of the Activation Record \n";
    	result += "\t sw $ra, -4($sp) # store $ra so in AR at fp - 4 \n";
		env.enterFrame();
    	result += env.codeStackPush(pushAmount);
    	//result += "\t move  $fp,  $sp # set fp for the sake of the dispatch \n";

    	//add formals bindings
    	int offsetFromFP = -8;
    	int offsetFromSP = formals.size() * 4;
    	Iterator<formal> iformal = formals.iterator();
    	for (Expression arg : expressions) {
    		formal frm = iformal.next();
    		//formal arg = (formal) formals.getNth(i);
	    	//str.println("\t lw $t0, $sp, -" + stackSize);
			// bind all attributes
        	result += arg.getCode(env);
        	result += "\t sw $a0,  " + offsetFromSP + "($sp) # store evaluated parameter on stack \n";
	    	env.pushLocalBinding( offsetFromFP , frm.name);
	    	//each parameter gets 4 bytes closer to FP and 4 bytes away from SP
	    	offsetFromFP += 4;
	    	offsetFromSP -= 4;
		}
    	
    	//result += "\t "
    	
    	result += "\t jal " + dispatchLabel + " # dispatch the method \n";

    	result += "\t lw $s0,    4($sp) # load back ra \n";
    	result += "\t lw $fp,    8($sp) # load back fp \n"; //TODO
    	result += env.codeStackPop(nonArgumentFrameBytes);
    	
		return result;
	}
	*/

}


/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    public Expression pred;
    public Expression then_exp;
    public Expression else_exp;
    /** Creates "cond" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for then_exp
      * @param a2 initial value for else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
	pred.dump_with_types(out, n + 2);
	then_exp.dump_with_types(out, n + 2);
	else_exp.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    


    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling
		String falseLabel = env.genLabel("IsFalse");
		String afterLabel = env.genLabel("AfterBoolSet");

		result += pred.getCode(env);
		result += "\t lw $t0, 12($a0)\n";
		result += "\t beq $t0, $zero, " + falseLabel + "\n";
		
		result += then_exp.getCode(env);
		result += "\t j " + afterLabel + "\n";
		
		result += "\t " + falseLabel + CgenSupport.LABEL + "\n";
		result += else_exp.getCode(env);
		
		result += "\t " + afterLabel + CgenSupport.LABEL + "\n";
		return result;
	}


}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    public Expression pred;
    public Expression body;
    /** Creates "loop" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for body
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    

    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling
		String testLabel = env.genLabel("Test");
		String afterLabel = env.genLabel("AfterBoolSet");

		result += "\t " + testLabel + CgenSupport.LABEL + "\n";
		result += pred.getCode(env);
		result += "\t lw $t0, 12($a0)\n";
		result += "\t beq $t0, $zero, " + afterLabel + "\n";
		//body
		result += body.getCode(env);
		result += "\t j " + testLabel + "\n";
		
		result += "\t " + afterLabel + CgenSupport.LABEL + "\n";
		return result;
	}


}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    public Expression expr;
    public Cases cases;
    /** Creates "typcase" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for cases
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    

    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling
		String afterLabel = env.genLabel("endcase");
		String loopLabel = env.genLabel("startcasetesting");
		Map<branch, String> labelMap = new HashMap<branch, String>();
		for (branch br : cases.getBranches())
			labelMap.put(br, env.genLabel(br.type_decl.toString()));
		

		result += env.codeStackPush(4);
		
		result += expr.getCode(env);
		int offsetFromFP = env.getFPOffset();
    	result += "\t sw $a0,  " + offsetFromFP + "($fp) # store evaluated parameter on stack \n";
    	
		result += "\t beq $a0, $zero, _case_abort2 # case on void\n";
		result += "\t lw $t0, 0($a0) # load class tag into $t0\n";
		result += "\t la $t1, InheritanceTable # load class tag into $t0\n";

		result += "\t " + loopLabel + CgenSupport.LABEL;
		
		result += "\t beq $t0, $zero, _case_abort # when we get to a null parent we have tested all branches\n";
		for (branch br : cases.getBranches())
		{
			int tag = env.ctable.getClassNode(br.type_decl).classTag;
			result += "\t beq $t0, " + tag + "," + labelMap.get(br)+ " # when we get to a null parent we have tested all branches\n";
			//result += "\t beq $t0, " + br.name + "," + labelMap.get(br)+ " # when we get to a null parent we have tested all branches\n";
		}
		
		result += "\t add $t0, $t0, $t1 # load class tag into $t0\n";
		result += "\t lw  $t0, 0($t0) # load class tag into $t0\n";
		result += "\t j " + loopLabel + "\n";
		
		//now see if this matches any of the cases match.  this is done
		//by walking up the class inheritance graph starting with classOf(expr)
		//if none is found then signal an error
		
		for (branch br : cases.getBranches())
		{

	    	env.pushLocalBinding( offsetFromFP , br.name);
	    	
			result += "\t " + labelMap.get(br) + CgenSupport.LABEL;
			result += br.expr.getCode(env);
			result += "\t j " + afterLabel + "\n";

	    	env.popLocalBinding();
		}

		result += "\t " + afterLabel + CgenSupport.LABEL;
		result += env.codeStackPop(4);
		
		return result;
	}


}


/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    public Expressions body;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    

    public String getCode(CompliationEnvironment env) {
    	/*
		String result = "";
		//result += env.codePushRAOnStack();
		//labeling
		result += env.evalExpressionsIntoRegisters(new Expression[] { e1, e2 }, new String[] { "$t3", "$a0" });
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 12($t3)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";

		result += "\t add $t0, $t1, $t2  # result of addition in $t0\n";
		result += "\t sw  $t0, 12($a0)   # store result in new integer \n";
		result += "\t move $a0, int_const0  # result of addition in $t0\n";

		//result += env.codeRestoreRA();
		return result;
		*/
		String result = "";
		//labeling
		
		for (Expression expr : body.asCollection())
			result += expr.getCode(env);
		return result;
	}


}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    public AbstractSymbol identifier;
    public AbstractSymbol type_decl;
    public Expression init;
    public Expression body;
    /** Creates "let" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for identifier
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      * @param a3 initial value for body
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }

    public String getCode(CompliationEnvironment env) {
		String result = "";

		// eval the form
		if (init == null || init instanceof no_expr)
		{
			result += "\t la $a0, " + type_decl + CgenSupport.PROTOBJ_SUFFIX + "\n";
			result += "\t jal Object.copy \n";
		}
		else
		{
			result += init.getCode(env);
		}

		//get a new binding
		result += "";
		result += env.codeStackPush(4);
		int offsetFromFP = env.getFPOffset();
    	result += "\t sw $a0,  " + offsetFromFP + "($fp) # store evaluated parameter on stack \n";
    	env.pushLocalBinding( offsetFromFP , identifier);
    	
    	result += body.getCode(env);
    	
    	env.popLocalBinding();
		result += env.codeStackPop(4);
		return result;
	}


}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "plus" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }

    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling

		result += env.codeStackPush(4);
		
		result += e1.getCode(env);
		result += "\t sw $a0, 4($sp)     # store first result \n";
		
		result += e2.getCode(env);
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 4($sp)     # load back first result after method\n";
		result += "\t lw $t1, 12($t1)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";
	
		result += "\t add $t0, $t1, $t2  # result of addition in $t0\n";
		result += "\t sw  $t0, 12($a0)   # store result in new integer \n";	

		result += env.codeStackPop(4);
		return result;
	}

}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "sub" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }

    public String getCode(CompliationEnvironment env) {
    	String result = "";
		result += env.codeStackPush(4);
		
		result += e1.getCode(env);
		result += "\t sw $a0, 4($sp)     # store first result \n";
		
		result += e2.getCode(env);
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 4($sp)     # load back first result after method\n";
		result += "\t lw $t1, 12($t1)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";
	
		result += "\t sub $t0, $t1, $t2  # result of addition in $t0\n";
		result += "\t sw  $t0, 12($a0)   # store result in new integer \n";	

		result += env.codeStackPop(4);
		return result;
	}


}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "mul" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    


    public String getCode(CompliationEnvironment env) {
    	/*
		String result = "";
		//result += env.codePushRAOnStack();
		//labeling
		result += env.evalExpressionsIntoRegisters(new Expression[] { e1, e2 }, new String[] { "$t3", "$a0" });
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 12($t3)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";

		result += "\t add $t0, $t1, $t2  # result of addition in $t0\n";
		result += "\t sw  $t0, 12($a0)   # store result in new integer \n";
		result += "\t move $a0, int_const0  # result of addition in $t0\n";

		//result += env.codeRestoreRA();
		return result;
		*/
		String result = "";
		//labeling

		result += env.codeStackPush(4);
		
		result += e1.getCode(env);
		result += "\t sw $a0, 4($sp)     # store first result \n";
		
		result += e2.getCode(env);
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 4($sp)     # load back first result after method\n";
		result += "\t lw $t1, 12($t1)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";
	

		result += "\t mult $t1, $t2   # result of division in $t0\n";
		result += "\t mflo $t0        # result of multiplication in $t0\n";
		result += "\t sw  $t0, 12($a0)   # store result in new integer \n";	

		result += env.codeStackPop(4);
		return result;
	}


}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "divide" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    


    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling

		result += env.codeStackPush(4);
		
		result += e1.getCode(env);
		result += "\t sw $a0, 4($sp)     # store first result \n";
		
		result += e2.getCode(env);
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 4($sp)     # load back first result after method\n";
		result += "\t lw $t1, 12($t1)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";
	

		//result += "\t beq $t2, $zero, $t2   # result of division in $t0\n";
		result += "\t div $t0, $t1, $t2   # result of division in $t0\n";
		result += "\t sw  $t0, 12($a0)   # store result in new integer \n";	

		result += env.codeStackPop(4);
		return result;
	}


}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    public Expression e1;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }

    public String getCode(CompliationEnvironment env) {
		String result = "#NEGATE\n";
		//labeling
		result += e1.getCode(env);
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t2, 12($a0)    # decode argument to negation\n";
		result += "\t addi $t1, $zero, 0 # load back first result after method\n";
		
		result += "\t sub $t0, $t1, $t2  # result of addition in $t0\n";
		result += "\t sw  $t0, 12($a0)   # store result in new integer \n";
		return result;
	}

}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "lt" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    
    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling

		result += env.codeStackPush(4);
		
		result += e1.getCode(env);
		result += "\t sw $a0, 4($sp)     # store first result \n";
		
		result += e2.getCode(env);
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 4($sp)     # load back first result after method\n";
		result += "\t lw $t1, 12($t1)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";
	

		result += "\t slt $t0, $t1, $t2   # result of division in $t0\n";
		result += env.codeSetBoolPointer("$t0", "$a0");

		result += env.codeStackPop(4);
		return result;
	}

}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "eq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }

    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling
		result += env.codeStackPush(4);
		result += e1.getCode(env);
		result += "\t sw $a0, 4($sp)     # store first result \n";
		
		result += e2.getCode(env);

		//move result pointers into $t1 and $t2 respectively
		result += "\t lw   $t1, 4($sp)     # load back first result after method\n";
		result += "\t move $t2, $a0        # load back first result after method\n";

		//comparisons between types get basic comparison
		if (e1.get_type() == e2.get_type() && (e1.get_type() == TreeConstants.Bool || e1.get_type() == TreeConstants.Str || e1.get_type() == TreeConstants.Int))
		{
			result += "\t la $a0, " + BoolConst.truebool.getCodeRef() + "     # true \n";
			result += "\t la $a1, " + BoolConst.falsebool.getCodeRef() + "     # false \n";
			result += "\t jal equality_test    # test primitive types against each other \n";
		}
		else
		{

			//TODO handle void
			String trueRef  = BoolConst.truebool.getCodeRef(),
	    	       falseRef = BoolConst.falsebool.getCodeRef();
			String falseLabel = env.genLabel("IsFalse");
			String afterLabel = env.genLabel("AfterBoolSet");

			result += "\t bne $t1, $t2, " + falseLabel + "\n";
			result += "\t la $a0, " + trueRef + "\n";
			result += "\t j " + afterLabel + "\n";
			result += "\t " + falseLabel + CgenSupport.LABEL + "\n";
			result += "\t la $a0, " + falseRef + "\n";
			result += "\t " + afterLabel + CgenSupport.LABEL + "\n";
			
			//result += env.codeSetBoolPointer("$t0", "$a0");
		}

		result += env.codeStackPop(4);
		return result;
	}

}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    public Expression e1;
    public Expression e2;
    /** Creates "leq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    


    public String getCode(CompliationEnvironment env) {
		String result = "";
		//labeling

		result += env.codeStackPush(4);
		
		result += e1.getCode(env);
		result += "\t sw $a0, 4($sp)     # store first result \n";
		
		result += e2.getCode(env);
		result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";

		result += "\t lw $t1, 4($sp)     # load back first result after method\n";
		result += "\t lw $t1, 12($t1)    # decode integers\n";
		result += "\t lw $t2, 12($a0)    \n";
		result += "\t addi $t2, $t2, 1  # subtract 1 from RHS because a < b <=> a <= b + 1 over integers \n";
	

		result += "\t slt $t0, $t1, $t2   # result of division in $t0\n";
		result += env.codeSetBoolPointer("$t0", "$a0");

		result += env.codeStackPop(4);
		return result;
	}

}


/** Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    public Expression e1;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }


}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    public AbstractSymbol token;
    /** Creates "int_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
	CgenSupport.emitLoadInt(CgenSupport.ACC,
                                (IntSymbol)AbstractTable.inttable.lookup(token.getString()), s);
    }
    

	public String getCode(CompliationEnvironment env) {
    	String ref = env.ctable.findIntSymbol(token.toString()).getCodeRef();
		return "\t la $a0, " + ref +" # int const: load from appropriate global!\n";
	}

}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    public Boolean val;
    /** Creates "bool_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
	CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(val), s);
    }
    
    public String getCode(CompliationEnvironment env) {
    	String ref = val ? BoolConst.truebool.getCodeRef() : BoolConst.falsebool.getCodeRef();
		return "\t la $a0, " + ref +" # const: load from appropriate global!\n";
	}

}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    public AbstractSymbol token;
    /** Creates "string_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
		out.print(Utilities.pad(n + 2) + "\"");
		Utilities.printEscapedString(out, token.getString());
		out.println("\"");
		dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
	CgenSupport.emitLoadString(CgenSupport.ACC,
                                   (StringSymbol)AbstractTable.stringtable.lookup(token.getString()), s);
    }
    
    public String getCode(CompliationEnvironment env) {
    	String ref = env.ctable.findStringSymbol(token.toString()).getCodeRef();
		return "\t la $a0, " + ref +" # string const: load from appropriate global!\n";
	}

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    public AbstractSymbol type_name;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
		dump_AbstractSymbol(out, n + 2, type_name);
		dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }


    public String getCode(CompliationEnvironment env) {
    	String result = "";
    	//copy prototype object
    	if (type_name != TreeConstants.SELF_TYPE)
    	{
    		result += "\t la $a0, " + type_name + CgenSupport.PROTOBJ_SUFFIX +"     # store pointer to prototype object in a0 \n";
    	}
    	else
    	{
    		//TODO
    	}
		result += "\t jal Object.copy    # Create new object \n";
		//call initialization
		result += "\t jal " + type_name + "_init" + "    # Create new object \n";
		return result;
	}

}


/** Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    public Expression e1;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }
    
    public String getCode(CompliationEnvironment env) {
    	String result = "";
    	result += e1.getCode(env);

		String trueRef  = BoolConst.truebool.getCodeRef(),
    	       falseRef = BoolConst.falsebool.getCodeRef();
		//falseRef = trueRef;
		String falseLabel = env.genLabel("IsFalse");
		String afterLabel = env.genLabel("AfterBoolSet");
		
		result += "\t beq " + "$a0" + ", $zero, " + falseLabel + "\n";
		result += "\t la "+ "$a0" + ", " + falseRef + "\n";
		result += "\t j " + afterLabel + "\n";
		result += "\t " + falseLabel + CgenSupport.LABEL + "\n";
		result += "\t la "+ "$a0" + ", " + trueRef + "\n";
		result += "\t " + afterLabel + CgenSupport.LABEL + "\n";
    	return result;
    }


}


/** Defines AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /** Creates "no_expr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    	
    }
    
    public String getCode(CompliationEnvironment env) {
		return "\t move $a0, $zero # no expression returns VOID!\n"; // TODO
	}


}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    public AbstractSymbol name;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public void code(PrintStream s) {
    }

    public String getCode(CompliationEnvironment env) {
    	String result = "";
    	CompliationEnvironment.Binding localBinding = env.getBinding(name);
    	
    	if (name == TreeConstants.self)
    	{
    		result += "\t move $a0, $s0 #self object\n";
    		
    	}
    	else if (localBinding != null)
    	{
    		result += "\t#Local binding for " + name + " \n";
    		result += "\t lw $a0, " + localBinding.fpOffset + "($fp)\n";
    	}
    	else {
    		int attribOffset = env.classdef.getAttributeOffset(name);
    		if (attribOffset >= 0) {
        		result += "\t#Attribute binding for " + name + " \n";
        		result += "\t lw $a0, " + attribOffset + "($s0)\n";
    		} else {
        		result += "\t#ERROR NO BINDING for " + name + " \n";
    		}
    	}
    	return result;
	}

}


