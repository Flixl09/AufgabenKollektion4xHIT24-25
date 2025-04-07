import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ListLogger implements Logger {
    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    @Override
    public void log(String message) {
        while (true) {
            try {
                messages.put(message);
                break;
            } catch (InterruptedException _) {}
        }
    }

    public List<String> getMessages() {
        return messages.stream().toList();
    }

    public void printMessages() {
        messages.forEach(System.out::println);
    }
}
