import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class InOutService {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int threadsCount;

        System.out.println("please enter the number of threads");
        threadsCount = sc.nextInt();

        threadsCount = (threadsCount == 0) ? 1 : threadsCount;

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsCount);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"))) {


            String line = reader.readLine();
            int number = 0;
            String outputFileName = "src/main/resources/output.txt";

            try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
                String value = "";

                while (line != null) {

                    if (line.equals("")) {
                        value = "";

                    } else {
                        number = Integer.parseInt(line);
                        FactorialCalc service = new FactorialCalc(number);
                        Future<BigInteger> futureTask = executor.submit(service);

                        value = String.valueOf(futureTask.get()) ;

                    }

                    writter.write(value + "\n");
                    line = reader.readLine();
                }

            System.out.println("Done! Get the result!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
                sc.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

