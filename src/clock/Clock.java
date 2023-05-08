package clock;

/**
 * Clock class is the main entry point for the Clock application.
 * It creates the Model, View, and Controller instances, and sets up their relationships.
 *
 * @author Lee Devine
 */
public class Clock {
    
    /**
     * The main method of the class application
     * 
     * @param args
     */
    public static void main(String[] args) {
        createMVC();
    }
    
    /**
     * Creates the Model, View and Controller instances
     * and sets their relationships
     * 1. The View will observe changes in the Model.
     * 2. The View will interact with the Controller.
     */
    public static void createMVC(){
        //Create the Model, View, Controller instances
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        //Establish the relationships between Model, View and Controller.
        model.addObserver(view); //The View will observer changes in the Model
        view.setController(controller); //The View will interact with the Controller.
    }
}
