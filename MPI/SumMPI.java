import mpi.*;

public class SumMPI {

    public static void main(String args[]) throws Exception {

        // Initialize MPI
        MPI.Init(args);

        // Get processor rank
        int rank = MPI.COMM_WORLD.Rank();

        // Get total number of processors
        int size = MPI.COMM_WORLD.Size();

        // Array elements
        int N = 8;
        int[] array = {1,2,3,4,5,6,7,8};

        // Elements per processor
        int elementsPerProcess = N / size;

        // Remaining elements
        int remainder = N % size;

        // Starting index
        int start = rank * elementsPerProcess + Math.min(rank, remainder);

        // Ending index
        int end = start + elementsPerProcess;

        // Distribute extra elements
        if (rank < remainder) {
            end += 1;
        }

        // Local sum
        int localSum = 0;

        // Calculate partial sum
        for (int i = start; i < end; i++) {
            localSum += array[i];
        }

        // Display processor-wise output
        for (int i = 0; i < size; i++) {

            if (rank == i) {

                System.out.println("\n--- Processor " + rank + " ---");

                System.out.print("Elements: ");

                for (int j = start; j < end; j++) {
                    System.out.print(array[j] + " ");
                }

                System.out.println();

                System.out.println("Partial Sum: " + localSum);

                System.out.println();

                // Delay for proper display
                Thread.sleep(2000);
            }

            // Synchronize processors
            MPI.COMM_WORLD.Barrier();
        }

        // Array to store final result
        int[] globalSum = new int[1];

        // Reduce operation
        MPI.COMM_WORLD.Reduce(
                new int[]{localSum}, 0,
                globalSum, 0,
                1,
                MPI.INT,
                MPI.SUM,
                0
        );

        // Final result
        if (rank == 0) {

            System.out.println("\n===== FINAL RESULT =====");

            System.out.println("Total Sum = " + globalSum[0]);
        }

        // Finalize MPI
        MPI.Finalize();
    }
}

//export MPJ_HOME=/opt/mpj
//export PATH=$PATH:$MPJ_HOME/bin
//echo $MPJ_HOME
//javac -cp /opt/mpj/lib/mpj.jar ds/MPISum.java
//mpjrun.sh -np 4 -cp . ds.MPISum


// first go in terminal do cd project -> src -> compile -> run 
// for windows :- javac -cp ".;C:\Users\waghm\Downloads\mpj-v0_44\lib\mpj.jar" ArraySumMPI.java
/* for ubuntu :- javac -cp /opt/mpj/lib/mpj.jar ArraySumMPI.java 
 *                mpjrun.sh -np 4 -cp . ArraySumMPI
 *                
 *  
 */
 