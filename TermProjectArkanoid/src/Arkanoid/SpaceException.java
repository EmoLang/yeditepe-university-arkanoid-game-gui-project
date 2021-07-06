package Arkanoid;

public class SpaceException extends Exception{
	public SpaceException() {
		 super("You entered empty");
	 }
	 public SpaceException(Throwable t) {
		super(t);
	 }
	 public SpaceException(String st) {
		 super(st);
	 }
	 public SpaceException(String st, Throwable t) {
		 super(st, t);
	 }
}
