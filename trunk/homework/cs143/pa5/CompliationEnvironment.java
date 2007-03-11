import java.util.LinkedList;
import java.util.List;

class CompliationEnvironment {
	public CgenClassTable ctable;
	public CgenNode classdef;
	
	public static int labelCount = 0;
	
	protected int fpOffset = 0;
	
	public class Binding {
		public int            fpOffset;
		public AbstractSymbol name;
	}
	
	protected List<Binding> bindings = new LinkedList<Binding>();
	


    public CompliationEnvironment(CgenClassTable ctable2, CgenNode node) {
		ctable = ctable2; classdef = node;
	}

	public String evalExpressionsIntoRegisters(Expression[] expressions, String[] registers) {
		String result = "";
		
		int requiredStackSize = expressions.length * 4 + 4;
		result += "\t addi $sp, $sp, -" + requiredStackSize + "  # push stack for multiple expression evaluation\n";
		result += "\t sw $ra, " + (requiredStackSize - 4) + "($sp)     # store ra \n";
		
		//compute expressions
		for (int i=0; i < expressions.length; i++) {
			result += expressions[i].getCode(this);
			result += "\t sw $a0, " + i *4 +"($sp)     # store expression\n";
		}
		// restore expressions
		for (int i=0; i < expressions.length; i++) {
			result += "\t lw " + registers[i] +", " + i *4 +"($sp)     # load results into register results \n";
		}
		result += "\t lw $ra, " + (requiredStackSize - 4) + "($sp)     # restore ra after multiple expression evaluation \n";
		result += "\t addi $sp, $sp, " + requiredStackSize + "  # pop stack\n";
		return result;
	}
    
    public String codePushRAOnStack() {
    	String result = "";
		result += "\t addi $sp, $sp, -8  # push stack to store RA\n";
		result += "\t sw   $ra, 4($sp)  # \n";
		return result;
    }
    
    public String codeRestoreRA() {
    	String result = "";
		result += "\t lw   $ra, 4($sp)  # \n";
		result += "\t addi $sp, $sp, 8  # restore RA\n";
		return result;
    }
    
    public void enterFrame() {
    	fpOffset = 0;
    }
    
    public String codeStackPush(int bytes) {
    	fpOffset -= bytes;
    	return "\t addi $sp, $sp, -" + bytes + " \n";
    }
    
    public String codeStackPop(int bytes) {
    	fpOffset += bytes;
    	return "\t addi $sp, $sp, " + bytes + " \n";
    }
    
    public String genLabel(String hint) { return hint +  labelCount++; }

	public String codeSetBoolPointer(String valueRegister, String outRegister) {
		String trueRef  = BoolConst.truebool.getCodeRef(),
    	       falseRef = BoolConst.falsebool.getCodeRef();
		//falseRef = trueRef;
		String falseLabel = genLabel("IsFalse");
		String afterLabel = genLabel("AfterBoolSet");
    	String result = "";
		result += "\t beq " + valueRegister + ", $zero, " + falseLabel + "\n";
		result += "\t la "+ outRegister + ", " + trueRef + "\n";
		result += "\t j " + afterLabel + "\n";
		result += "\t " + falseLabel + CgenSupport.LABEL + "\n";
		result += "\t la "+ outRegister + ", " + falseRef + "\n";
		result += "\t " + afterLabel + CgenSupport.LABEL + "\n";
		//result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";
		return result;
	}

	public int getFPOffset() {
		return fpOffset;
	}

	public void setFPOffset(int fpOffset) {
		this.fpOffset = fpOffset;
	}
	
	public Binding getBinding(AbstractSymbol sym) {
		for (int i= bindings.size()-1; i >= 0; i--)
		{
			if (bindings.get(i).name == sym)
				return bindings.get(i);
		}
		return null;
	}

	public void addBinding(Binding b) {
		bindings.add(b);
	}

	public void popBinding(Binding b) {
		bindings.add(b);
		for (int i= bindings.size()-1; i >= 0; i--)
		{
			if (bindings.get(i) == b)
			{
				bindings.remove(i);
				return;
			}
		}
	}
	
	public void pushLocalBinding(int fpOffset, AbstractSymbol symbol) {
		Binding b = new Binding();
		b.name = symbol;
		b.fpOffset = fpOffset;
		addBinding(b);
	}
	
	public void popLocalBinding() {
		bindings = bindings.subList(0, bindings.size() - 1);
	}
	
	
	

	public String codeAndBind(int fpOffset, AbstractSymbol symbol, String register) {
		Binding b = new Binding();
		b.name = symbol;
		b.fpOffset = fpOffset;
		addBinding(b);
		return "\t sw " + register + " " + fpOffset + "($fp)\n";
	}
	
	
	
	
	
	
}
