
import java.time.LocalDate;
import java.util.Scanner;

public class AccountArray {
    private Creation creation = new Creation();
    private int MAX_ACCOUNTS = 10;
    private Account[] accountArray = new Account[MAX_ACCOUNTS];
    private Series[] seriesArray = creation.series;
    private int countUsers = 0;
    private int lastEpisodeWatched = 0, seriesWatched = 0;
    private boolean[] seriesFinished = new boolean[seriesArray.length];
    private boolean[] seriesStarted = new boolean[seriesArray.length];

    public boolean login(String username, String password) {
        for (int i = 0; i < countUsers; i++) {
            if(accountArray[i].getUsername().equals(username) && accountArray[i].getPassword().equals(password))
                return true;
        }
        System.out.println("\u001B[31m"+"Invalid username or password"+"\u001B[0m");
        return false;
    }

    public void register(String username, String password) {
        Scanner scanner = new Scanner(System.in);
        if (countUsers == MAX_ACCOUNTS) {
            growArray();
        }

        while (isUserExists(username)) {
            System.out.println("\u001B[31m"+"Username already exists"+"\u001B[0m");
            System.out.println("Enter new username: ");
            username = scanner.nextLine();
        }

        while (!isPasswordValid(password)) {
            System.out.println("\u001B[31m"+"Password not valid"+"\u001B[0m");
            System.out.println("\u001B[36m"+"Make sure password contains at least 1 letter and 1 digit with a minimum of 6 characters"+"\u001B[0m");
            System.out.println("Please enter new password: ");
            password = scanner.nextLine();
        }

        accountArray[countUsers] = new Account(username, password);
        accountArray[countUsers].setDateOfCreation(LocalDate.now());
        accountArray[countUsers].setEndOfSubscription(accountArray[countUsers].getDateOfCreation().plusMonths(1));
        System.out.println("\u001B[32m"+"Registered successfully!"+"\u001B[0m");
        countUsers++;
    }

    public void userInfo(String username) {
        int countSeriesStarted = 0, countSeriesFinished = 0, countEpisodesWatched = 0;
        for(int i=0;i<countUsers;i++) {
            if (accountArray[i].getUsername().equals(username)) {
                System.out.println("\u001B[35m" + "User: " + username + "\u001B[0m");
                System.out.println("\u001B[36m" + "Registered date: " + accountArray[i].getDateOfCreation() + "\u001B[0m");
                System.out.println("\u001B[36m" + "End of subscription: " + accountArray[i].getEndOfSubscription() + "\u001B[0m");
                for (int j = 0; j < seriesStarted.length; j++) {
                    if (seriesStarted[j])
                        countSeriesStarted++;
                }
                System.out.println("\u001B[36m" + "Started watching " + countSeriesStarted + " series" + "\u001B[0m");

                for (int j = 0; j < seriesFinished.length; j++) {
                    if (seriesFinished[j])
                        countSeriesFinished++;
                }
                System.out.println("\u001B[36m" + "Finished watching " + countSeriesFinished + " series" + "\u001B[0m");

                for (int j = 0; j < seriesArray.length; j++) {
                    countEpisodesWatched += seriesArray[j].getCountWatched();
                }

                System.out.println("\u001B[36m" + "Watched " + countEpisodesWatched + " episodes overall" + "\u001B[0m");
            }
        }
    }

    public void listOfSeries() {
        for (int i=0; i < seriesArray.length; i++) {
            System.out.println("\u001B[36m"+(i+1)+". "+seriesArray[i].getSeriesName()+"\u001B[0m");
        }
    }

    public void listOfStartedSeries() {
        boolean isStarted = false;
        int count = 0;
        for (int i=0; i < seriesArray.length; i++) {
            if (seriesStarted[i]) {
                System.out.println("\u001B[36m" + (count + 1) + ". " + seriesArray[i].getSeriesName() + "\u001B[0m");
                count++;
                isStarted = true;
            }
        }
        if (!isStarted)
            System.out.println("\u001B[31m"+"You haven't started watching any series"+"\u001B[0m");
    }

    public void listOfEpisodes(String seriesName) {
        Scanner scanner = new Scanner(System.in);
        boolean isFound = false;
        for (int i=0; i < seriesArray.length; i++) {
            if (seriesArray[i].getSeriesName().equals(seriesName)) {
                System.out.println("List of Episodes of " + seriesName + " Series: ");
                for (int j = 0; j < seriesArray[i].getEpisodes().length; j++) {
                    System.out.print("\u001B[36m");
                    System.out.print("Episode " + (j + 1) + ": \"" + seriesArray[i].getEpisodes()[j].getEpisodeName() + "\" - \"");
                    System.out.print(seriesArray[i].getEpisodes()[j].getEpisodeDesc() + "\" - Added on: ");
                    System.out.println(seriesArray[i].getEpisodes()[j].getDateAired() + "\u001B[0m");
                    isFound = true;
                }
            }
        }
        if (!isFound) {
            System.out.println("\u001B[31m" + "The series name you entered does not exist!" + "\u001B[0m");
            System.out.println("Please enter a correct Series name:");
            seriesName = scanner.nextLine();
            listOfEpisodes(seriesName);
        }
    }

    private boolean isUserExists(String username) {
        for (int i=0; i<countUsers; i++) {
            if (accountArray[i].getUsername().equals(username))
                return true;
        }
        return false;
    }

    private boolean isPasswordValid(String password) {
        if (!(password.length() >= 6)) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        int countDigits = 0;
        for (int i = 0; i <= 9; i++) {
            if(password.contains(Integer.toString(i))) {
                countDigits++;
            }
        }

        if (countDigits == 0)
            return false;

        int countLetters = 0;
        for (int i = 65; i <= 90; i++) {
            if(password.contains(Character.toString((char) i))) {
                countLetters++;
            }
        }

        for (int i = 90; i <= 122; i++) {
            if(password.contains(Character.toString((char) i))) {
                countLetters++;
            }
        }

        if (countLetters == 0)
            return false;

        return true;
    }

    private void growArray() {
        Account[] tempArray = null;
        if (countUsers == MAX_ACCOUNTS) {
            tempArray = new Account[MAX_ACCOUNTS*2];
            for (int i = 0; i < MAX_ACCOUNTS; i++) {
                tempArray[i] = accountArray[i];
            }
        }
        accountArray = tempArray;
        MAX_ACCOUNTS++;
    }

    public void lastWatchedEpisode(String seriesName) {
        boolean isWatched = false;
        for (int i = 0; i < seriesArray.length; i++) {
            if (seriesArray[i].getSeriesName().equals(seriesName)) {
                for (int j = 0; j < seriesArray[i].getEpisodes().length; j++) {
                    if (seriesArray[i].getEpisodes()[j].isWatched())
                        isWatched = true;
                }
            }
        }
        if (!isWatched)
            System.out.println("\u001B[35m" + "Haven't watched the series" + "\u001B[0m");
        else
            System.out.println("\u001B[35m" + "Last watched episode: " + (lastEpisodeWatched+1) +
                    ". " + seriesArray[seriesWatched].getEpisodes()[lastEpisodeWatched].getEpisodeName() + "\u001B[0m");
    }

    public void addEpisodeWatched(String seriesName, int episodeNumber) {
        for (int i = 0; i < seriesArray.length; i++) {
            if (seriesArray[i].getSeriesName().equals(seriesName)) {
                for (int j = 0; j < seriesArray[i].getEpisodes().length; j++) {
                    if (seriesArray[i].getCountWatched() == 0) {
                        seriesStarted[i] = true;
                    }

                    if (seriesArray[i].getEpisodes()[j].getEpisodeName().equals(seriesArray[i].getEpisodes()[episodeNumber-1].getEpisodeName())) {
                        seriesArray[i].getEpisodes()[j].setWatched(true);
                        seriesArray[i].setCountWatched(seriesArray[i].getCountWatched()+1);
                        lastEpisodeWatched = j;
                        seriesWatched = i;
                    }

                    if (seriesArray[i].getEpisodes().length == seriesArray[i].getCountWatched()) {
                        seriesFinished[i] = true;
                        System.out.println("\u001B[32m"+"You have watched every episode of "+seriesArray[i].getSeriesName()+"\u001B[0m");
                    }
                }
            }
        }
    }

    public Series[] getSeriesArray() {
        return seriesArray;
    }

    public void setSeriesArray(Series[] seriesArray) {
        this.seriesArray = seriesArray;
    }

    public boolean[] getSeriesFinished() {
        return seriesFinished;
    }

    public void setSeriesFinished(boolean[] seriesFinished) {
        this.seriesFinished = seriesFinished;
    }
}
