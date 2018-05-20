public class Main {

    public static void main(String[] args) {

//        Parser.parseInput(System.in, System.out);

        EventInfo eventInfo = EventParser.parseEvents(System.in);

        new Outpoots(new EventProcessor(eventInfo), System.out);

//        I will have something that i slike an ecent processor here it will do the following:
//        1. iterate through all of the events
//        if the event has an employeeId for an employee that does not exist yet,
//        it will create a new employee with a new employee record
//        2. Will iterate through each event, depending on the type of event, will calculate the profit for that event.
//        3. Will deterimine profit for an employee by adding all profits from its events
//        4. will return an object that contains all employees with their total profits, this will be passed into the Event Loader
//

//        The event loader will do the following
//        Iterate through the object from the processor and print it according to spec



    }

}