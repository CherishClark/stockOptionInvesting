public class createEventFactory {
    public Event createEvent(String eventString, String fileDelimiter) throws Exception {
        try {
            return new Event.Builder().build();
        } catch (Exception e) {
            throw new Exception(eventString + "Event could not be created");
        }
    }
}
