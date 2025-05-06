package org.example.demo;
import java.util.logging.Level;
import java.util.logging.Logger;

class Philosopher extends Thread {
    private int name;
    private State currentState;
    private Forks forks;
    private boolean wasHungry = false;
    private static int currentName = 0;
    private int cycleCount = 0;
    private static final int MAX_CYCLES = 3;

    private enum State {HUNGRY, EATING, THINKING};

    public Philosopher(Forks forks) {
        this.forks = forks;
        this.currentState = State.HUNGRY;
        this.name = currentName++;
        System.out.println("Filozoful #" + name + " a ajuns la masă!");
    }

    @Override
    public void run() {
        while (cycleCount < MAX_CYCLES) {
            switch (currentState) {
                case HUNGRY:
                    if (!wasHungry) {
                        System.out.println("Filozoful #" + name + " este flămând");
                    }
                    wasHungry = true;
                    if (forks.takeForks(name)) {
                        currentState = State.EATING;
                    }
                    break;
                case EATING:
                    System.out.println("Filozoful #" + name + " mănâncă");
                    try {
                        Thread.sleep(2000 + (int) (Math.random() * 1000)); // Delay 2-3 secunde
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //rqrqwrqwrq
                    forks.leaveForks(name);
                    currentState = State.THINKING;
                    wasHungry = false;
                    break;
                case THINKING:
                    System.out.println("Filozoful #" + name + " gândește");
                    try {
                        Thread.sleep(3000 + (int) (Math.random() * 2000)); // Delay 3-5 secunde
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    cycleCount++;
                    if (cycleCount >= MAX_CYCLES) {
                        System.out.println("Filozoful #" + name + " a terminat ciclurile și părăsește masa.");
                        return;
                    }
                    currentState = State.HUNGRY;
                    break;
            }
        }
    }
}