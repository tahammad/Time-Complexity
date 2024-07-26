package hw3;
/**
 * 
 * @author Tanjim Ahammad
 * SBU Email: tanjim.ahammad@stonybrook.edu
 * Recitation: R03
 * SBU ID:114863193
 */

public class CodeBlock {
/**
 * Contains final string block type that will be used to see whether a block contains these block headers
 * 	Final ints are created to make identifying the indices in the String array easier for each header.
 */
static final String[] BLOCK_TYPES= {"def "," for ", "while ", "if", "elif", "else" };
static final int DEF = 0 ;
static final int FOR = 1;
static final int WHILE = 2;
static final int IF = 3;
static final int ELIF = 4;
static final int ELSE = 5;
private Complexity blockComplexity;
private Complexity highestSubComplexity; 
private String name;
private String loopVariable;

//Getters and setters to set the block and sub complexities as well as the loop variable.
public Complexity getBlockComplexity() {
	return blockComplexity;
}

public void setBlockComplexity(Complexity blockComplexity) {
	this.blockComplexity = blockComplexity;
}

public Complexity getHighestSubComplexity() {
	return highestSubComplexity;
}

public void setHighestSubComplexity(Complexity highestSubComplexity) {
	this.highestSubComplexity = highestSubComplexity;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getLoopVariable() {
	return loopVariable;
}

public void setLoopVariable(String loopVariable) {
	this.loopVariable = loopVariable;
}

	public static void main(String[] args) {
CodeBlock k=new CodeBlock();
k.setLoopVariable("");
System.out.println(k.getLoopVariable());
	}

}
