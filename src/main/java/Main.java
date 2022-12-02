import data.Appointment;
import data.Contact;
import data.ServiceData;
import data.Task;
import services.AppointmentService;
import services.ContactService;
import services.TaskService;



public class Main {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();
        taskService.showDatabase();
    }
}
