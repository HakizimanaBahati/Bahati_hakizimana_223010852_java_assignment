package gukina;

import java.util.ArrayList;
import java.util.Scanner;

public class Sports {

   
    static class Player {
        private String name;
        private int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }

    
    static class Team {
        private String teamName;
        private ArrayList<Player> players;

        public Team(String teamName) {
            this.teamName = teamName;
            this.players = new ArrayList<>();
        }

        public void addPlayer(Player player) {
            players.add(player);
        }

        public int getTotalScore() {
            int total = 0;
            for (Player p : players) {
                total += p.getScore();
            }
            return total;
        }

        public Player getTopPlayer() {
            Player top = players.get(0);
            for (Player p : players) {
                if (p.getScore() > top.getScore()) {
                    top = p;
                }
            }
            return top;
        }

        public String getTeamName() {
            return teamName;
        }

        public void displayTeamInfo() {
            System.out.println("Team: " + teamName);
            System.out.println("Players:");
            for (Player p : players) {
                System.out.println("  " + p.getName() + " - Score: " + p.getScore());
            }
            System.out.println("Total Score: " + getTotalScore());
            System.out.println("Top Player: " + getTopPlayer().getName() +
                    " (" + getTopPlayer().getScore() + ")");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of teams: ");
        int numTeams = sc.nextInt();
        sc.nextLine(); 

        Team[] teams = new Team[numTeams];

       
        for (int i = 0; i < numTeams; i++) {
            System.out.print("\nEnter name of Team " + (i + 1) + ": ");
            String teamName = sc.nextLine();
            teams[i] = new Team(teamName);

            System.out.print("Enter number of players in " + teamName + ": ");
            int numPlayers = sc.nextInt();
            sc.nextLine(); 

            for (int j = 0; j < numPlayers; j++) {
                System.out.print("Enter Player " + (j + 1) + " name: ");
                String playerName = sc.nextLine();
                System.out.print("Enter " + playerName + "'s score: ");
                int score = sc.nextInt();
                sc.nextLine(); 

                teams[i].addPlayer(new Player(playerName, score));
            }
        }

        System.out.println("\n=== Teams Information ===");
        for (Team t : teams) {
            t.displayTeamInfo();
        }

 
        Team winner = teams[0];
        for (Team t : teams) {
            if (t.getTotalScore() > winner.getTotalScore()) {
                winner = t;
            }
        }

        System.out.println("🏆 The team with the highest total score is: " +
                winner.getTeamName() + " with " + winner.getTotalScore() + " points!");

        sc.close();
    }
}
