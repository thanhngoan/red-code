// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////

import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.io.PrintStream;
import java.util.Vector;

class SemanticError extends Exception {
	SemanticError(int lineNumber, String description)
	{
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3286449635718189221L;
}

class UndefinedTypeError extends SemanticError {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1295673767486805019L;

	UndefinedTypeError(int lineNumber, String description)
	{
		super(lineNumber, description);
	}
}

/** 
 * Defines simple phylum Program
 * 
 * For some unknown reason, this is an abstract type.
 **/
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract StaticEnvironment semant();

}


/**
 *  Defines simple phylum Class_
 *  For some unknown reason, this is an abstract type.
 */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Classes
  * <p>See <a href="ListNode.html">ListNode</a> for full documentation. </p>
  * 
  */
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
    
    public Collection<class_> asCollection()
    {
    	Collection<class_> coll = new LinkedList<class_>();
    	Enumeration enumer = getElements();
    	while (enumer.hasMoreElements())
    		coll.add( (class_)enumer.nextElement());
    	return coll;
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
	public abstract void canonicalize(StaticEnvironment env);
	
	public method getMethodMatching(AbstractSymbol methodName, StaticEnvironment env)
    {
    	return null; 
    }
    public attr getAttributeMatching(AbstractSymbol variableName, StaticEnvironment env)
    {
    	return null;
    }
    public abstract void analyzeForm(StaticEnvironment env);
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
    
    public abstract class_ resolveStaticType(StaticEnvironment env);
    //public class_ resolveStaticType(StaticEnvironment env) { return null; }
    //public abstract void analyzeForm(StaticEnvironment env);

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
    protected Classes classes;
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

    public Classes getClasses() { return classes; }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
            // sm: changed 'n + 1' to 'n + 2' to match changes elsewhere
	    ((Class_)e.nextElement()).dump_with_types(out, n + 2);
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
    public StaticEnvironment semant() {
    	StaticEnvironment env = new StaticEnvironment(this);
    	semanticallyAnalyzeInEnvironment(env);
    	class_ mainClass = null;

    	for (class_ clazz : classes.asCollection())
    		if (clazz.getName() == TreeConstants.Main && clazz.isValidClass())
    			mainClass = clazz;
    	if (mainClass == null)
    		env.reportError(new StaticEnvironment.NoMainClassError
    				(getLineNumber(), AbstractTable.stringtable.addString("<program>")));
    	return env;
    }
    
    public void semanticallyAnalyzeInEnvironment(StaticEnvironment env)
    {
    	for (class_ clazz : classes.asCollection())
    		clazz.canonicalize(env);
    	
    	for (class_ clazz : classes.asCollection())
    		clazz.canonicalizeFeatures(env);
    	
    	for (class_ clazz : classes.asCollection())
    		clazz.analyzeForm(env);
    }
}


/** Defines AST constructor 'class_'.
    <p>
    See <a href="TreeNode.h
 */
class class_ extends Class_ {
    protected AbstractSymbol name;
    protected AbstractSymbol parent;
    protected Features features;
    protected AbstractSymbol filename;

    protected LinkedList<class_> directSuperclasses;
    protected LinkedList<class_> directSubclasses;
    /** Creates "class_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_(int lineNumber, AbstractSymbol nameSym, AbstractSymbol parentSym, Features features, AbstractSymbol filenameSym) {
        super(lineNumber);
        this.name = nameSym;
        this.parent = parentSym;
        this.features = features;
        this.filename = filenameSym;

    	directSubclasses = new LinkedList<class_>();
    	directSuperclasses = new LinkedList<class_>();
    }
    
    public Collection<class_> getDirectSuperclasses() { return directSuperclasses; }
    
    /**
     * TODO: make sure we copy the class hierarchy
     */
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
    
    public boolean isBuiltinClass() {
    	return name == TreeConstants.Object_ 
    	|| name == TreeConstants.Bool 
    	|| name == TreeConstants.Int
    	|| name == TreeConstants.Str
    	|| name == TreeConstants.IO; 
    }
    
    public boolean isInheritable() {
    	return !isBuiltinClass() || name == TreeConstants.Object_;
    }
    
    /**
     * Registers this class in the given environment and fills the inheritance
     * hierarchy.
     * @param env environment in which to register the class symbol
     */
    public void canonicalize(StaticEnvironment env)
    {
    	if (name != TreeConstants.Object_)
    	{
	    	class_ parentClass = env.findClass(getParent());
	    	if (parentClass == null)
	    	{
	    		env.reportError(new StaticEnvironment.UnresolvedSuperclassError(getLineNumber(), getFilename(), getParent()));
	    		this.parent = null;
	    	}
	    	else if (!parentClass.isInheritable())
	    	{
	    		env.reportError(new StaticEnvironment.IllegalSuperclassError(getLineNumber(), getFilename(), getParent()));
	    		this.parent = null;
	    	}
	    	directSuperclasses.add(parentClass);
    	}
    	if (env.findClass(name) != null)
    	{
    		env.reportError(new StaticEnvironment.ClassRedefinitionError(getLineNumber(), getFilename(), name));
    		this.parent = null;
    	}
    	else
    		env.registerClass(this);
    }
    
    public boolean isValidClass() { 
    	return name == TreeConstants.Object_ || getParent() != null;
    }
    
    public class_ getMethodReturnType(AbstractSymbol methodName, StaticEnvironment env)
    {
    	method matchingMeth = findMethod(methodName, env);
    	if (matchingMeth != null)
    		return env.findClass(matchingMeth.return_type);
    	else
    		return null;
    }
    
    public class_ getMemberType(AbstractSymbol variableName, StaticEnvironment env)
    {
    	attr matchingAttr = findAttribute(variableName, env);
    	if (matchingAttr != null)
    		return env.findClass(matchingAttr.type_decl);
    	else
    		return null;
    }
    
    public method findMethod(AbstractSymbol methodName, StaticEnvironment env)
    {
    	for (Feature feat : features.asCollection())
    	{
    		method result = feat.getMethodMatching(methodName, env);
    		if (result != null)
    			return result;
    	}
    	//search through parent classes
    	for (class_ parentClass : directSuperclasses)
    	{
    		method result = parentClass.findMethod(methodName, env);
    		if (result != null)
    			return result;
    	}
    	return null;
    	
    }
    
    public attr findAttribute(AbstractSymbol variableName, StaticEnvironment env)
    {
    	for (Feature feat : features.asCollection())
    	{
    		attr result = feat.getAttributeMatching(variableName, env);
    		if (result != null)
    			return result;
    	}
    	//search through parent classes
    	for (class_ parentClass : directSuperclasses)
    	{
    		attr result = parentClass.findAttribute(variableName, env);
    		if (result != null)
    			return result;
    	}
    	return null;
    }
    
    /**
     * Registers this feature's symbolic information in the current class.
     * @param env environment in which the feature is defined.
     */
    public void canonicalizeFeatures(StaticEnvironment env)
    {
    	env.enterClassDefinition(this);
    	for (Feature feat : features.asCollection())
    		feat.canonicalize(env);
    	env.leaveClassDefinition(this);
    }
    
    /**
     * 
     */
    
    
    /**
     * Analyzes the class definition in the given environment.
     * 
     * IMPORTANT: the environment must already contain canonicalized class and feature names.
     * @param env environment in which class definition is found.
     */
    public void analyzeForm(StaticEnvironment env)
    {
    	if (isValidClass())
    	{
	    	env.enterClassDefinition(this);
	    	for (Feature feat : features.asCollection())
	    		feat.analyzeForm(env);
	    	env.leaveClassDefinition(this);
    	}
    	else
    		env.warn(getLineNumber(), getFilename(), "Skipping definition of class '" +name +"' because of errors.");
    }
    
    public boolean subclassOf(class_ ofType)
    {
    	if (ofType == this)
    		return true;
    	else
    		for (class_ parentClass : this.directSuperclasses)
    			if (parentClass.subclassOf(ofType))
    				return true;
    	return false;
    }
    
    protected void addSubclass(class_ subclass)
    {
    	directSubclasses.add(subclass);
    }
    
    public Collection<class_> directSuperclasses()
    {
    	return null;
    }
    
    //public AbstractSymbol getFilename() { return filename; }
    
    // sm: why were these three not in here already?
    // they are present in the PA3 cool-tree.java skeleton..
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }
    public AbstractSymbol getFilename() { return filename; }
    
    public boolean isSelfType() { return name == TreeConstants.SELF_TYPE; }

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

}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    protected AbstractSymbol name;
    protected Formals formals;
    protected AbstractSymbol return_type;
    protected Expression expr;
    
    protected class_ evaledReturnType;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol name, Formals formals, AbstractSymbol rettype, Expression expression) {
        super(lineNumber);
        this.name = name;
        this.formals = formals;
        this.return_type = rettype;
        this.expr = expression;
    }
    public TreeNode copy() {
        method cp = new method(lineNumber, 
        		copy_AbstractSymbol(name), 
        		(Formals)formals.copy(), 
        		copy_AbstractSymbol(return_type), 
        		(Expression)expr.copy());
        cp.evaledReturnType = evaledReturnType;
        return cp;
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
     * Processes the return type symbol of the method into a class_ so that
     * method calls can be analyzed for their return type in the future.
     */
    public void canonicalize(StaticEnvironment env)
    {
    	if (this.return_type != TreeConstants.SELF_TYPE)
    	{
	    	class_ returnType = env.findClass(this.return_type);
	    	if (returnType == null)
	    	{
	    		// TODO: error handling
	    		// parentClass = object
	    		env.reportError(new StaticEnvironment.UnresolvedTypeError(lineNumber, env.getFilename(), this.return_type));
	    	}
	    	evaledReturnType = returnType;
    	}
    	else
    	{
    		evaledReturnType = new class_(lineNumber, TreeConstants.SELF_TYPE, null, null, env.getFilename());
    	}
    }
    
    public void analyzeForm(StaticEnvironment env)
    {
    	if (!env.inNativeInstallationMode())
    	{
	    	env.enterScopedFrame();
	    	for (formal oneFormal : formals.asCollection())
	    	{
	    		env.registerBinding(oneFormal.getName(), env.findClass(oneFormal.getTypeName()));
	    	}
	    	class_ resolvedExpressionType = this.expr.resolveStaticType(env);
	    	if (resolvedExpressionType == null)
	    		; //TODO: this should never be true
	    	if (!resolvedExpressionType.subclassOf(evaledReturnType))
	    		; // TODO: EXCEPTION FIXME
	    	env.leaveScopedFrame();
    	}
    }

	public method getMethodMatching(AbstractSymbol methodName, StaticEnvironment env)
    {
    	if (name == methodName)
    		return this;
    	else
    		return null;
    }
}


/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression init;
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
	@Override
	public void canonicalize(StaticEnvironment env) {
		// TODO Auto-generated method stub
	}
    
	public void analyzeForm(StaticEnvironment env)
    {
		//TODO ensure all conditions satisfied.
    	if (!env.inNativeInstallationMode())
    	{
    		class_ attributeType = env.resolveVariableType(name);
	    	env.enterScopedFrame();
	    	class_ resolvedExpressionType;
	    	if (init instanceof no_expr)
	    	{
	    		resolvedExpressionType = attributeType;
	    		init.set_type(attributeType.getName());
	    	}
	    	else
	    		resolvedExpressionType= init.resolveStaticType(env);
	    	
	    	if (resolvedExpressionType == null)
	    		System.out.println("exceptional circumstances"); //TODO: this should never be true
	    	if (!resolvedExpressionType.subclassOf(attributeType))
	    		env.reportError(new StaticEnvironment.UnexpectedTypeError
	    				(init.getLineNumber(), env.getFilename(), resolvedExpressionType.getName(), attributeType.getName()));
	    	env.leaveScopedFrame();
    	}
    }

    public attr getAttributeMatching(AbstractSymbol variableName, StaticEnvironment env)
    {
    	if (name == variableName)
    		return this;
    	else
    		return null;
    }
}


/** Defines AST constructor 'formal'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formal extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
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

    public AbstractSymbol getName() { return name; }
    public AbstractSymbol getTypeName() { return type_decl; }

}


/** Defines AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression expr;
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
    protected AbstractSymbol name;
    protected Expression expr;
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
    
    public class_ resolveStaticType(StaticEnvironment env)
    {
    	class_ varType = env.getVariableType(name);
    	if (varType != null)
    	{
	    	if (expr.resolveStaticType(env).subclassOf(varType))
	    	{
	    		return varType;
	    	}
	    	else
	    		; // TODO: error expression type conflict
    	}
    	else
    		; // TODO error unbound variable
    	return null;
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol type_name;
    protected AbstractSymbol name;
    protected Expressions actual;
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
    /*
	@Override
	public class_ resolveStaticType(StaticEnvironment env) {
		class_ returnType = env.findClass(this.type_name).getMethodReturnType(this.name, env);
		//TODO add more
		return returnType;
	}
*/
    
    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ result;
    	class_ exprType = expr.resolveStaticType(env);
    	
    	class_ dispatchClass = env.findClass(type_name);
    	method applicableMethod;

    	if (dispatchClass.isSelfType())
    		applicableMethod = env.getCurrentClassDefinition().findMethod(name, env);
    	else
    		applicableMethod = dispatchClass.findMethod(name, env);
    	
    	//TODO: check that the expressions are compatable
    	for (Expression expr : actual.asCollection())
    		expr.resolveStaticType(env);
    	
    	result = env.resolveClass(applicableMethod.return_type, true);
    	if (result.isSelfType())
    		result = exprType;

    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
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
    
    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ result;
    	class_ exprType = expr.resolveStaticType(env);

    	method applicableMethod;
    	if (exprType.isSelfType())
    		applicableMethod = env.getCurrentClassDefinition().findMethod(name, env);
    	else
    		applicableMethod = exprType.findMethod(name, env);
    		
    	//TODO: check that the expressions are compatable
    	for (Expression expr : actual.asCollection())
    		expr.resolveStaticType(env);
    	result = env.resolveClass(applicableMethod.return_type, true);
    	
    	if (result.isSelfType())
    		result = exprType;
    	
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    protected Expression pred;
    protected Expression then_exp;
    protected Expression else_exp;
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

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ result = null;
    	class_ testType = pred.resolveStaticType(env);
    	class_ thenType = then_exp.resolveStaticType(env);
    	class_ elseType = else_exp.resolveStaticType(env);
    	result = env.mostSpecificCommonAncestor(elseType, thenType);
    	//TODO is this it?
    	set_type(result.getName());
    	return result;
    }
}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    protected Expression pred;
    protected Expression body;
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
    
    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ result = env.findClass(TreeConstants.Object_);
    	class_ testType = pred.resolveStaticType(env);
    	class_ bodyType = body.resolveStaticType(env);
    	//TODO test that testType is <= Bool
    	set_type(result.getName());
    	return result;
    }
}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    protected Expression expr;
    protected Cases cases;
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
    

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ result = null;
    	for (branch br : cases.getBranches())
    	{
    		class_ resolvedType = env.findClass(br.type_decl);
    		
    		env.enterScopedFrame();
    		env.registerBinding(br.name, resolvedType);
    		class_ actualExprType = br.expr.resolveStaticType(env);
    		env.leaveScopedFrame();
    		
    		//TODO ensure branch's expression's type matches resolvedType
    		if (result == null)
    			result = actualExprType;
    		else
    			result = env.mostSpecificCommonAncestor(result, actualExprType);
    	}
    	if (result == null) 
    		; //TODO no branches
    	//TODO is this it?
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    protected Expressions body;
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
    

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ result = null;
    	for (Expression expr : body.asCollection())
    		result = expr.resolveStaticType(env);
    	
    	//TODO is this it?
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
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
    

    public class_ resolveStaticType(StaticEnvironment env) {
    	//TODO: SELF_TYPE stuff
    	class_ result = null;
    	env.enterScopedFrame();
    	
    	class_ supposedType = env.resolveClass(type_decl, true);
    	//TODO ensure supposed type is not nil
    	class_ actualType;
    	if (init instanceof no_expr)
		{
			actualType = supposedType;
			init.set_type(supposedType.getName());
		}
    	else
    	{
    		actualType = init.resolveStaticType(env);
    	}
    	//TODO ensure actualType is subclass of supposedType
    	env.registerBinding(identifier, supposedType);
    	result = body.resolveStaticType(env);
    	env.leaveScopedFrame();
    	//TODO is this it?
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
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
    
    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env),
    		   c2 = e2.resolveStaticType(env);
    	//TODO ensure that both are of type Int
    	class_ result =  env.findClass(TreeConstants.Int);
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
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
    
    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env),
    		   c2 = e2.resolveStaticType(env);
    	//TODO ensure that both are of type Int
    	class_ result =  env.findClass(TreeConstants.Int);
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
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
    

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env),
    		   c2 = e2.resolveStaticType(env);
    	//TODO ensure that both are of type Int
    	class_ result =  env.findClass(TreeConstants.Int);
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
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
    

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env),
    		   c2 = e2.resolveStaticType(env);
    	//TODO ensure that both are of type Int
    	class_ result =  env.findClass(TreeConstants.Int);
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    protected Expression e1;
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

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env);
    	//TODO ensure that e1 is of type Int
    	class_ result =  env.findClass(TreeConstants.Int);
    	set_type(result.getName());
    	return result;
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    protected Expression e1;
    protected Expression e2;
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

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env),
    		   c2 = e2.resolveStaticType(env);
    	//TODO ensure that both are of type Int
    	class_ result =  env.findClass(TreeConstants.Bool);
    	set_type(result.getName());
    	return result;
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    protected Expression e1;
    protected Expression e2;
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

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env),
    		   c2 = e2.resolveStaticType(env);
    	//if c1 or c2  E { Int, String, Bool} -> c1 == c2
    	//TODO ensure that both are of type
    	class_ result =  env.findClass(TreeConstants.Bool);
    	set_type(result.getName());
    	return result;
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    protected Expression e1;
    protected Expression e2;
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

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ c1 = e1.resolveStaticType(env),
    		   c2 = e2.resolveStaticType(env);
    	//TODO ensure that both are of type Int
    	class_ result =  env.findClass(TreeConstants.Bool);
    	set_type(result.getName());
    	return result;
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

}


/** WHAT IS THIS CLASS? 
 * Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    protected Expression e1;
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
    

    public class_ resolveStaticType(StaticEnvironment env) {
    	class_ result =  null;
    	//set_type(result.getName());
    	return result;
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }

}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    protected AbstractSymbol token;
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
    
    public class_ resolveStaticType(StaticEnvironment env) { 
    	class_ result =  env.findClass(TreeConstants.Int);
    	set_type(result.getName());
    	return result;
    }

}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    protected Boolean val;
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

    public class_ resolveStaticType(StaticEnvironment env) { 
    	class_ result = env.findClass(TreeConstants.Bool);
    	set_type(result.getName());
    	return result;
    }
}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    protected AbstractSymbol token;
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

    
    public class_ resolveStaticType(StaticEnvironment env) { 
    	class_ result = env.findClass(TreeConstants.Str);
    	set_type(result.getName());
    	return result;
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    protected AbstractSymbol type_name;
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
    
    public class_ resolveStaticType(StaticEnvironment env)
    {
    	class_ result = null;
    	result = env.resolveClass(type_name, true);
    	if (result == null)
    		; // TODO error undeclared type
    	set_type(result.getName());
    	return result;
    }
}


/** Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    protected Expression e1;
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


    public class_ resolveStaticType(StaticEnvironment env) {
    	e1.resolveStaticType(env); 
    	class_ result = env.findClass(TreeConstants.Bool);
    	this.set_type(result.getName());
    	return result;
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
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
    
    

    public class_ resolveStaticType(StaticEnvironment env) {
    	//TODO is this the correct behavior?
    	return null;
    }

}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    protected AbstractSymbol name;
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

    
    public class_ resolveStaticType(StaticEnvironment env) { 
    	class_ result = env.getVariableType(name);
    	if (result == null)
    	{
    		env.reportError(new StaticEnvironment.UndefinedIdentifierError(getLineNumber(), env.getFilename(), name));
    		result = env.findClass(TreeConstants.Object_);
    	}
    	this.set_type(result.getName());
    	return result;
    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }

}


