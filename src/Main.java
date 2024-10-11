public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Задача 1", "Описание 1", 0, TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Описание 2", 0, TaskStatus.NEW);

        taskManager.createTask(task1);
        taskManager.createTask(task2);

        Epic epic1 = new Epic("Эпик 1", "Описание 1", 0, TaskStatus.NEW);
        taskManager.createEpic(epic1);
        SubTask subTask1 = new SubTask("Подзадача 1", "Описание 1", 0, TaskStatus.NEW,
                epic1.getId());
        SubTask subTask2 = new SubTask("Подзадача 2", "Описание 2", 0, TaskStatus.NEW,
                epic1.getId());

        taskManager.createSubTask(subTask1);
        taskManager.createSubTask(subTask2);

        Epic epic2 = new Epic("Эпик 2", "Описание 2", 0, TaskStatus.NEW);
        taskManager.createEpic(epic2);
        SubTask subTask3 = new SubTask("Подзадача 3", "Описание 3", 0, TaskStatus.NEW,
                epic2.getId());

        taskManager.createSubTask(subTask3);

        System.out.println("Получим эпик: " + epic1 + " Его подзадачи: " +
                taskManager.getSubTasksByEpicId(epic1.getId()));
        System.out.println();


        System.out.println("Cписок задач: " + taskManager.getAllTasks());
        System.out.println("Cписок эпиков: " + taskManager.getAllEpics());
        System.out.println("Cписок подзадач: " + taskManager.getAllSubTasks());
        System.out.println();
        System.out.println("Меняем статусы.");
        System.out.println();

        task1.setStatus(TaskStatus.IN_PROGRESS);
        task2.setStatus(TaskStatus.DONE);

        taskManager.updateTask(task1);
        taskManager.updateTask(task2);

        subTask1.setStatus(TaskStatus.DONE);
        taskManager.updateSubTask(subTask1);

        epic1.setStatus(TaskStatus.DONE);
        taskManager.updateEpic(epic1);

        subTask3.setStatus(TaskStatus.DONE);
        taskManager.updateSubTask(subTask3);

        System.out.println("Cписок задач: " + taskManager.getAllTasks());
        System.out.println("Cписок эпиков: " + taskManager.getAllEpics());
        System.out.println("Cписок подзадач: " + taskManager.getAllSubTasks());
        System.out.println();
        System.out.println("Удаляем один эпик и одну задачу.");
        System.out.println();

        taskManager.removeTaskById(task1.getId());
        taskManager.removeEpicById(epic1.getId());

        System.out.println("Cписок задач: " + taskManager.getAllTasks());
        System.out.println("Cписок эпиков: " + taskManager.getAllEpics());
        System.out.println("Cписок подзадач: " + taskManager.getAllSubTasks());



    }
}
