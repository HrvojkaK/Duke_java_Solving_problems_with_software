
public class Main
{
	public static void main(String[] args) {
		CaesarBreaker cb = new CaesarBreaker();
		cb.testCaesarCipher();
		cb.testCaesarBreaker();
		System.out.println("Two key cipher and breaker:");
		TwoKeysCaesarBreaker tk = new TwoKeysCaesarBreaker();
		tk.testTwoKeys();
		tk.testBreakTwoKeys();
	}
}