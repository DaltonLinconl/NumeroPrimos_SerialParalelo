import java.util.concurrent.atomic.AtomicBoolean;
public class MecanismoEsperaOcupada{
    private AtomicBoolean flagOcupado = new AtomicBoolean(false);
    private int valorCompartilhado = 0;
    public void incrementarValor(){
        while(flagOcupado.get()){

        }
        flagOcupado.set(true);
        System.out.println(Thread.currentThread().getName()+" - Valor atual: "+ valorCompartilhado);
        valorCompartilhado++;
        System.out.println(Thread.currentThread().getName()+" - Novo atual: "+ valorCompartilhado);
        flagOcupado.set(false);
        
    }
    public static void main(String[] args){
        MecanismoEsperaOcupada mecanismo = new MecanismoEsperaOcupada();

        Thread thread1 = new Thread(() -> mecanismo.incrementarValor());
        Thread thread2 = new Thread(() -> mecanismo.incrementarValor());
        Thread thread3 = new Thread(() -> mecanismo.incrementarValor());
        

        thread1.start();
        thread2.start();
        thread3.start();    
    }

}