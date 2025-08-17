package inputHandler;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    // Static method to get a string input
    public String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int inputInt(String prompt) {
        return inputInt(prompt, 100);
    }

    // Static method to get an integer input
    public int inputInt(String prompt , int maximumExclusive) {
        while (true) {
            System.out.print(prompt);
            try {
                int number = Integer.parseInt(scanner.nextLine());
                if( number >= 0 && number < maximumExclusive){
                    return number;
                }else{
                    throw new Error("*** Invalid input. Please reenter your input: ");
                }

            } catch (NumberFormatException e) {
                continue;
            }
        }
    }

    public String getDirectionInput(){
        String str = input("Enter direction (U for up, D for down, L for left, R for right): ");
        boolean isValid = false;
        while (!isValid) {
            if (str.equalsIgnoreCase("U") || str.equalsIgnoreCase("D") || str.equalsIgnoreCase("L") || str.equalsIgnoreCase("R")) {
                isValid = true;
            } else {
                str = input("*** The input direction is unavailable. Please enter an available direction: ");
            }
        }
        return str.toUpperCase();
    }

    public String getEquipmentCode(){
        String str = input("Enter the short name of an equipment:").toLowerCase();
        boolean isValid = false;
        while (!isValid){
            switch (str){
                case "td":
                case "ws":
                case "cm":
                case "ch":
                case "cl":
                case "wb":
                case "ph":
                case "no":
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid equipment code. Please enter a valid short name:");
                    str = input("Enter the short name of an equipment:");
                    break;
            }
        }
        return str;
    }

    public String processAnswer(int id){
        String str = input("Choose the action of Researcher "+id +" : ");
        boolean isValid = false;
        while(!isValid){
            switch (str){
                case "1":
                case "2":
                case "3":
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid number:");
                    str = input("Choose the action of Researcher "+id +" : ");
                    break;
            }
        }
        return str;
    }

    // Ensure scanner is closed when the application ends
    public void close() {
        scanner.close();
    }
}
