import java.util.Scanner;

public class MainWindow {
    public static void main(String[] args) {
        new MainWindow();
    }

    public MainWindow() {
        int choice;
        String username, password;
        AccountArray accountArray = new AccountArray();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\u001B[33m" + "(1) Sign in \n(2) Sign up\n(3) Exit" + "\u001B[0m");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter username: ");
                username = scanner.next();
                System.out.println("Enter password: ");
                password = scanner.next();
                if (accountArray.login(username, password)) {
                    System.out.println("\u001B[32m" + "Signed in successfully" + "\u001B[0m");
                    showSignedInOptions(accountArray,username);
                }
            } else if (choice == 2) {
                System.out.println("Enter username: ");
                username = scanner.next();

                System.out.println("\u001B[36m" + "Make sure password contains atleast 1 letter and 1 digit with a minimum of 6 characters" + "\u001B[0m");
                System.out.println("enter password: ");
                password = scanner.next();

                accountArray.register(username, password);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid input, Please choose again");
            }
        } while (true);
    }

    public void showSignedInOptions(AccountArray accountArray, String username) {
        int choice, episodeChoice;
        String seriesName;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\u001B[33m" + "(1) Show list of all series \n(2) Show list of series you started watching " +
                    "\n(3) Show account information \n(4) Choose series to watch \n(5) Sign out" + "\u001B[0m");
            choice = scanner.nextInt();

            if (choice == 1) {
                accountArray.listOfSeries();
            }
            if (choice == 2) {
                accountArray.listOfStartedSeries();
            }
            if (choice == 3) {
                accountArray.userInfo(username);
            }
            if (choice == 4) {
                scanner.nextLine(); //had to add this line since without it line 62 is just skipped
                System.out.println("Enter Series name: ");
                seriesName = scanner.nextLine();
                accountArray.listOfEpisodes(seriesName);
                accountArray.lastWatchedEpisode(seriesName);
                System.out.println("What episode would you like to watch?");
                episodeChoice = scanner.nextInt();
                accountArray.addEpisodeWatched(seriesName, episodeChoice);

            }
            if (choice == 5) {
                break;
            }
        } while (true);
    }
}
