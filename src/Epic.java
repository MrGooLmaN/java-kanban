import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTaskIds;


    public Epic(String name, String description, int id, TaskStatus status) {
        super(name, description, id, status);
        subTaskIds = new ArrayList<>();
    }

    public List<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void addSubTaskId(int subTaskId){
        subTaskIds.add(subTaskId);
    }

    @Override
    public String toString() {
        return "Epic{" + super.toString() +
                "subTaskIds=" + subTaskIds +
                '}';
    }
}
