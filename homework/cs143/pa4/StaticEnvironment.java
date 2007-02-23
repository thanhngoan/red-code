import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Holds the environment context at a particular time during semantic analysis.
 * 
 * We use the StaticEnvironment to hold object and method bindings and also
 * the self object.
 * 
 * The environment object is responsible for the environmental semantics.  What this means
 * is that all identifier lookups, and many other rules rules pertaining to the environment
 * are encaspulated here in case they should change in the future.  Of course, due to the
 * primitve semantics of Java, this is not an easy feat so we defer a lot of the node-specific
 * cases to treenode methods.
 * 
 * @author reddaly
 */
public class StaticEnvironment {
	
	public void warn(int lineNumber, AbstractSymbol filename, String message)
	{
		System.err.println("Warning (" + filename + ", line " + lineNumber + "): " + message);
	}
	
	public static class SemanticError {
		SemanticError(int lineNumber, AbstractSymbol filename, String message)
		{
			System.err.println("Error in " + filename + ", line " + lineNumber + ": " + message);
		}
	}
	
	public static class UndefinedIdentifierError extends SemanticError {
		UndefinedIdentifierError(int lineNumber, AbstractSymbol filename, AbstractSymbol identifier) {
			super(lineNumber, filename, "Undefined identifier: '" + identifier + "'");
			// TODO Auto-generated constructor stub
		}
		
	}
	
	public static class NoMainClassError extends SemanticError {

		NoMainClassError(int lineNumber, AbstractSymbol filename) {
			super(lineNumber, filename, "No main class found.");
			// TODO Auto-generated constructor stub
		}
		
	}

	public static class InheritanceError extends SemanticError {
		InheritanceError(int lineNumber, AbstractSymbol filename, String message) {
			super(lineNumber, filename, message);
		}
	}
	

	public static class ClassRedefinitionError extends InheritanceError {
		ClassRedefinitionError(int lineNumber, AbstractSymbol filename, AbstractSymbol name) {
			super(lineNumber, filename, "Illegal class redefinition: '" + name + "'");
		}
	}

	public static class UnresolvedSuperclassError extends InheritanceError {

		UnresolvedSuperclassError(int lineNumber, AbstractSymbol filename, AbstractSymbol type) {
			super(lineNumber, filename, "Unknown superclass: " + type);
		}
	}
	
	public static class IllegalSuperclassError extends InheritanceError {

		IllegalSuperclassError(int lineNumber, AbstractSymbol filename, AbstractSymbol name) {
			super(lineNumber, filename, "Illegal superclass: '" + name + "'");
		}
		
	}
	
	public static class UnresolvedTypeError extends SemanticError {

		UnresolvedTypeError(int lineNumber, AbstractSymbol filename, AbstractSymbol type) {
			super(lineNumber, filename, "Unknown type: " + type);
		}
	}
	public static class UnexpectedTypeError extends SemanticError {
		UnexpectedTypeError(int lineNumber, AbstractSymbol filename, AbstractSymbol type, AbstractSymbol expected) {
			super(lineNumber, filename, "Bad type '" + type +"', expected type '" + expected + "'");
		}
	}

	public class ObjectBinding {
		public AbstractSymbol identifier;
		public class_ type;
		public ObjectBinding(AbstractSymbol s, class_ t) {
			identifier = s; type = t;
		}
	}
	
	public class MethodBinding {
		public AbstractSymbol identifier;
		public method meth;
		public MethodBinding(AbstractSymbol s, method m) {
			identifier = s; meth = m;
		}
	}
	
	//ClassTable classTable;
	protected Stack<ObjectBinding> activeBindings;
	protected Stack<Integer> scopeFrameBindingCounts;
	protected int frameCounter;
	protected class_ currentClassDefinition;
	protected Map<AbstractSymbol, class_> classTable;
	protected boolean nativeMode; // true when we are installing native types
	protected int errorCount;
	
	StaticEnvironment(program prog)
	{
		frameCounter = 0;
		activeBindings = new Stack<ObjectBinding>();
		scopeFrameBindingCounts = new Stack<Integer>();
		//classTable = new ClassTable(prog.getClasses());
		currentClassDefinition = null;
		classTable = new HashMap<AbstractSymbol, class_>();
		initializeCoolPrimitives();
	}
	
	/**
	 * @param sym
	 * @return the class in this environment with the given symbol as the name
	 */
	public class_ findClass(AbstractSymbol sym)
    {
		return classTable.get(sym);
    	//return classTable.findClass(sym);
    }
	
	public class_ resolveClass(AbstractSymbol name, boolean allowSelfType)
	{
		class_ result = null;
		if (name == TreeConstants.SELF_TYPE)
    		result = this.makeNewSelfType(0);
		else
			result = findClass(name);
		return result;
	}
	
	/**
	 * Canonicalizes the given class in the environment.
	 * @param clazz
	 */
	public void registerClass(class_ clazz)
	{
		classTable.put(clazz.getName(), clazz);
	}

	/**
	 * Called when beginning a class definition with the provided class
	 * @param clazz
	 */
	public void enterClassDefinition(class_ clazz)
	{
		currentClassDefinition = clazz;
		enterScopedFrame();
		addScopedVariableBinding(new ObjectBinding(TreeConstants.self, makeNewSelfType(0)));
	}

	/**
	 * Called when leaving a class definition of the provided class.
	 * @param clazz
	 */
	public void leaveClassDefinition(class_ clazz)
	{
		leaveScopedFrame();
		currentClassDefinition = null;
	}

	protected void enterScopedFrame()
	{
		scopeFrameBindingCounts.push(0);
	}

	protected void leaveScopedFrame()
	{
		int numBindingsToPop = scopeFrameBindingCounts.pop();
		for (int i=0; i < numBindingsToPop; i++)
			activeBindings.pop();
	}
	
	public void addScopedVariableBinding(ObjectBinding binding)
	{
		activeBindings.push(binding);
		int numBindingsThisFrame = scopeFrameBindingCounts.pop();
		scopeFrameBindingCounts.push(numBindingsThisFrame + 1);
	}
	
	/**
	 * Searches the current environment for the binding of variableName and returns the type.
	 * @param variableName
	 * @return the type of the given identifier in the current environment
	 */
	public class_ getVariableType(AbstractSymbol variableName)
	{
		class_ result = null;

		if (getCurrentClassDefinition() == null)
			result = null;
		else
			result = getCurrentClassDefinition().getMemberType(variableName, this);
		
		for (ObjectBinding binding : activeBindings)
			if (binding.identifier == variableName)
				result = binding.type;
		return result;
	}
	
	public class_ resolveVariableType(AbstractSymbol variableName)
	{
		class_ result = getVariableType(variableName);
		return result;
	}
	
	public class_ getMethodReturnType(AbstractSymbol methodName)
	{
		if (getCurrentClassDefinition() == null)
			return null;
		else
			return getCurrentClassDefinition().getMethodReturnType(methodName, this);
	}
	
	public class_ getCurrentClassDefinition()
	{
		return currentClassDefinition;
	}
	
	public void finalizeEnvironment()
	{
		//if we have errored fatally 
		if (getErrorCount() > 0) {
		    System.err.println("Compilation halted due to " + getErrorCount() + " static semantic errors.");
		    System.exit(1);
		}
	}
	
	public int getErrorCount() { return errorCount; }
	
	/**
	 * Returns the superclass of the provided classes that is the lowest in the type hierarchy. 
	 * @param one
	 * @param two
	 * @return
	 */
	public class_ mostSpecificCommonAncestor(class_ one, class_ two)
	{
		class_ mostSpecific = null;
		if (one.subclassOf(two))
			return two;
		else if (two.subclassOf(one))
			return one;
		for (class_ super1 : one.getDirectSuperclasses())
			if ((mostSpecific = mostSpecificCommonAncestor(super1, two)) != null)
				break;
		return mostSpecific;
	}
	
	public class_ mostSpecificCommonAncestor(Collection<class_> classes)
	{
		return null;
	}
	
	public class_ resolveIdentifierType(AbstractSymbol symbol)
	{
		return getVariableType(symbol);
	}
	
	public void registerBinding(AbstractSymbol symbol, class_ type)
	{
		this.addScopedVariableBinding(new ObjectBinding(symbol, type));
	}
	
	public void enterNativeInstallation()
	{
		nativeMode = true;
	}
	
	public void leaveNativeInstallation()
	{
		nativeMode = false;
	}
	
	public boolean inNativeInstallationMode() { return nativeMode; }
	
	public void reportError(SemanticError error)
	{
		errorCount++;
	}
	
	public AbstractSymbol getFilename()
	{
		if (getCurrentClassDefinition() != null)
			return getCurrentClassDefinition().filename;
		else
			return null;
	}
	
	protected void initializeCoolPrimitives()
	{
		AbstractSymbol filename = AbstractTable.stringtable.addString("<basic class>");
		
		// The following demonstrates how to create dummy parse trees to
		// refer to basic Cool classes.  There's no need for method
		// bodies -- these are already built into the runtime system.

		// IMPORTANT: The results of the following expressions are
		// stored in local variables.  You will want to do something
		// with those variables at the end of this method to make this
		// code meaningful.

		// The Object class has no parent class. Its methods are
		//        cool_abort() : Object    aborts the program
		//        type_name() : Str        returns a string representation 
		//                                 of class name
		//        copy() : SELF_TYPE       returns a copy of the object

		class_ Object_class = 
		    new class_(0, 
			       TreeConstants.Object_, 
			       TreeConstants.No_class,
			       new Features(0)
				   .appendElement(new method(0, 
						      TreeConstants.cool_abort, 
						      new Formals(0), 
						      TreeConstants.Object_, 
						      new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.type_name,
						      new Formals(0),
						      TreeConstants.Str,
						      new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.copy,
						      new Formals(0),
						      TreeConstants.SELF_TYPE,
						      new no_expr(0))),
			       filename);
		
		// The IO class inherits from Object. Its methods are
		//        out_string(Str) : SELF_TYPE  writes a string to the output
		//        out_int(Int) : SELF_TYPE      "    an int    "  "     "
		//        in_string() : Str            reads a string from the input
		//        in_int() : Int                "   an int     "  "     "

		class_ IO_class = 
		    new class_(0,
			       TreeConstants.IO,
			       TreeConstants.Object_,
			       new Features(0)
				   .appendElement(new method(0,
						      TreeConstants.out_string,
						      new Formals(0)
							  .appendElement(new formal(0,
									     TreeConstants.arg,
									     TreeConstants.Str)),
						      TreeConstants.SELF_TYPE,
						      new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.out_int,
						      new Formals(0)
							  .appendElement(new formal(0,
									     TreeConstants.arg,
									     TreeConstants.Int)),
						      TreeConstants.SELF_TYPE,
						      new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.in_string,
						      new Formals(0),
						      TreeConstants.Str,
						      new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.in_int,
						      new Formals(0),
						      TreeConstants.Int,
						      new no_expr(0))),
			       filename);

		// The Int class has no methods and only a single attribute, the
		// "val" for the integer.

		class_ Int_class = 
		    new class_(0,
			       TreeConstants.Int,
			       TreeConstants.Object_,
			       new Features(0)
				   .appendElement(new attr(0,
						    TreeConstants.val,
						    TreeConstants.prim_slot,
						    new no_expr(0))),
			       filename);

		// Bool also has only the "val" slot.
		class_ Bool_class = 
		    new class_(0,
			       TreeConstants.Bool,
			       TreeConstants.Object_,
			       new Features(0)
				   .appendElement(new attr(0,
						    TreeConstants.val,
						    TreeConstants.prim_slot,
						    new no_expr(0))),
			       filename);

		// The class Str has a number of slots and operations:
		//       val                              the length of the string
		//       str_field                        the string itself
		//       length() : Int                   returns length of the string
		//       concat(arg: Str) : Str           performs string concatenation
		//       substr(arg: Int, arg2: Int): Str substring selection

		class_ Str_class =
		    new class_(0,
			       TreeConstants.Str,
			       TreeConstants.Object_,
			       new Features(0)
				   .appendElement(new attr(0,
						    TreeConstants.val,
						    TreeConstants.Int,
						    new no_expr(0)))
				   .appendElement(new attr(0,
						    TreeConstants.str_field,
						    TreeConstants.prim_slot,
						    new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.length,
						      new Formals(0),
						      TreeConstants.Int,
						      new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.concat,
						      new Formals(0)
							  .appendElement(new formal(0,
									     TreeConstants.arg, 
									     TreeConstants.Str)),
						      TreeConstants.Str,
						      new no_expr(0)))
				   .appendElement(new method(0,
						      TreeConstants.substr,
						      new Formals(0)
							  .appendElement(new formal(0,
									     TreeConstants.arg,
									     TreeConstants.Int))
							  .appendElement(new formal(0,
									     TreeConstants.arg2,
									     TreeConstants.Int)),
						      TreeConstants.Str,
						      new no_expr(0))),
			       filename);

		program primitivesProgram = new program(0,
				new Classes(0)
				  .appendElement(Object_class)
				  .appendElement(Str_class)
				  .appendElement(Bool_class)
				  .appendElement(Int_class)
				  .appendElement(IO_class));
		
		enterNativeInstallation();
		primitivesProgram.semanticallyAnalyzeInEnvironment(this);
		leaveNativeInstallation();
	}
	

	protected void initializeCoolPrimitives2()
	{
		AbstractSymbol objectSym = AbstractTable.idtable.addString("Object");
		AbstractSymbol boolSym =   AbstractTable.idtable.addString("Bool");
		AbstractSymbol intSym =    AbstractTable.idtable.addString("Int");
		AbstractSymbol stringSym = AbstractTable.idtable.addString("String");
		AbstractSymbol selfTypeSym = AbstractTable.idtable.addString("SELF_TYPE");
		AbstractSymbol coolFilename = AbstractTable.idtable.addString("<cool.cl>");
		Expression noExpression = new no_expr(0);
		//The primitive object "Object"
		Feature objAbortFeature = new method(0,
								  AbstractTable.idtable.addString("abort"),
								  new Formals(0),
								  objectSym,
								  noExpression);
		Feature objTypeNameFeature = new method(0,
				  AbstractTable.idtable.addString("type_name"),
				  new Formals(0),
				  stringSym,
				  noExpression);
		Feature objCopyFeature = new method(0,
				  AbstractTable.idtable.addString("copy"),
				  new Formals(0),
				  stringSym,
				  noExpression);
		Features objectFeatures = new Features(0);
		objectFeatures.appendElement(objTypeNameFeature)
					  .appendElement(objCopyFeature)
					  .appendElement(objAbortFeature);
								  
		class_ objectClass = new class_(0, objectSym, null, objectFeatures, coolFilename);

		// IO class
		class_ ioClass = new class_(0,
				AbstractTable.idtable.addString("IO"),
				AbstractTable.idtable.addString("Object"),
				new Features(0).appendElement(
						new method(0,
								  // out_string (x : String) : SELF_TYPE
								  AbstractTable.idtable.addString("out_string"),
								  new Formals(0).appendElement(
										  new formal(0,
												    AbstractTable.idtable.addString("x"),
										  			stringSym)),
								  selfTypeSym,
								  noExpression))
							    .appendElement(
						new method(0,
								  // out_int (x : Int) : SELF_TYPE
								  AbstractTable.idtable.addString("out_int"),
								  new Formals(0).appendElement(new formal(0,
		  								  AbstractTable.idtable.addString("x"),
		  								  intSym)),
										  selfTypeSym,
										  noExpression))
								 .appendElement(
						new method(0,
								  // in_string() : String
								  AbstractTable.idtable.addString("in_string"),
								  new Formals(0),
								  stringSym,
								  noExpression))
								 .appendElement(
						new method(0,
								  // in_int() : Int
								  AbstractTable.idtable.addString("in_int"),
								  new Formals(0),
								  intSym,
								  noExpression))
						,								   
				coolFilename);

		// String class
		class_ stringClass = new class_(0,
				AbstractTable.idtable.addString("String"),
				AbstractTable.idtable.addString("Object"),
				new Features(0).appendElement(
						new method(0,
								  // substr(i : Int, l : Int) : String
								  AbstractTable.idtable.addString("substr"),
								  new Formals(0).appendElement(
										  new formal(0,
												    AbstractTable.idtable.addString("i"),
												    intSym)).appendElement(
										  new formal(0,
												     AbstractTable.idtable.addString("l"),
												     intSym)),
						  		  stringSym,
						  		noExpression))
							    .appendElement(
						new method(0,
								  // concat (s : String) : String
								  AbstractTable.idtable.addString("concat"),
								  new Formals(0).appendElement(new formal(0,
		  								  AbstractTable.idtable.addString("s"),
		  								  stringSym)),
		  								  stringSym,
		  								noExpression))
								 .appendElement(
						new method(0,
								  // length() : Int
								  AbstractTable.idtable.addString("length"),
								  new Formals(0),
								  intSym,
								  noExpression))
						,								   
				coolFilename);

		// Bool class
		class_ boolClass = new class_(0,
				AbstractTable.idtable.addString("Bool"),
				AbstractTable.idtable.addString("Object"),
				new Features(0),								   
				coolFilename);
		
		// Bool class
		class_ intClass = new class_(0,
				AbstractTable.idtable.addString("Int"),
				AbstractTable.idtable.addString("Object"),
				new Features(0),								   
				coolFilename);
		
		program primitivesProgram = new program(0,
				new Classes(0).appendElement(objectClass)
				  .appendElement(intClass)
				  .appendElement(boolClass)
				  .appendElement(stringClass)
				  .appendElement(ioClass));
		enterNativeInstallation();
		primitivesProgram.semanticallyAnalyzeInEnvironment(this);
		leaveNativeInstallation();
	}
	
	
	class_ makeNewSelfType(int lineNumber) { 
		return new class_(lineNumber, TreeConstants.SELF_TYPE, null, null, getFilename());
	}
	
}
