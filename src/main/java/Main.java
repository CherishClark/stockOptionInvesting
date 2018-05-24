public class Main {

    public static void main(String[] args) {

        EventInfo eventInfo = EventParser.parseEvents(System.in);

        new Outputer(new EventProcessor(eventInfo), System.out);

    }

}