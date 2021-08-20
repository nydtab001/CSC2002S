import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

public class test {
    public test() {
    }

    /**
     * main methods that runs the filtering sequentially and used to compare with the parallelized code
     *
     * @param args data input to the application
     * @throws IOException Exception if the file is not found
     */
    public static void main(String[] args) throws IOException {
        File file = new File("src\\sampleInput100.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int i = 0;
        int size = Integer.parseInt(br.readLine());
        DecimalFormat df = new DecimalFormat("#.#####");
        double[] x = new double[100];

        String st;
        double[] y;
        for(y = new double[100]; (st = br.readLine()) != null; ++i) {
            st = st.split(" ")[1];
            st = st.replace(',', '.');
            x[i] = Double.parseDouble(df.format(Double.parseDouble(st)));
        }
        long start = System.currentTimeMillis(); // start of the filtration timing
        int filter_size = 7;
        for(int j = 0; j < size; ++j) {
            int num_neighbours = (filter_size - 1) / 2;
            if (j < num_neighbours) {
                y[j] = x[j];
            } else if (j > size - 1 - num_neighbours) {
                y[j] = x[j];
            } else {
                double[] window = new double[filter_size];

                for(int k = 0; k < filter_size; ++k) {
                    window[k] = x[j - (num_neighbours - k)];
                }

                Arrays.sort(window);
                y[j] = window[window.length / 2];
            }
        }
        long finish = System.currentTimeMillis(); // end of the filteration timing
        System.out.println("sequential program");
        System.out.println("filter size: "+filter_size);
        System.out.println("time taken(ms): "+(finish-start)); // prints time difference to give the overall time taken
      //  System.out.println(Arrays.toString(y));
    }
}

