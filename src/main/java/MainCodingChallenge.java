import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MainCodingChallenge {

    static List<User> users = Arrays.asList(
            new User("John", "Martin", 40),
            new User("Gregor", "Rheis", 34),
            new User("Mary", "Keit", 35)
    );

    static String nameForOptional = "test";

    public static void main(String[] args) throws Exception {
        MainCodingChallenge mainCodingChallenge = new MainCodingChallenge();
        mainCodingChallenge.startTest();
    }

    public void startTest() {
        useOptional(nameForOptional);
        useOptional(null);
        useStream(users);
        useStream(null);
        System.out.println(useCompletableFuture(5, 6));
    }

    public Integer useOptional(String value) {
        return Optional.ofNullable(value).map(String::length).orElse(-1);

//        /* TODO: Create an Optional and assign the 'value' param passed to the function.
//         * Return the length of 'value'. In case 'value' is null return '-1' as default.
//         */
//        return -1;
    }

    public List<String> useStream(List<User> users) {
        return Optional.ofNullable(users).orElse(List.of()).stream().filter(u -> u.getAge() >= 40).map(User::getName).collect(Collectors.toList());

        /* TODO: Filter the 'this.users' array for users under the age of 40.
         * Return a List of the remaining users names.
         */
//        return null;
    }

    public Integer useCompletableFuture(int a, int b) {
        ExecutorService es = Executors.newFixedThreadPool(5);

        Supplier<Integer> task = () -> addFun1(a, b);
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(task, es);

        try {
            return cf.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            es.shutdown();
        }
        /* TODO: Create an Executor with a fixed pool of 5 threads.
         * Create a CompletableFuture and use it to pass 'this.addFun1(...)' to the Executor.
         * Wait until the response is received and return it.
         */
//        return -1;
    }

    public Integer addFun1(int a, int b) {
        System.out.println(Instant.now() + " -" + Thread.currentThread().getName() + " BEFORE addFun1 with thread sleep 2,5 sec");
        try {
            Thread.sleep(2500);
        } catch (InterruptedException interruptedException) {
            System.out.println("interruptedException.printStackTrace()");
            interruptedException.printStackTrace();
        }
        System.out.println(Instant.now() + " -" + Thread.currentThread().getName() + " AFTER addFun1 with thread sleep 2,5 sec");
        return a + b;
    }
}
