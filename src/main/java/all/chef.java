package all;

import java.util.ArrayList;
import java.util.List;

public class chef extends Person {

    private String expertise;
    private List<String> assignedTasks = new ArrayList<>();
    private List<String> notifications = new ArrayList<>();

    public chef(String userName, String expertise, String pass, String role) {
        super(userName, pass, role);
        this.expertise = expertise;
    }

    public String getExpertise() {
        return expertise;
    }

    public List<String> getAssignedTasks() {
        return assignedTasks;
    }

    public void assignTask(String task) {
        assignedTasks.add(task);
        String message = "Task assigned: " + task;
        notifications.add(message);
        System.out.println("🔔 " + message + " to " + userName);
    }

    public int getTaskCount() {
        return assignedTasks.size();
    }

    public boolean isValid() {
        return userName != null && expertise != null;
    }

    public List<String> getNotifications() {
        return notifications;
    }
}