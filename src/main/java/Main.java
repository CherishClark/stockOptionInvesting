public class Main {

    public static void main(String[] args) {

        EventInfo eventInfo = EventParser.parseEvents(System.in);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        new Outputer(eventProcessor.getEmployeesList(), System.out);
    }

}