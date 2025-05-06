package org.example.demo;

public class Main {
    public static void main(String[] args) {
        int number = 8; // Numărul grupei plus șase
        Forks forks = new Forks(number);
        Philosopher[] philosophers = new Philosopher[number];

        for (int i = 0; i < number; i++) {
            philosophers[i] = new Philosopher(forks);
        }

        for (int i = 0; i < number; i++) {
            philosophers[i].start();
        }
    }
}