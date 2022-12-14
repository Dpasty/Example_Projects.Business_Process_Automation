import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(args, scanner);
        boolean isWork = true;

        while (isWork) {
            menu.displayMenu();
            int mode;
            try {
                mode = Integer.parseInt(scanner.next());
                switch (mode) {
                    case 1:
                        menu.addUser();
                        break;
                    case 2:
                        menu.getUserBalance();
                        break;
                    case 3:
                        menu.perfomTransfer();
                        break;
                    case 4:
                        menu.viewAllTransactionForUser();
                        break;
                    case 5:
                        if (menu.getDevMode()) {
                            menu.remoteTransferById();
                        } else {
                            isWork = false;
                        }
                        break;
                    case 6:
                        if (menu.getDevMode()) {
                            menu.checkTransferValidity();
                        } else {
                            throw new RuntimeException();
                        }
                        break;
                    case 7:
                        if (menu.getDevMode()) {
                            isWork = false;
                        } else {
                            throw new RuntimeException();
                        }
                        break;
                    default:
                        System.out.println("Несуществующий вариант, пожалуйста, повторите ввод");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + "\nПожалуйста, повторите ввод");
            }
        }
    }
}
