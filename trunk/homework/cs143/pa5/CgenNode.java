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

// This is a project skeleton file

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.Enumeration;

class CgenNode extends class_ {
	
	/** unique integer identifer for this class */
	protected int classTag;
	
    /** The parent of this node in the inheritance tree */
    private CgenNode parent;

    /** The children of this node in the inheritance tree */
    private Vector children;

    /** Indicates a basic class */
    final static int Basic = 0;

    /** Indicates a class that came from a Cool program */
    final static int NotBasic = 1;
    
    /** Does this node correspond to a basic class? */
    private int basic_status;

    /** Constructs a new CgenNode to represent class "c".
     * @param c the class
     * @param basic_status is this class basic or not
     * @param table the class table
     * */
    CgenNode(Class_ c, int basic_status, CgenClassTable table) {
	super(0, c.getName(), c.getParent(), c.getFeatures(), c.getFilename());
	this.parent = null;
	this.children = new Vector();
	this.basic_status = basic_status;
	AbstractTable.stringtable.addString(name.getString());
    }

    void addChild(CgenNode child) {
    	children.addElement(child);
    }

    /** Gets the children of this class
     * @return the children
     * */
    Enumeration getChildren() {
    	return children.elements(); 
    }

    /** Sets the parent of this class.
     * @param parent the parent
     * */
    void setParentNd(CgenNode parent) {
	if (this.parent != null) {
	    Utilities.fatalError("parent already set in CgenNode.setParent()");
	}
	if (parent == null) {
	    Utilities.fatalError("null parent in CgenNode.setParent()");
	}
	this.parent = parent;
    }
    
    void emitPrototype(CgenClassTable ctable, PrintStream str) {
    	//semantic section
    	List<attr> attrlist = canonicalAttributesList();
    	// DEFINITION                 | OFFSET INTO OBJ in bytes 
    	int gcTag    =            -1; // -4
    	int classTag = getClassTag(); // 0
		int size     = attrlist.size() + 3;  // 4
		String dispatchPointer = getDispatchTableRef();
		
		//print section
		str.println("#Prototype object for class " + this.getName()+ "");
		str.print(CgenSupport.WORD); str.println(gcTag);
		str.print(this.getName() + CgenSupport.PROTOBJ_SUFFIX + CgenSupport.LABEL);
		str.print(CgenSupport.WORD); str.println(classTag);
		str.print(CgenSupport.WORD); str.println(size);
		str.print(CgenSupport.WORD); str.println(dispatchPointer);
		
		for (attr attrib : attrlist)
		{
			str.print(CgenSupport.WORD); str.println(attrib.defaultWordValue(ctable));
		}
    }
    
    public CgenNode getSelf() { return this; }
    
    /**
     * Abstract representation of a dispatch table.  Can be turned into assembly
     * and also referenced by other parts of the compiler.  Computes itself on construction
     * given the current inheritance graph.
     * @author reddaly
     *
     */
    public class DispatchTable {
    	public class Entry {
    		public CgenNode classContext;
    		public method meth;
    		public Entry(method m, CgenNode c) { this.meth = m; this.classContext = c; }
    	}
    	public List<Entry> entries = new LinkedList<Entry>();
    	
    	public DispatchTable() { compute(); }
    	
    	public void compute() {
    		fillMethodsUsingClass(getSelf());
    	}
    	
    	protected void fillMethodsUsingClass(CgenNode context) {
    		if (context.parent != null)
    			fillMethodsUsingClass(context.parent);
    		for (method m : context.features.getMethods())
    			addOrReplaceMethod(m, context);
    	}
    	
    	protected void addOrReplaceMethod(method m, CgenNode definitionContext) {
    		boolean replaced = false;
    		Entry newEntry = new Entry(m, definitionContext);
    		for (int i=0; i < entries.size(); i++)
    		{
    			Entry entry = entries.get(i);
    			if (entry.meth.name == m.name)
    			{
    				entries.set(i, newEntry);
    				replaced = true;
    			}
    		}
    		if (replaced == false)
    			entries.add(newEntry);
    	}
    	
    	public String getCodeDef(CgenClassTable ctable) {
    		String result = "";
    		result += "#Dispatch table for " + getName()+ "\n";
    		result += getName() + CgenSupport.DISPTAB_SUFFIX + CgenSupport.LABEL;
    		for (Entry entry : entries)
    		{
    			result += CgenSupport.WORD + entry.classContext.getName() + "." + entry.meth.name + "\n";
    		}
    		return result;
    	}
    }

	public void emitDispatchTable(CgenClassTable ctable, PrintStream str)
	{
		str.print(new DispatchTable().getCodeDef(ctable));
	}
    
    /**
     * 
     * @return value to fill in for this type of class when no expression is given
     */
    String getDefaultWordValue(CgenClassTable ctable) {
    	if (this.basic_status == Basic)
    	{
    		if (isTypeInteger())
    			return ctable.findIntSymbol("0").getCodeRef() + " # basic type default for " + name;
    		else if (isTypeBool())
        		return BoolConst.falsebool.getCodeRef() + " # basic type default for " + name;
    		else if (isTypeString())
        		return ctable.findStringSymbol("").getCodeRef() + " # basic type default for " + name;
    		else
    			return "0 # basic type initialized to VOID"; //"TODO DEFAULT OBJECT ASSIGNMENT.. 0?" ;
    	}
    	else
    	{
    		//String prototypeRef = ctable.findStringSymbol(this.name.toString()).getCodeRef();
    		//return prototypeRef + " # prototype object initial value";
    		return "0 #non-basic intialization to null";
    	}
    }
    
    public boolean isTypeInteger()   { return name.getString().toLowerCase().equals("int"); }
    public boolean isTypeString()    { return name.getString().toLowerCase().equals("string"); }
    public boolean isTypeBool()    { return name.getString().toLowerCase().equals("bool"); }
    
    /**
     * List of attributes on this class, in significant order that corresponds to the stored form
     * of the object's record.
     * @return
     */
    List<attr> canonicalAttributesList() {
    	LinkedList<attr> attribs = new LinkedList<attr>();
    	if (parent != null)
    		attribs.addAll(parent.canonicalAttributesList());
    	attribs.addAll(features.getAttributes());
    	return attribs;
    }
    
    /**
     * List of attributes on this class, in significant order that corresponds to the stored form
     * of the object's record.
     * @return
     */
    List<method> canonicalMethodList() {
    	LinkedList<method> methods = new LinkedList<method>();
    	if (parent != null)
    		methods.addAll(parent.canonicalMethodList());
		methods.addAll(features.getMethods());
    	return methods;
    }
    
    String getDispatchTableRef() {
    	return this.getName() + CgenSupport.DISPTAB_SUFFIX;
    }
    


	public void emitMethodDefinitions(CompliationEnvironment env, PrintStream str) {
		env.classdef = this;
		if (basic()) // do nothing for basic classes
			return;
		str.println("# METHOD DEFINITIONS FOR " + getName() + " \n");
		for (method m : features.getMethods())
		{
			str.print(m.getCode(env));
		}
	}
	

    /** Gets the parent of this class
     * @return the parent
     * */
	CgenNode getParentNd() {
		return parent; 
    }

    /** Returns true is this is a basic class.
     * @return true or false
     * */
    boolean basic() { 
    	return basic_status == Basic; 
    }

	public int getClassTag() {
		return classTag;
	}

	public void setClassTag(int classTag) {
		this.classTag = classTag;
	}

	/**
	 Object initialization is the other tricky part of implementation.  Each object has a label
called `classname_init' that corresponds to its initialization procedure.  After an object
has been copied into a new memory location with Object.copy and the prototype object pointer
in the $a0 register, this segment of code is called.  It will not modify $a0 and will
follow the semantics specified in the `new' operational semantics.  The init procedure
is fairly fairly tricky because it is has similar semantics to method definitions.
The prototype objects already provide the default values for each attribute.  Next,
the superclass's initialization procedure is called. Finally, Each
attribute initialization expression is evaluated in the order of declaration in
a static environment with the current class as its context class, and the newly
initialized object is returned.
	 */
	public void emitInitialization(CgenClassTable ctable, PrintStream str) {
		str.println(initializationLabel() + CgenSupport.LABEL);
		CompliationEnvironment env = new CompliationEnvironment(ctable, this);
		if (parent == null)
		{
			//call parent initializer
			str.println("jal " + initializationLabel());
		}
		//create frame for initialization of attributes
		int nonArgumentFrameBytes = 12;
		int stackSize = nonArgumentFrameBytes;
    	str.println("\t sw $fp,  0($sp) # store frame pointer in top-most portion of stack");
    	str.println("\t move $fp, $sp");
		env.enterFrame();
		str.print(env.codeStackPush(stackSize));
    	str.println("\t sw $ra,  -4($fp) # store ra ");
    	str.println("\t sw $s0,  -8($fp) # store ra ");
    	
    	//str.println("\t move $s0, $a0");
    	
    	//initialize all attributes
		for (int i=0; i < canonicalAttributesList().size(); i++) {
			attr attrib = canonicalAttributesList().get(i);
			if (attrib.init == null || attrib.init instanceof no_expr)
			{
				str.println("\t #no init for attribute -> VOID" + attrib.name);
				str.print("\t sw $zero " + getAttributeOffset(attrib) + "($s0)\n");
			}
			else
			{
				str.print(attrib.init.getCode(env));
				str.print("\t sw $a0 " + getAttributeOffset(attrib) + "($s0)\n");
			}
		}

		//pop frame
    	str.println("\t lw $ra,  -4($fp) # load back ra ");
    	str.println("\t lw $s0,  -8($fp) # load back ra ");
    	str.println("\t lw $fp,   0($fp) # store frame pointer in top-most portion of stack");
    	str.print(env.codeStackPop(stackSize));
    	str.println("\t jr $ra");
	}
	
	public String getCodeForAttributeRef(AbstractSymbol sym, String toRegister, String objPointerRegister) {
		return "\t lw " + toRegister + " " + getAttributeOffset(sym) + "(" + objPointerRegister + ")\n";
	}
	
	public int getAttributeOffset(attr attrib) {
		return getAttributeOffset(attrib.name);
	}
	
	public int getAttributeOffset(AbstractSymbol sym) {
		for (int i=0; i < canonicalAttributesList().size(); i++) {
			if (sym == canonicalAttributesList().get(i).name)
				return 12 + 4 * i;
		}
		return -1;
		
	}
	
	public method getMethodByName(AbstractSymbol sym )
	{
		for (method m : features.getMethods())
			if (m.name == sym)
				return m;
		return null;
	}
	
	public String initializationLabel() { 
		return getName() + CgenSupport.CLASSINIT_SUFFIX;
	}
}
    

    
