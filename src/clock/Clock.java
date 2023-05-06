package clock;

/**
 * Clock class is the main entry point for the Clock application
 * @author Lee Devine
 */
public class Clock {
    
    /**
     * The main method of the class application
     * @param args
     */
    public static void main(String[] args) {
        //Create the Model, View, Controller instances
        Model model = new Model();
        View view = new View(model);
        //Add the View as an observer to the Model
        model.addObserver(view);
        //Create the Controller instance, passing Model and View instances
        Controller controller = new Controller(model, view);
    }
}
