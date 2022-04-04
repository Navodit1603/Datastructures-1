import java.util.Random;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;


public class Sorts {

    public static void Create(ArrayList<Integer> data){
        Random random = new Random();
        for(int i = 0; i < 5000; i++){
            int x = random.nextInt(501);
            data.add(x);
        }
        // return data;
    }

    public static void Bubble(ArrayList<Integer> b_data) {
        int switches = 0;
        int comparisons = 0;
        int size = b_data.size();
        final Duration timeElapsed;

        Instant start = Instant.now(); // time capture starts
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (b_data.get(j) > b_data.get(j + 1)) {
                    // swap arr[j+1] and arr[j]
                    int temp = b_data.get(j);
                    b_data.set(j, b_data.get(j + 1));
                    b_data.set(j + 1, temp);
                    switches++;
                }
                comparisons++;
            }
        }
        Instant end = Instant.now();
        timeElapsed = Duration.between(start,end);
        System.out.println("Bubble Sort:");
        //System.out.println(b_data);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Switches: " + switches);
        System.out.println("Time(Nanoseconds): "+timeElapsed);
    }

    public static void Selection(ArrayList<Integer> data){
        int size = data.size();
        int switches = 0;
        int comparisons = 0;
        final Duration timeElapsed;

        Instant start = Instant.now(); // time capture starts
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < size-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < size; j++) {
                if (data.get(j) < data.get(min_idx)) {
                    min_idx = j;
                }
                comparisons++;
            }
            int temp = data.get(min_idx);
            data.set(min_idx, data.get(i));
            data.set(i,temp);
            switches++;
        }
        Instant end = Instant.now();
        timeElapsed = Duration.between(start,end);
        System.out.println("Selection Sort:");
        //System.out.println(data);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Switches: " + switches);
        System.out.println("Time(Nanoseconds): "+timeElapsed);
    }

    public static void Insertion(ArrayList<Integer> data){
        int size = data.size();
        int switches = 0;
        int comparisons = 0;
        final Duration timeElapsed;

        Instant start = Instant.now(); // time capture starts
        for(int i = 1; i < size; i++){
            int current = data.get(i);
            int j = i - 1;
            while(j >= 0 && data.get(j) > current){
                data.set(j+1, data.get(j));
                j--;
                switches++;
                comparisons++;
            }
            comparisons++;
            data.set(j+1, current);
        }
        Instant end = Instant.now();
        timeElapsed = Duration.between(start,end);
        System.out.println("Insertion Sort:");
        //System.out.println(data);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Switches: " + switches);
        System.out.println("Time(Nanoseconds): "+timeElapsed);
    }

    public static void mergeSort(ArrayList<Integer> data,  int comparisons, int switches){
        int size = data.size();


        if (size < 2) {
            return;
        }

        int mid = data.size()/2;
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for(int i = 0; i < mid; i++){
            left.add(data.get(i));
        }

        for(int i = mid; i < size; i++){
            right.add(i - mid, data.get(i));
        }

        mergeSort(left, comparisons, switches);
        mergeSort(right, comparisons, switches);



        Merge(data, left, right, switches, comparisons);
        //System.out.println(data);
    }

    public static void Merge(
            ArrayList<Integer> data, ArrayList<Integer> left,
            ArrayList<Integer> right, int switches, int comparisons){


       int leftSize = left.size();
       int rightSize = right.size();


       int i = 0, j = 0, k = 0;

       while(i<leftSize && j< rightSize){
           comparisons += 2;
           if(left.get(i)<= right.get(j)){
               data.set(k,left.get(i));
               comparisons++;
               switches++;
               i++;
           }
           else{
               data.set(k,right.get(j));
               switches++;
               j++;
           }
           k++;
       }

       while (i< leftSize){
           data.set(k,left.get(i));
           i++;
           k++;

       }
       while(j<rightSize){
           data.set(k,right.get(j));
           j++;
           k++;
       }
    }


    public static void main(String[] args){
        ArrayList<Integer> data = new ArrayList<>();
        Create(data);
        System.out.println(data);
        System.out.println("");

        //bubble sort
        ArrayList<Integer> b_data = new ArrayList<>();
        for(int numbers : data){
            b_data.add(numbers);
        }
        Bubble(b_data);
        System.out.println("");

        //Selection sort
        ArrayList<Integer> s_data = new ArrayList<>();
        for(int numbers : data){
            s_data.add(numbers);
        }
        Selection(s_data);
        System.out.println("");

        //insertion sort
        ArrayList<Integer> i_data = new ArrayList<>();
        for(int numbers : data){
            i_data.add(numbers);
        }
        //System.out.println(i_data);
        Insertion(i_data);
        System.out.println("");

        //merge sort
        ArrayList<Integer> m_data = new ArrayList<>();
        for(int numbers : data){
            m_data.add(numbers);
        }
        final Duration timeElapsed;
        int comparisons =0;
        int switches = 0;
        Instant start = Instant.now(); // time capture starts
        mergeSort(m_data, comparisons, switches);
        Instant end = Instant.now();
        timeElapsed = Duration.between(start,end);
        System.out.println("Merge Sort");
        //System.out.println(comparisons);
        //System.out.println(switches);
        System.out.println("Time(Nanoseconds): "+timeElapsed);

    }

}
