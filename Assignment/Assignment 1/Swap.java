import java.util.Scanner;
public class Swap {
    
    public void swapNumber() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter first number: ");
		int num1 = sc.nextInt();
		System.out.print("Enter second number: ");
		int num2 = sc.nextInt();

		System.out.println("num1: "+num1+" num2: "+num2);

		int temp = num1;
		num1 = num2;
		num2 = temp;

		System.out.println("num1: "+num1+" num2: "+num2);
        sc.close();
	}

    public static void main(String[] args) {
        Swap s1 = new Swap();
        s1.swapNumber();
    }
}
