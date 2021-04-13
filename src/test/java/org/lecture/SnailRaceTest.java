package org.lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class SnailRaceTest {

    @Test
    public void testWinnerTimeCalculation() {

        ArrayList<Crawler> crawlers = new ArrayList<>();

        Snail slowCrawler = new Snail.SnailBuilder()
                .withName("xy")
                .withVelocity(4.99)
                .withShellColour(ShellColour.BROWN)
                .build();
        crawlers.add(slowCrawler);

        Slug fastCrawler = new Slug.SlugBuilder()
                .withName("ab")
                .withVelocity(5.5)
                .withLivesBelowEarth(true)
                .build();
        crawlers.add(fastCrawler);

        //simulate input
        String input = "55";
        System.setIn(new ByteArrayInputStream(input.getBytes()));  //mock System.in
        SnailRace snailRace = new SnailRace(crawlers);

        Assertions.assertEquals(10,snailRace.getWinnerTime());

    }

    @Test
    public void testGetFastestCrawler() {

        ArrayList<Crawler> crawlers = new ArrayList<>();

        Snail slowCrawler = new Snail.SnailBuilder()
                .withName("xy")
                .withVelocity(1.1)
                .withShellColour(ShellColour.BROWN)
                .build();
        crawlers.add(slowCrawler);

        Slug fastCrawler = new Slug.SlugBuilder()
                .withName("ab")
                .withVelocity(1.10001)
                .withLivesBelowEarth(true)
                .build();
        crawlers.add(fastCrawler);

        Assertions.assertEquals(fastCrawler, SnailRace.getFastestCrawler(crawlers));
    }

}
