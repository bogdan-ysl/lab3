package org.example.demo;

public class Forks {
    private final boolean[] forks; // true dacă furculița este ocupată, false dacă este liberă
    private final int number; // numărul total de furculițe (și filozofi)

    public Forks(int number) {
        this.number = number;
        this.forks = new boolean[number]; // inițial toate furculițele sunt libere (false)
        System.out.println("Furculițele au fost create!");
    }

    public synchronized boolean takeForks(int philosopherId) {
        int leftFork = philosopherId; // furculița din stânga
        int rightFork = (philosopherId + 1) % number; // furculița din dreapta

        // Verificăm dacă ambele furculițe sunt libere
        if (!forks[leftFork] && !forks[rightFork]) {
            forks[leftFork] = true; // ocupăm furculița din stânga
            forks[rightFork] = true; // ocupăm furculița din dreapta
            System.out.println("Filozoful #" + philosopherId + " a luat furculițele.");
            return true; // filozoful poate mânca
        }
        return false; // furculițele nu sunt disponibile
    }

    public synchronized void leaveForks(int philosopherId) {
        int leftFork = philosopherId; // furculița din stânga
        int rightFork = (philosopherId + 1) % number; // furculița din dreapta

        forks[leftFork] = false; // eliberăm furculița din stânga
        forks[rightFork] = false; // eliberăm furculița din dreapta
        System.out.println("Filozoful #" + philosopherId + " a eliberat furculițele.");
        notifyAll(); // notificăm ceilalți filozofi care așteaptă
    }
}