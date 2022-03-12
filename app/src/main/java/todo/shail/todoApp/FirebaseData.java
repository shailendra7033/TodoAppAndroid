package todo.shail.todoApp;

public class FirebaseData {

    String task;

    public FirebaseData( String task) {
//        this.title = title;
        this.task = task;
    }

    public FirebaseData() {
    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
