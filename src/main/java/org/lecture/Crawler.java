package org.lecture;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Class representing a crawler
 */
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Crawler {

    @EqualsAndHashCode.Include
    protected final String name;

    private final double velocity;

    private Double crawlerDistance;

    /**
     * Crawler constructor
     * @param name of crawler
     * @param velocity of crawler
     */
    protected Crawler(String name, double velocity) {
        this.name = name;
        this.velocity = velocity;
        this.crawlerDistance = null;
    }

    /**
     * 'crawl' calculates the distance a crawler has reached with its velocity
     * in that point of time when the fastest one has won
     * @param winnerTime number of milliseconds the fastest crawler needed to win the race
     */
    public void crawl(Double winnerTime) {
        this.crawlerDistance = velocity*winnerTime;
    }

    /**
     * overriding the default toString method
     * @return String with name, velocity and respective crawlerDistance (how many
     * mm reached at the point of time when the winning crawler reached its aim)
     */
    public String toString() {
        return name + " with a velocity of " + velocity + " @ distance " + crawlerDistance;
    }
}