package ru.yandex;

import manager.InMemoryTaskManager;
import manager.TaskManager;
import manager.Managers;
import status.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.util.List;
import java.util.Map;


public class Main {

    static TaskManager taskManager = Managers.getInMemoryTaskManager(Managers.getDefaultHistory());
    static final Map<Integer, Task> newTasks = taskManager.getTasks();
    static final Map<Integer, Epic> newEpics = taskManager.getEpics();
    static final Map<Integer, Subtask> newSubtasks = taskManager.getSubtasks();


    public static void main(String[] args) {

        System.out.println("ИСТОРИЯ");
        System.out.println("Создание");
        taskManager.createTask(new Task("Описание-1", "Task-1", Status.NEW));
        taskManager.createTask(new Task("Описание-2", "Task-2", Status.NEW));
        taskManager.createEpic(new Epic("Описание-1", "Epic-1", Status.NEW));
        taskManager.createEpic(new Epic("Описание-2", "Epic-2", Status.NEW));
        taskManager.createSubtask(new Subtask("Описание-1", "Subtask-1", Status.NEW, 3));
        taskManager.createSubtask(new Subtask("Описание-2", "Subtask-2", Status.NEW, 3));
        taskManager.createSubtask(new Subtask("Описание-3", "Subtask-3", Status.NEW, 4));
        taskManager.createSubtask(new Subtask("Описание-4", "Subtask-4", Status.NEW, 4));

        System.out.println("Получить по id");
        taskManager.getTaskById(1);
        taskManager.getEpicById(3);
        taskManager.getTaskById(2);
        taskManager.getSubtaskById(5);
        taskManager.getSubtaskById(6);
        taskManager.getEpicById(4);
        taskManager.getSubtaskById(5);
        taskManager.getSubtaskById(6);
        taskManager.getSubtaskById(7);
        taskManager.getSubtaskById(8);
        taskManager.getSubtaskById(7);
        taskManager.getTaskById(2);


        System.out.println("Получить историю");
        List<Task> history = taskManager.getHistory();
        System.out.println(history);
    }

        public static void printTasks () {
            if (newTasks.size() == 0) {
                System.out.println("Список задач пуст");
                return;
            }
            for (Task task : newTasks.values()) {
                System.out.println("Task{" +
                        "description='" + task.getDescription() + '\'' +
                        ", id=" + task.getId() +
                        ", name='" + task.getName() + '\'' +
                        ", status=" + task.getStatus() +
                        '}');
            }
        }

        public static void printEpics () {
            if (newEpics.size() == 0) {
                System.out.println("Список эпиков пуст");
                return;
            }
            for (Epic epic : newEpics.values()) {
                System.out.println("Epic{" +
                        "subtasksIds=" + epic.getSubtaskIds() +
                        ", description='" + epic.getDescription() + '\'' +
                        ", id=" + epic.getId() +
                        ", name='" + epic.getName() + '\'' +
                        ", status=" + epic.getStatus() +
                        '}');
            }
        }

        public static void printSubtasks () {
            if (newSubtasks.size() == 0) {
                System.out.println("Список подзадач пуст");
                return;
            }
            for (Subtask subtask : newSubtasks.values()) {
                System.out.println("Subtask{" +
                        "epicId=" + subtask.getEpicId() +
                        ", description='" + subtask.getDescription() + '\'' +
                        ", id=" + subtask.getId() +
                        ", name='" + subtask.getName() + '\'' +
                        ", status=" + subtask.getStatus() +
                        '}');
            }
        }

}

        /*System.out.println("ЗАДАЧА");
        System.out.println("Создать задачу");
        manager.createTask(new Task("Описание-1", "Task-1", Status.NEW));
        manager.createTask(new Task("Описание-2", "Task-2", Status.NEW));
        printTasks();
        System.out.println("Получить все задачи");
        List<Task> taskList = manager.getAllTasks();
        System.out.println(taskList);
        System.out.println("Получить задачу по id");
        Task task = manager.getTaskById(1);
        System.out.println(task);
        System.out.println("Обновить задачу");
        task.setStatus(Status.IN_PROGRESS);
        manager.updateTask(task);
        System.out.println(task);
        System.out.println();

        System.out.println("ЭПИК");
        System.out.println("Создать эпик");
        manager.createEpic(new Epic("Описание-1", "Epic-1", Status.NEW));
        manager.createEpic(new Epic("Описание-2", "Epic-2", Status.NEW));
        printEpics();
        System.out.println("Получить все эпики");
        List<Epic> epics = manager.getAllEpics();
        System.out.println(epics);
        System.out.println("Получить эпик по id");
        Epic epic = manager.getEpicById(3);
        System.out.println(epic);
        System.out.println("Обновить эпик");
        epic.setStatus(Status.IN_PROGRESS);
        manager.updateEpic(epic);
        Epic epic3 = manager.getEpicById(3);
        System.out.println(epic3);
        System.out.println();

        System.out.println("ПОДЗАДАЧА");
        System.out.println("Создать подзадачу");
        manager.createSubtask(new Subtask("Описание-1", "Subtask-1", Status.NEW, 3));
        manager.createSubtask(new Subtask("Описание-2", "Subtask-2", Status.NEW, 3));
        manager.createSubtask(new Subtask("Описание-3", "Subtask-3", Status.NEW, 4));
        printSubtasks();
        System.out.println("Получить все подзадачи по эпик id");
        List<Subtask> subtasksByEpicId = manager.getAllSubtasksByEpicId(3);
        System.out.println(subtasksByEpicId);
        System.out.println("Получить все подзадачи");
        List<Subtask> subtasks = manager.getAllSubtasks();
        System.out.println(subtasks);
        System.out.println("Получить подзадачу по id");
        Subtask subtask = manager.getSubtaskById(5);
        System.out.println(subtask);
        System.out.println("Обновить подзадачи");
        subtask.setStatus(Status.IN_PROGRESS);
        manager.updateSubtask(subtask);
        System.out.println(subtask);
        System.out.println();

        System.out.println("УДАЛЕНИЕ");
        System.out.println("Удалить задачу по id");
        manager.deleteTaskById(1);
        System.out.println(taskList);
        System.out.println("Удаление всех задач");
        manager.deleteAllTasks();
        printTasks();
        System.out.println("Удалить подзадачу по id");
        manager.deleteSubtaskById(5);
        printSubtasks();
        System.out.println("Удалить все подзадачи");
        manager.deleteAllSubtasks();
        printSubtasks();
        System.out.println("Удалить эпик по id");
        manager.deleteEpicById(4);
        printEpics();
        System.out.println("Удалить все эпики");
        manager.deleteAllEpics();
        printEpics();
    }*/