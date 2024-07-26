import java.io.*;
import java.util.*;
//number of digits in the name is stack size
public class PythonTracer {
	public static final int SPACE_COUNT = 4;
	static int size;
	static int finalDigit;
	static String blockName;

	/**
	 * 
	 * @param filename This is the filename that the user will enter
	 * @return This method returns the highest sub complexity
	 * @throws IOException
	 */
	public static Complexity traceFile(String filename) throws IOException {
		Stack<CodeBlock> j = new Stack<CodeBlock>();
		Scanner reader = new Scanner(new File(filename));

		while (reader.hasNextLine()) {
			String data = reader.nextLine();
			if (!(data.trim().isEmpty()) && !(data.trim().charAt(0) == '#')) {
				int indents = (data.indexOf(data.trim())) / (SPACE_COUNT);
				while (indents < size) {
					if (indents == 0) {
						reader.close();
						CodeBlock oldTop = j.peek();
						int ncomp = oldTop.getBlockComplexity().getnPower()
								+ oldTop.getHighestSubComplexity().getnPower();
						int logcomp = oldTop.getHighestSubComplexity().getLogPower()
								+ oldTop.getBlockComplexity().getLogPower();
						Complexity oldTopComplexity = new Complexity();
						oldTopComplexity.setnPower(ncomp);
						oldTopComplexity.setLogPower(logcomp);
						return oldTopComplexity;

					} else {
						CodeBlock oldTop = j.pop();
						System.out.println(
								"Leaving block " + oldTop.getName() + ", updating block " + j.peek().getName() + ":");
						finalDigit = Integer.parseInt(oldTop.getName().substring(oldTop.getName().length() - 1));
						size--;
						int ncomp = oldTop.getBlockComplexity().getnPower()
								+ oldTop.getHighestSubComplexity().getnPower();
						int logcomp = oldTop.getHighestSubComplexity().getLogPower()
								+ oldTop.getBlockComplexity().getLogPower();
						Complexity oldTopComplexity = new Complexity();
						oldTopComplexity.setnPower(ncomp);
						oldTopComplexity.setLogPower(logcomp);
						if (oldTopComplexity.greaterThan(j.peek().getHighestSubComplexity())) {
							j.peek().setHighestSubComplexity(oldTopComplexity);
						}
						System.out.println("Block " + j.peek().getName() + ":             block complexity = "
								+ j.peek().getBlockComplexity() + "             highest sub-complexity = "
								+ j.peek().getHighestSubComplexity() + "\n");
					}
					
				}
				if (data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.FOR])
						|| data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.DEF])
						|| data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELIF])
						|| data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.ELSE])
						|| data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.WHILE])
						|| data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.IF])) {

					if (data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.FOR])) {
						Complexity f = new Complexity();
						if (data.contains("log_N")) {
							f = new Complexity();
							f.setLogPower(1);
						} else {
							f = new Complexity();
							f.setnPower(1);
						}

						CodeBlock k = new CodeBlock();
						k.setBlockComplexity(f);
						Complexity o = new Complexity();
						o.setnPower(0);
						k.setHighestSubComplexity(o);
						String newName = j.peek().getName() + "." + (1 + finalDigit);
						j.push(k);
						j.peek().setName(newName);
						System.out.println("Entering block " + newName + " 'for':");
						System.out.println("Block " + j.peek().getName() + ":             block complexity = "
								+ j.peek().getBlockComplexity() + "             highest sub-complexity = "
								+ j.peek().getHighestSubComplexity() + "\n");
						size++;

					} else if (data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.WHILE])) {
						String loopVariable = data.substring(data.indexOf(">") - 2, data.indexOf(">") - 1);
						CodeBlock c = new CodeBlock();
						c.setLoopVariable(loopVariable);
						Complexity h = new Complexity();
						h.setnPower(0);
						c.setBlockComplexity(h);
						Complexity f = new Complexity();
						f.setnPower(0);
						c.setHighestSubComplexity(f);
						String newName = j.peek().getName() + "." + (1 + finalDigit);
						j.push(c);
						j.peek().setName(newName);
						System.out.println("Entering block " + newName + " 'while':");
						System.out.println("Block " + j.peek().getName() + ":             block complexity = "
								+ j.peek().getBlockComplexity() + "             highest sub-complexity = "
								+ j.peek().getHighestSubComplexity() + "\n");
						size++;
					}

					else {
						if (data.contains(CodeBlock.BLOCK_TYPES[CodeBlock.DEF])) {
							CodeBlock c = new CodeBlock();
							Complexity h = new Complexity();
							h.setnPower(0);
							c.setBlockComplexity(h);
							Complexity f = new Complexity();
							f.setnPower(0);
							c.setHighestSubComplexity(f);
							j.push(c);
							size++;
							j.peek().setName("1");
							System.out.println("Entering block " + j.peek().getName() + " 'def':");
							System.out.println("Block " + j.peek().getName() + ":             block complexity = "
									+ j.peek().getBlockComplexity() + "             highest sub-complexity = "
									+ j.peek().getHighestSubComplexity() + "\n");
						} else {
							CodeBlock c = new CodeBlock();
							Complexity h = new Complexity();
							h.setnPower(0);
							c.setBlockComplexity(h);
							Complexity f = new Complexity();
							f.setnPower(0);
							c.setHighestSubComplexity(f);
							String newName = j.peek().getName() + "." + (1 + finalDigit);
							j.push(c);
							j.peek().setName(newName);
							System.out.println("Entering block " + newName+":");
							System.out.println("Block " + j.peek().getName() + ":             block complexity = "
									+ j.peek().getBlockComplexity() + "             highest sub-complexity = "
									+ j.peek().getHighestSubComplexity() + "\n");
							size++;
						}

					}
				} else if (j.peek().getLoopVariable() != null && (data.contains(j.peek().getLoopVariable())
						&& (data.contains("-=") || data.contains("/=")))) {
					if (data.contains("-=")) {
						Complexity w = new Complexity();
						w.setnPower(1);
						j.peek().setBlockComplexity(w);
					} else if (data.contains("/=")) {
						Complexity w = new Complexity();
						w.setLogPower(1);
						j.peek().setBlockComplexity(w);
					}
					System.out.println("Found update statement, updating block " + j.peek().getName() + ":");
					System.out.println("Block " + j.peek().getName() + ":             block complexity = "
							+ j.peek().getBlockComplexity() + "             highest sub-complexity = "
							+ j.peek().getHighestSubComplexity() + "\n");
				}
			}

			else {

			}
		}
		while (size > 1) {
			CodeBlock oldTop = j.pop();
			size--;
			int ncomp = oldTop.getBlockComplexity().getnPower() + oldTop.getHighestSubComplexity().getnPower();
			int logcomp = oldTop.getHighestSubComplexity().getLogPower() + oldTop.getBlockComplexity().getLogPower();
			Complexity oldTopComplexity = new Complexity();
			oldTopComplexity.setnPower(ncomp);
			oldTopComplexity.setLogPower(logcomp);
			if (oldTopComplexity.greaterThan(j.peek().getHighestSubComplexity())) {
				j.peek().setHighestSubComplexity(oldTop.getBlockComplexity());
			}
		}
		System.out.println("Leaving block " + j.peek().getName() + "." + "\n");
		size--;
		System.out.print("Overall complexity of " + filename + ":");
		return j.pop().getHighestSubComplexity();
	}

	public static void main(String[] args) {
		boolean run = true;
		do {
			Scanner sk = new Scanner(System.in);
			System.out.println("Please enter a file name (or 'quit' to quit):");
			String filename = sk.nextLine();
			if (filename.equalsIgnoreCase("quit")||filename.equalsIgnoreCase("'quit'")) {
				run = false;
			} else {
				try {
					System.out.println(traceFile(filename));
				} catch (IOException e) {
					System.out.println(
							"Please make sure your file is in the right location and you type the name with the file type correctly");
				}
			}
		} while (run);
		System.out.println("Programming terminating successfully..");
	}
}
