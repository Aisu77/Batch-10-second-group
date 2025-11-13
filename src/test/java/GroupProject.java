import java.util.Scanner;

public class GroupProject {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a language: ");
        String greeting= scanner.nextLine();

        switch(greeting){
            case "english":
                System.out.println("Hello!");
                break;
            case "spanish":
                System.out.println("Hola!");
                break;
            case "french":
                System.out.println("Bonjour!");
                break;
            case "kyrgyz":
                System.out.println("Salam!");
                break;
            default:
                System.out.println("Not found");
                break;
        }
    }
}
