package ru.netology.javacore;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    protected Set<String> tasks = new HashSet<>();
    protected final int TASK_LIMIT = 7;


    public void addTask(String task) {
        if (tasks.size() < TASK_LIMIT) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }


    public String getAllTasks() {
        return tasks
                .stream()
                .sorted()
                .collect(Collectors.toList())
                .toString()
                .replaceAll("(?U)[^\\p{L}\\p{N}\\s]+", "");
    }

}
