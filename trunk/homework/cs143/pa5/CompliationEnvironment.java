
class CompliationEnvironment {
	public CgenClassTable ctable;
	public CgenNode classdef;
	
	public int labelCount = 0;
	


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
    
    public String codeStackPush(int bytes) {
    	return "\t addi $sp, $sp, " + bytes + " \n";
    }
    
    public String codeStackPop(int bytes) {
    	return "\t addi $sp, $sp, -" + bytes + " \n";
    }
    
    public String genLabel(String hint) { return hint + "_" + labelCount++; }

	public String codeSetBoolPointer(String valueRegister, String outRegister) {
		String trueRef  = BoolConst.truebool.getCodeRef(),
    	       falseRef = BoolConst.falsebool.getCodeRef();
		String falseLabel = genLabel("IsFalse");
		String trueLabel = genLabel("IsTrue");
		String afterLabel = genLabel("AfterBoolSet");
    	String result = "";
		result += "\t beq $t0, $zero, " + falseLabel + "\n";
		result += "\t la "+ outRegister + ", " + trueRef + "\n";
		result += "\t j " + afterLabel + "\n";
		result += "\t " + falseLabel + CgenSupport.LABEL + "\n";
		result += "\t la "+ outRegister + ", " + falseRef + "\n";
		result += "\t " + afterLabel + CgenSupport.LABEL + "\n";
		result += "\t " + afterLabel + CgenSupport.LABEL + "\n";
		//result += "\t jal Object.copy    # Create a new integer that's a copy of the second argument \n";
		return result;
	}
	
	
}
