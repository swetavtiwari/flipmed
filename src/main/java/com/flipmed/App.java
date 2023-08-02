package com.flipmed;

import com.flipmed.service.Driver;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.start();
    }
}
