package Arkanoid;

public class ExceptionEmptyLine extends Exception{
	public ExceptionEmptyLine() {
		 super("You entered empty");
	 }
	 public ExceptionEmptyLine(Throwable t) {
		super(t);
	 }
	 public ExceptionEmptyLine(String st) {
		 super(st);
	 }
	 public ExceptionEmptyLine(String st, Throwable t) {
		 super(st, t);
	 }
}