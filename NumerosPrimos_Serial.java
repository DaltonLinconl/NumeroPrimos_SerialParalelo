package java_files;
import java.util.Arrays;

public class NumerosPrimos_Serial {

    public static void main(String[] args) {
        
        long tempoInicial = System.currentTimeMillis();

        int inicio = 1;
        int fim = 1000000;


        int[] primos = new int[fim - inicio +1];
        int indecePrimo = 0;

        for(int i = inicio; i <= fim; i++){
            boolean ehPrimo = true;

            for(int j= 2; j<= Math.sqrt(i); j++){
                if (i % j == 0) {
                    ehPrimo = false;
                    break;
                }
                
            }
            if(ehPrimo){
                primos[indecePrimo++] = i;
            }
        }
        System.out.println("Números primos entre "+inicio+" e "+ fim + ":");
        System.out.println("\n[");
        for(int primo : Arrays.copyOfRange(primos, 0, indecePrimo)){
            System.out.print(primo + ", ");
        }
        System.out.println("]");

        long tempoFinal = System.currentTimeMillis();
        long tempoExecucao = tempoFinal - tempoInicial;
        System.out.println("\n");
        System.out.println("Tempo de Execução: "+ tempoExecucao + " milisegundos");

        
    }
}