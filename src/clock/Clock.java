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
        createMVC();
    }
    
    public static void createMVC(){
        //Create the Model, View, Controller instances
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        model.addObserver(view);
        view.setController(controller);
    }
}
