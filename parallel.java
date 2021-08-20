import java.text.DecimalFormat;
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class parallel extends RecursiveTask<double[]> {
    static int SEQUENTIAL_THRESHOLD = 21;
    int lo;
    int hi;
    int filter_size;
    double[] arr;

    /**
     * constructor for the parallel class that performs the parallelization of an input array
     *
     * @param a the array to filtered
     * @param l the specified start position of the array
     * @param h the specified end postion of the array
     * @param f the filter size of filter window
     */

    public parallel(double[] a, int l, int h, int f){
        this.lo = l;
        this.hi = h;
        this.arr = a;
        this.filter_size = f;
    }

    /**
     * computes the filtering process on a thread.
     * Uses a recursive step to subdivide an array into individual threads to satisfy the divide and conquer algorithm
     *
     * @return retruns the resulting filtered array
     */
    @Override
    protected double[] compute() {
        if (hi-lo <= SEQUENTIAL_THRESHOLD){
            double[] y = new double[SEQUENTIAL_THRESHOLD];
            for(int i=lo;i<hi;i++){
                int num_neighbours=(filter_size-1)/2;
                if(i<num_neighbours+lo) {
                    y[i-lo] = this.arr[i];
                }
                else if(i>hi-1-num_neighbours){
                    y[i-lo]=this.arr[i];
                }
                else{
                    double[] window = new double[filter_size];
                    for(int k=0;k<filter_size;k++){
                        window[k]=this.arr[i-(num_neighbours-k)];
                    }
                    Arrays.sort(window);
                    y[i-lo]= window[window.length/2];
                }
            }
            return y;
        }else{
            parallel left = new parallel(arr,lo,(hi+lo)/2,filter_size);
            parallel right = new parallel(arr,(hi+lo)/2,hi,filter_size);
            left.fork();
            double[] rightAns = right.compute();
            double[] leftAns = left.join();
            int left_size = leftAns.length;
            int right_size = rightAns.length;
            double[] result = new double[left_size+right_size];
            System.arraycopy(leftAns,0,result,0,left_size);
            System.arraycopy(rightAns,0,result,left_size,right_size);
        return result;
        }
    }
}
