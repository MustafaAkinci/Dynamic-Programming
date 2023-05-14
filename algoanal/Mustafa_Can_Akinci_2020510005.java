import java.io.File;
import java.util.Scanner;

public class Mustafa_Can_Akinci_2020510005 {
    public static void main(String[] args) {
        int n = 3;
        int p = 5;
        int c = 5;
        try {
            Scanner sc = new Scanner(new File("players_salary.txt")); // reading the file
            sc.nextLine();
            int a = 0;
            while (sc.hasNextLine()) { // Finds the number of elements of the array to be opened
                sc.nextLine();
                a = a + 1;
            }
            int[] playersSalary = new int[a];
            sc = new Scanner(new File("players_salary.txt")); // //reading the file
            sc.nextLine();
            int k = 0;
            while (sc.hasNextLine()) { // assigns data from file to array
                String line = sc.nextLine();
                String[] token = line.split("\t");
                String lastToken = token[token.length - 1].trim();
                playersSalary[k] = Integer.parseInt(lastToken);
                // System.out.println(lastToken);
                k++;
            }

            Scanner sc2 = new Scanner(new File("yearly_player_demand.txt")); // reading the file
            sc2.nextLine();
            int b = 0;
            while (sc2.hasNextLine()) { // Finds the number of elements of the array to be opened
                sc2.nextLine();
                b = b + 1;
            }
            int[] playerDemand = new int[b];
            sc2 = new Scanner(new File("yearly_player_demand.txt")); //// reading the file
            sc2.nextLine();
            int j = 0;
            while (sc2.hasNextLine()) { //// assigns data from file to array
                String line2 = sc2.nextLine();
                String[] token2 = line2.split("\t");
                String lastToken2 = token2[token2.length - 1].trim();
                playerDemand[j] = Integer.parseInt(lastToken2);
                // System.out.println(lastToken2);
                j++;
            }
            System.out.println(DP(n, p, c, playersSalary, playerDemand)); // prints the minimum cost

            sc.close();
            sc2.close();
        } catch (Exception e) {
            System.err.println(e);
        }

    }

    static public int DP(int n, int p, int c, int[] playersSalary, int[] playerDemand) {
        int cost = 0; // initial cost is defined as 0
        int different; // the difference variable is defined
        int previousPlayers = 0; // previous player is identified
        for (int i = 0; i < n; i++) {
            different = playerDemand[i] - p - previousPlayers; // The difference between the player demand and the p
                                                               // value is calculated after subtracting the number of
                                                               // players.
            previousPlayers = 0;
            if (different >= 0) { // If player demand is greater than p after subtracting players
                cost = cost + different * c; // Cost is added to the cost multiplied by the difference
            } else {
                if (i + 1 != n && playerDemand[i + 1] - p > 0) { // If the current player's index is less than the last
                                                                 // element's index and the next player's demand is
                                                                 // greater than p
                    if (Math.abs(different) > Math.abs(playerDemand[i + 1] - p)) { // If the absolute value of the
                                                                                   // difference is greater than the
                                                                                   // absolute value of the next
                                                                                   // player's demand
                        previousPlayers = Math.abs(playerDemand[i + 1] - p); // The previous player count is made the
                                                                             // absolute value of the next player's
                                                                             // request
                    } else
                        previousPlayers = Math.abs(different); // If not, the previous player count, the absolute value
                                                               // of the difference is made
                    cost = cost + playersSalary[previousPlayers - 1]; // The cost is added to the salary of the player
                                                                      // corresponding to the previous number of
                                                                      // players.
                }
            }
        }
        return cost; //// finally the total cost is returned.
    }
}