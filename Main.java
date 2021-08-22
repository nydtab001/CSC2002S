import java.text.DecimalFormat;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class Main {

    /**
     * main method used to fetch data from a file and inserts this data into an array which is to be used for the filtering process
     *
     * @param args data input to the application
     * @throws Exception Exception if the file is not found
     */

    public static void main(String[] args) throws Exception {
        try {
            File file = new File("C:\\Users\\Taboka\\Desktop\\Course\\CSC2002S\\Ass1\\src\\" + args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int i = 0;
            int size = Integer.parseInt(br.readLine());
            double[] x = new double[size];
            double[] y;
            DecimalFormat df = new DecimalFormat("#.#####");
            while ((st = br.readLine()) != null) {
                st = st.split(" ")[1];
                st = st.replace(',', '.');
                x[i] = Double.parseDouble(df.format(Double.parseDouble(st)));
                i = i + 1;
            }
            long start = System.currentTimeMillis(); //start of the timing of the thread
            y = ForkJoinPool.commonPool().invoke(new parallel(x, 0, x.length, Integer.parseInt(args[1])));
            long finish = System.currentTimeMillis(); //end of the timing of the thread
            System.out.println("parallel program");
            System.out.println("filter size: " + args[1]);
            System.out.println("time taken(ms): " + (finish - start)); //prints the time difference
            PrintWriter fw = new PrintWriter(args[2]);  // initializes the file to be written to from the application input
            fw.println(y.length); // writes the length of the array or the subsequent lines of the file
            for (int j = 0; j < y.length; j++) {
                String temp = String.valueOf(y[j]);
                temp = temp.replace('.', ',');
                temp = j + " " + temp;
                fw.println(temp); // writes each array element on a new line
            }
            fw.close(); // closes the file writer
        } catch (Exception e) {
            System.out.println("File "+args[0]+" not found");
        }
    }
}
