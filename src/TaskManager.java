import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private Map<Integer, Task> tasks;
    private Map<Integer, SubTask> subTasks;
    private Map<Integer, Epic> epics;
    private int nextId;

    TaskManager() {
        tasks = new HashMap<>();
        subTasks = new HashMap<>();
        epics = new HashMap<>();

        nextId = 1;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<SubTask> getAllSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void clearSubTasks() {
        subTasks.clear();
    }

    public void clearEpics() {
        epics.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public int createTask(Task task) {
        task.setId(nextId);
        nextId++;
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public int createEpic(Epic epic) {
        epic.setId(nextId);
        nextId++;
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
        return epic.getId();
    }

    public int createSubTask(SubTask subTask) {
        subTask.setId(nextId);
        nextId++;
        subTasks.put(subTask.getId(), subTask);

        Epic epic = epics.get(subTask.getEpicId());
        epic.addSubTaskId(subTask.getId());
        updateEpicStatus(epic);

        return subTask.getId();
    }

    public int updateTask(Task task){
        if(!tasks.containsKey(task.getId())){
            System.out.println("Задачи по этому идентификатору нет.");
            return 0;
        }
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public int updateEpic(Epic epic){
        if(!epics.containsKey(epic.getId())){
            System.out.println("Эпика по этому идентификатору нет.");
            return 0;
        }
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
        return epic.getId();
    }

    public int updateSubTask(SubTask subTask){
        if(!subTasks.containsKey(subTask.getId())){
            System.out.println("Подзадачи по этому идентификатору нет.");
            return 0;
        }
        subTasks.put(subTask.getId(), subTask);
        updateEpicStatus(epics.get(subTask.getEpicId()));
        return subTask.getId();
    }

    public void removeTaskById(int id){
        if(!tasks.containsKey(id)){
            System.out.println("Задачи по этому идентификатору нет.");
            return;
        }
        tasks.remove(id);
    }

    public void removeEpicById(int id){
        if(!epics.containsKey(id)){
            System.out.println("Эпика по этому идентификатору нет.");
            return;
        }
        Epic epic = epics.get(id);
        for(Integer subTaskId : epic.getSubTaskIds()){
            subTasks.remove(subTaskId);
        }
        epics.remove(id);
    }

    public void removeSubTaskById(int id){
        if(!subTasks.containsKey(id)){
            System.out.println("Подзадачи по этому идентификатору нет.");
            return;
        }
        SubTask subTask = subTasks.get(id);
        Epic epic = epics.get(subTask.getEpicId());
        epic.getSubTaskIds().remove(subTask.getId());
        subTasks.remove(id);
        updateEpicStatus(epic);
    }

    public List<SubTask> getSubTasksByEpicId(int epicId){
        List<SubTask> subTasksByEpic = new ArrayList<>();
        for(Integer subTaskId : epics.get(epicId).getSubTaskIds()){
            subTasksByEpic.add(subTasks.get(subTaskId));
        }
        return subTasksByEpic;
    }




    private void updateEpicStatus(Epic epic) {
        if (epic.getSubTaskIds().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        boolean isNew = true;
        boolean isDone = true;

        for (Integer subTaskId : epic.getSubTaskIds()) {
            SubTask subTask = subTasks.get(subTaskId);
            subTask.setEpicId(epic.getId());
            if (subTask.getStatus() != TaskStatus.NEW) {
                isNew = false;
            }
            if (subTask.getStatus() != TaskStatus.DONE) {
                isDone = false;
            }
        }

        if (isNew) {
            epic.setStatus(TaskStatus.NEW);
        } else if (isDone) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }




}
