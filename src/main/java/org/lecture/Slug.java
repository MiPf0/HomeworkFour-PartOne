package org.lecture;

import lombok.Getter;

/**
 * Representation of a crawler: "Slug"
 */
@Getter
public final class Slug extends Crawler {

    //there are types of slugs that live below the earth, others above
    private final Boolean livesBelowEarth;

    /**
     * Slug constructor
     * @param name
     * @param velocity
     * @param livesBelowEarth - if slug lives below earth or not
     */
    private Slug(String name, double velocity, Boolean livesBelowEarth) {
        super(name, velocity);
        this.livesBelowEarth = livesBelowEarth;
    }

    /**
     * class slug builder
     */
    public static class SlugBuilder {

        private String name;
        private double velocity;
        private Boolean livesBelowEarth;

        public SlugBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SlugBuilder withVelocity(double velocity) {
            this.velocity = velocity;
            return this;
        }

        public SlugBuilder withLivesBelowEarth(Boolean livesBelowEarth) {
            this.livesBelowEarth = livesBelowEarth;
            return this;
        }

        public Slug build() {
            if (name == null) {
                this.name = "Nameless slug";
            }
            return new Slug
                    (name, velocity, livesBelowEarth);
        }
    }
}