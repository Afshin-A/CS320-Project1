import data.Appointment;
import data.Contact;
import data.ServiceData;
import data.Task;
import services.AppointmentService;
import services.ContactService;
import services.TaskService;
//import services.Service;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // Using polymorphism, we can refer as a ContactService as a Service object.
        // Service uses a generic T bound by type ServiceData
        // It is possible to add any ServiceData types (Contact, Task, Appointment) to the contactService object below.
        // Should this be allowed?
        // The program wouldn't break.
/*        Service contactService = new ContactService();
        contactService.add("1", new Task("Study","Study French"));
        contactService.add("2", new Contact("Af","Ah","2109476915","Vance Jackson"));
        contactService.remove("1");
        contactService.showDatabase();
//        contactService.update("2", new Appointment(new Date(), "Endodontist appointment"));
        contactService.showDatabase();*/


    }
}
