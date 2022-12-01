package services;

import data.ServiceData;
import validations.Validations;

import java.util.HashMap;
import java.util.Map;

/**
 * Service has default access modifier. Clients cannot instantiate an Object of this type using polymorphism.
 * Clients can only instantiate its subclasses.
 * @param <T> We must ensure T can only be one of the following types: Contact, Task, and Appointment.
 */
abstract class Service <T extends ServiceData> {

    /**
     * Database
     */
    protected Map<String, T> database = new HashMap<>();

    /**
     * Ensures id meets its requirements
     * @param id
     */
    protected void validateID(String id) {
        Validations.validateObjectNotNull(id);
        Validations.validateStringLengthIsInRange(id, 10);
    }

    /**
     * Adds a ServiceData object to the database and assigns it an id, only if id doesn't already exist in database
     * @param id
     * @param data
     */
    public void add(String id, T data) {
        validateID(id);
        Validations.validateObjectNotNull(data);
        database.putIfAbsent(id, data);
    }

    /**
     * Removes and returns a ServiceData object from the database using an id. If id does not exist in database, method does nothing.
     * @param id
     * @return
     */
    public T remove(String id) {
        validateID(id);
        return database.remove(id);
    }

    /**
     * Finds and updates a ServiceData object using an id. If id doesn't exist in database, method does nothing.
     * Note: update() should not be available in all subclasses (such as AppointmentService). It should have private access.
     * Subclasses in need of this feature should override this method.
     * @param id
     * @param data
     */
    protected void update(String id, T data) {
        validateID(id);
        Validations.validateObjectNotNull(data);
        T temp = database.get(id);
        if (temp != null) {
            database.put(id, data);
        }
    }


    // FIXME: 12/1/22 Remove this method before publishing final build
    public void showDatabase() {
        System.out.println(database);
    }
}
