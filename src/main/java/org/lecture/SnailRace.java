package org.lecture;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * class of snailRace
 */
@Getter
public class SnailRace {

    private final ArrayList<Crawler> crawlers;
    private final Double distance;
    private final Double winnerTime;

    /**
     * snail race constructor
     * @param crawlers list of crawlers taking part in the race
     */
    public SnailRace(ArrayList<Crawler> crawlers) {
        this.crawlers = crawlers;
        this.distance = getDistanceInput();
        this.winnerTime = calculateWinnerTime();
    }

    /**
     * gets Distance input from user in mm
     * @return Double (distance in mm)
     */
    private Double getDistanceInput() {
        System.out.println("""
                How many millimeters long should the distance be?
                (Be careful - don't make it too long, otherwise you wait for ages. Keep in mind - it's about snails/slugs!)""");
        Double distanceDouble = null;
        try {
            Scanner sc = new Scanner(System.in);
            String distanceString = sc.nextLine();

            if(distanceString.contains(","))
                distanceString = distanceString.replaceAll(",", ".");
            distanceDouble = Double.valueOf(distanceString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distanceDouble;
    }

    /**
     * calculates time the fastest crawler needs to finish the race, measured in milliseconds
     * @return time in milliseconds the fastest crawler needs to win the race
     */
    private Double calculateWinnerTime() {
        Crawler winner = Collections.max(this.crawlers, Comparator.comparingDouble(Crawler::getVelocity));
        return this.distance/winner.getVelocity();
    }

    /**
     * applies the crawl() method to every crawler of the crawlers array list of this object
     */
    public void letCrawlersCrawl() {
        for(Crawler crawler : this.crawlers) {
            crawler.crawl(this.winnerTime);
        }
    }

    /**
     * executes the race
     * simulates the waiting time
     * gives back the winning crawler and the time it needed
     * gives back all the other crawlers and the distance they made until the winning crawler reached the finish line
     */
    public void executeRace() {
        Double millisecondsToBePassed = (this.distance/getVelocityOfFastestCrawler(crawlers))*1000;

        letCrawlersCrawl();

        long sleepingTime = millisecondsToBePassed.longValue();
        TimeUnit time = TimeUnit.MILLISECONDS;

        System.out.println("Race started!!! . . .");

        try {
            time.sleep(sleepingTime);
            System.out.println("The race took "
                    + sleepingTime
                    + " milliseconds.");
        } catch (InterruptedException e) {
            System.out.println("Interrupted while racing.");
        }

        ArrayList<Crawler> fastestCrawlers = new ArrayList<>();
        fastestCrawlers = getFastestCrawlers(crawlers);

        if(fastestCrawlers.size()>1) {
            System.out.println("The winners are: ");
            for(Crawler crawler : fastestCrawlers) {
                System.out.println(crawler.toString());
            }

            ArrayList<Crawler> listWithoutWinners = crawlers;
            for(Crawler crawler : fastestCrawlers) {
                listWithoutWinners.remove(crawler);
            }

            System.out.println("\nPerformance of others:");
            for(Crawler crawler : listWithoutWinners) {
                System.out.println(crawler.toString());
            }
        } else {
            System.out.println("The winner is: " + getFastestCrawler(crawlers).toString() + "\n");

            ArrayList<Crawler> listWithoutWinner = crawlers;
            listWithoutWinner.remove(getFastestCrawler(crawlers));

            System.out.println("Performance of others:");
            for(Crawler crawler : listWithoutWinner) {
                System.out.println(crawler.toString());
            }
        }
    }

    /**
     * gets the velocity of the fastest crawler of the crawlers list of this object
     * @param crawlers takes the crawlers array list of this class/object
     * @return returns the velocity in Double format of the fastest crawler
     */
    public static Double getVelocityOfFastestCrawler(ArrayList<Crawler> crawlers) {
        Crawler fastestCrawler = Collections.max(crawlers, Comparator.comparingDouble(Crawler::getVelocity));
        return fastestCrawler.getVelocity();
    }

    /**
     * gets the fastest crawler of the crawlers list
     * @param crawlers takes in the crawlers array list
     * @return gives back the fastest crawler in a Crawler object
     */
    public static Crawler getFastestCrawler(ArrayList<Crawler> crawlers) {
        return Collections.max(crawlers, Comparator.comparingDouble(Crawler::getVelocity));
    }

    public static ArrayList<Crawler> getFastestCrawlers(ArrayList<Crawler> crawlers) {

        ArrayList<Crawler> fastestCrawlers = new ArrayList<>();

        for(Crawler crawler : crawlers) {
            if(crawler.getVelocity()==getVelocityOfFastestCrawler(crawlers)) {
                fastestCrawlers.add(crawler);
            }
        }
        return fastestCrawlers;
    }
}