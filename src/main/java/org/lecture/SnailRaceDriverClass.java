package org.lecture;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * driver class for snail race
 */
@Slf4j
public class SnailRaceDriverClass {

    /**
     * main method of driver class for snail race
     */
    public static void main(String[] args) {

        System.out.println("""
                
                ####### Welcome to the snail race simulator. #######
                """);

        ArrayList<Crawler> crawlers = getListOfPartakingCrawlers();
        log.info("List of crawlers from user input finished.");

        SnailRace snailRace = new SnailRace(crawlers);
        snailRace.executeRace();
        log.info("Race is done.");

    }

    /**
     * gets the list of partaking crawlers from the user
     * @return array list of crawlers
     */
    public static ArrayList<Crawler> getListOfPartakingCrawlers() {

        int numberOfPartakingCrawlers = getNumberOfPartakingCrawlers();
        ArrayList<Crawler> crawlers = new ArrayList<>();

        for(int i = 1; i<= numberOfPartakingCrawlers; i++) {

           System.out.println("This is the configuration of your " + i + ". snail/slug in the crawler race.");

           Boolean isSnail = getDecisionIfTrueSnailIfFalseSlug();
           String name = getNameInput();
           Double velocity = getVelocityInput();

           if(isSnail) {
               ShellColour shellColour = getColourInput();
               Crawler raceParticipant = new Snail.SnailBuilder()
                       .withName(name)
                       .withVelocity(velocity)
                       .withShellColour(shellColour)
                       .build();
               crawlers.add(raceParticipant);
           } else {
               Boolean livesBelowEarth = getLivesBelowEarth();
               Crawler raceParticipant = new Slug.SlugBuilder()
                       .withName(name)
                       .withVelocity(velocity)
                       .withLivesBelowEarth(livesBelowEarth)
                       .build();
               crawlers.add(raceParticipant);
           }
        }
        log.info("All crawlers are entered by the user into an array list.");
        return crawlers;
    }

    /**
     * gets decision from user if snail or slug
     * @return Boolean true if snail, false if slug
     */
    public static Boolean getDecisionIfTrueSnailIfFalseSlug() {
        System.out.println("Do you want it to be a snail or a slug? Type (1) to get a snail, type (2) to get a slug.");
        int option = 0;
        try {
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User did not type (1) for snail/(2) for slug but something else - program exited (0).");
        }
        Boolean isSnail = null;
        switch(option) {
            case 1 -> isSnail = true;
            case 2 -> isSnail = false;
            default -> System.exit(0);
        }
        return isSnail;
    }

    /**
     * gets number of partaking crawlers the user wants to have
     * @return int number of crawlers
     */
    public static int getNumberOfPartakingCrawlers() {
        System.out.println("How many crawlers (snails and/or slugs) should take part in the race?");
        int numberOfPartakingCrawlers = 0;
        try {
            Scanner sc = new Scanner(System.in);
            numberOfPartakingCrawlers = sc.nextInt();
            sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User did not type a number of how many snails/slugs he/she wants to take part in the race - program exited (0).");
        }
        return numberOfPartakingCrawlers;
    }

    /**
     * gets name from user chosen for snail/slug
     * @return String name
     */
    public static String getNameInput() {
        System.out.println("What's the name of this snail/slug?");
        String name = null;
        try {
            Scanner sc = new Scanner(System.in);
            name = sc.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User failed to enter the name of the snail/slug in question. Program exited (0).");
        }
        return name;
    }

    /**
     * gets velocity of chosen snail/slug from the user
     * @return Double value of velocity (mm/seconds)
     */
    public static Double getVelocityInput() {
        System.out.println("How many millimeters per second does this racer proceed on average? Please enter it's mm/s value.");
        Double veloDouble = null;
        try {
            Scanner sc = new Scanner(System.in);
            String veloString = sc.nextLine();

            if(veloString.contains(","))
                veloString = veloString.replaceAll(",", ".");
            veloDouble = Double.valueOf(veloString);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User failed to enter the velocity of the snail/slug in question. Program exited (0).");
        }
        return veloDouble;
    }

    /**
     * gets Colour of shell for a snail from the user
     * @return enum ShellColour
     */
    public static ShellColour getColourInput() {
        ShellColour shellColour = null;
        System.out.println("Is your snail (1) yellow, (2) brown, or (3) pink? Please enter the respective number.");

        int option = 0;

        try {
            Scanner sc = new Scanner(System.in);
            option = sc.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User failed to enter the option of colour for the snail/slug in question. Program exited (0).");
        }
        switch(option) {
            case 1 -> shellColour = ShellColour.YELLOW;
            case 2 -> shellColour = ShellColour.BROWN;
            case 3 -> shellColour = ShellColour.PINK;
            default -> System.exit(0);
        }
        return shellColour;
    }

    /**
     * gets information if slug lives below earth or not
     * @return Boolean true if living below earth, false if not
     */
    public static Boolean getLivesBelowEarth() {
        Boolean livesBelowEarth = null;
        System.out.println("Does your slug live below earth? Please type y for yes, n for no.");

        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();

        switch(option) {
            case "y" -> livesBelowEarth = true;
            case "n" -> livesBelowEarth = false;
            default -> System.exit(0);
        }
        return livesBelowEarth;
    }

}