package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;


public class TodoServer {
    private final int PORT;
    private final Todos todos;
    private final Gson gson = new Gson();
    private Deque<Operation> operationsList = new ArrayDeque<>();

    public TodoServer(int port, Todos todos) {
        this.PORT = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Starting server at " + PORT + "...");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    String input = in.readLine();
                    Operation operation = gson.fromJson(input, Operation.class);
                    if (operation.type != OperationType.RESTORE) {
                        operationsList.add(operation);
                    } else {
                        operation = operationsList.pollLast();
                        if (operation.type == OperationType.ADD) {
                            operation.type = OperationType.REMOVE;
                        } else {
                            operation.type = OperationType.ADD;
                        }
                    }
                    if (operation.type == OperationType.ADD) {
                        todos.addTask(operation.task);
                    }
                    if (operation.type == OperationType.REMOVE) {
                        todos.removeTask(operation.task);
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private class Operation {
        @SerializedName("type")
        OperationType type;
        String task;

    }
}
