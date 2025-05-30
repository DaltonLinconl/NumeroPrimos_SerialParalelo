import java.util.concurrent.Semaphore;
public class MecanismoSemaforo2{
    private Semaphore semaforo = new Semaphore(1);
    private int valorCompartilhado = 0;
    public void incrementarValor() throws InterruptedException{
        semaforo.acquire();
        System.out.println(Thread.currentThread().getName()+" - Valor atual: "+ valorCompartilhado);
        valorCompartilhado++;
        System.out.println(Thread.currentThread().getName()+" - Novo atual: "+ valorCompartilhado);
        semaforo.release();
        
    }
    public static void main(String[] args) throws InterruptedException{
        MecanismoSemaforo2 mecanismo = new MecanismoSemaforo2();

        Thread[] threads = new Thread[30];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {try {mecanismo.incrementarValor();} catch (InterruptedException e) {e.printStackTrace();}});
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println("Valor final: "+mecanismo.valorCompartilhado);
    }

}