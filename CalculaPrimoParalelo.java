package java_files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CalculaPrimoParalelo {
    public static void main(String[] args) {
        long tempoInicial = System.currentTimeMillis();
        int inicio = 1;
        int fim = 1000000;
        int numThreads = 10;
        int chunkSize = (fim - inicio + 1) / numThreads;
        List<Integer> result = new ArrayList<>();

        // Usando ThreadPool para um melhor gerenciamento
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int threadStart = inicio + i * chunkSize;
            int threadEnd = threadStart + chunkSize - 1;
            executor.execute(() -> achaPrimos(threadStart, threadEnd, result));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait
        }

        System.out.println("Prime numbers between " + inicio + " and " + fim + ":");
        System.out.println(result);

        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;
        System.out.println("\nExecution time: " + tempoExecucao + " milliseconds");
    }

    public static void achaPrimos(int start, int end, List<Integer> result) {
        for (int num = start; num <= end; num++) {
            if (ehPrimo(num)) {
                synchronized (result) {
                    result.add(num);
                }
            }
        }
    }

    public static boolean ehPrimo(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}