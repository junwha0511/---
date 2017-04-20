import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int a,b;
		Scanner scan = new Scanner(System.in);
		a=scan.nextInt();
		b=scan.nextInt();
		System.out.printf("%.3f", ((float)a*b/2));
	}

}
