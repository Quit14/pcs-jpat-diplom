package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodosTests {
    private Todos todos = new Todos();
 @BeforeEach
 void setUp() {
     todos.addTask("Д");
     todos.addTask("Г");
     todos.addTask("А");
 }

    @Test
    @DisplayName("Больше 7 задач в списке")
    void addMoreThanPossible () {
     todos.addTask("К");
     todos.addTask("И");
     todos.addTask("Ж");
     todos.addTask("Л");
     todos.addTask("Е");
     String result = "А Г Д Ж И К Л";
        Assertions.assertEquals(result, todos.getAllTasks());
    }

    @Test
    @DisplayName("Отсутствие повторов задач")
    void checkRepeats () {
     todos.addTask("А");
     String result = "А Г Д";
     Assertions.assertEquals(result, todos.getAllTasks());
    }


    @Test
    @DisplayName("Проверяем удаление")
    void removeTaskTest() {
     todos.removeTask("Д");
     String result = "А Г";
     Assertions.assertEquals(result, todos.getAllTasks());
    }

    @Test
    @DisplayName("Проверяем добавление")
    void addTaskTest() {
     todos.addTask("Б");
     String result = "А Б Г Д";
     Assertions.assertEquals(result, todos.getAllTasks());
    }


}
