import java.util.Scanner;
public class IDCard {
    
    private String name;
	private int age;
	private char group;
	private String msg;
	public void getData() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name: ");
		name = sc.nextLine();
		System.out.print("Enter your Age: ");
		age = sc.nextInt();
		System.out.print("Enter Blood Group(ex: A): ");
		group = sc.next().charAt(0);

		if(age >= 20) {
			msg = "RED";
		} else if(age >= 15) {
			msg = "BLUE";
		} else if(age >= 10) {
			msg = "YELLOW";
		}

		sc.close();
	}
	public void idCard() {
        System.out.println();
		System.out.println("------------------------------------");
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Blood Group: " + group);
		System.out.println("------------------------------------");
		System.out.println("Your Group is: "+msg);
		System.out.println("------------------------------------");
	}

    public static void main(String[] args) {
        IDCard obj = new IDCard();
        obj.getData();
        obj.idCard();
    }
}
