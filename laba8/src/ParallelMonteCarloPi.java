import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelMonteCarloPi {

    private static final long ITERATIONS = 1_000_000_000L;
    private static final long SEED = 12345L;

    public static void main(String[] args) {


        if (args.length != 1) {
            System.out.println("Usage: java ParallelMonteCarloPi <threads>");
            return;
        }

        int threads;

        try {
            threads = Integer.parseInt(args[0]);

            if (threads <= 0) {
                System.out.println("Number of threads must be greater than 0.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Thread count must be an integer.");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        long startTime = System.nanoTime();

        long iterationsPerThread = ITERATIONS / threads;
        long remainder = ITERATIONS % threads;

        List<Future<Long>> results = new ArrayList<>();

        for (int i = 0; i < threads; i++) {

            long currentIterations = iterationsPerThread;

            if (i < remainder) {
                currentIterations++;
            }

            final long iterations = currentIterations;
            final int threadNumber = i;

            Callable<Long> task = () -> {

                Random random = new Random(SEED + threadNumber);

                long insideCircle = 0;

                for (long j = 0; j < iterations; j++) {

                    double x = random.nextDouble();
                    double y = random.nextDouble();

                    if (x * x + y * y <= 1.0) {
                        insideCircle++;
                    }
                }

                return insideCircle;
            };

            results.add(executor.submit(task));
        }

        long totalInsideCircle = 0;

        try {
            for (Future<Long> result : results) {
                totalInsideCircle += result.get();
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        double pi = 4.0 * totalInsideCircle / ITERATIONS;

        long endTime = System.nanoTime();

        double timeMs = (endTime - startTime) / 1_000_000.0;

        System.out.printf("PI is %.5f%n", pi);
        System.out.println("THREADS " + threads);
        System.out.printf("ITERATIONS %,d%n", ITERATIONS);
        System.out.printf("TIME %.2fms%n", timeMs);
    }
}