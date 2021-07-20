import java.util.Scanner;
public class Birthday {
    
    public void birthdayInvitation() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name: ");
		String name = sc.nextLine();
		System.out.println("Hey "+name+", it's my birthday this weekend and you are invited to the party at my place. Bring gifts!!!🥰");
        sc.close();
	}

    public static void main(String[] args) {
        Birthday b1 = new Birthday();
        b1.birthdayInvitation();
    }
}
