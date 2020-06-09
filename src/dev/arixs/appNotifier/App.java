package dev.arixs.appNotifier;

public class App {

    private String name;
    private String task;
    private int id;

    public App(String name, String task, int id) {
        this.name = name;
        this.task = task;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getTask() {
        return this.task;
    }

    public int getId() {
        return this.id;
    }

}