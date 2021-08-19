import java.text.DecimalFormat;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Taboka\\Desktop\\Course\\CSC2002S\\Ass1\\src\\"+args[0]);
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
        long start = System.currentTimeMillis();
        y = ForkJoinPool.commonPool().invoke(new parallel(x, 0, x.length,Integer.parseInt(args[1])));
        long finish = System.currentTimeMillis();
        System.out.println("sequential program");
        System.out.println("filter size: "+args[1]);
        System.out.println("time taken(ms): "+(finish-start));
        PrintWriter fw = new PrintWriter(args[2]);
        fw.println(y.length);
        for(int j=0;j<y.length;j++){
            String temp = String.valueOf(y[j]);
            temp = temp.replace('.',',');
            temp = j+" "+temp;
            fw.println(temp);
        }
        fw.close();
    }
}
