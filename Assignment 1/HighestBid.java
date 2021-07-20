import java.util.Scanner;
public class HighestBid {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		System.out.print("First Friend bid: ");
		int f1 = sc.nextInt();
		System.out.print("Second Friend bid: ");
		int f2 = sc.nextInt();
		System.out.print("Third Friend bid: ");
		int f3 = sc.nextInt();

		if(f1 > f2 && f1 > f3) {
			System.out.println("First Win");
		} else if(f2 > f3) {
			System.out.println("Second Win");
		} else {
			System.out.println("Third Win");
		}
		sc.close();
    }
}