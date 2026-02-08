import java.util.ArrayList;

public class ArraysArrayList {
     
        public static void main(String[] args){
            // Create an array of integers
            int[] numbers = {1, 2, 3, 4, 5};
           System.out.println("Length of the array: " + numbers.length);

            for (int i = 0; i < numbers.length; i++) {
                System.out.println("index " + i + ":  " + numbers[i]);
            }

            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            // list.add("string); // This will cause a compile-time error because the list is of type Integer
            list.add(3);
            list.add(4);
            list.add(5);

            System.out.println("Size of the ArrayList: " + list.size());
            
            for (int i = 0; i < list.size(); i++) {
                System.out.println("index " + i + ":  " + list.get(i));
            }

            // while loop
            int index = 0;
            while (index < list.size()) {
                System.out.println("index " + index + ":  " + list.get(index));
                index++;
            }


            ArrayList<String> names = new ArrayList<>();
            names.add("Alice");
            names.add("Bob");
            names.add(123); // This will cause a compile-time error because the list is of type String
            names.add("Charlie");

            for (String x : names) {
                System.out.println(x);
            }
        }

}

