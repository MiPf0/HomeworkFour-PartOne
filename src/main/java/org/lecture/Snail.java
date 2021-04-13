package org.lecture;

import lombok.Getter;

/**
 * Representation of a crawler: "Snail"
 */

@Getter
public final class Snail extends Crawler {

    private final ShellColour shellColour;

    /**
     * Snail constructor
     * @param name
     * @param velocity
     * @param shellColour
     */
    private Snail(String name, double velocity, ShellColour shellColour) {
        super(name, velocity);
        this.shellColour = shellColour;
    }

    /**
     * class snail builder
     */
    public static class SnailBuilder {

        private String name;
        private double velocity;
        private ShellColour shellColour;

        public SnailBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SnailBuilder withVelocity(double velocity) {
            this.velocity = velocity;
            return this;
        }

        public SnailBuilder withShellColour(ShellColour shellColour) {
            this.shellColour = shellColour;
            return this;
        }

        public Snail build() {
            if (name == null) {
                this.name = "Nameless snail";
            }
            return new Snail
                    (name, velocity, shellColour);
        }
    }
}