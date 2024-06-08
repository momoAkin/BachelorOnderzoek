package test;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.MemoryMXBean;

public class PerformanceSieveOfEratosthenes {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        // Perform a computation-intensive task
        performTask();

        long endTime = System.nanoTime();

        // Measure elapsed time
        long elapsedTime = endTime - startTime;

        // Measure CPU time
        long cpuTime = getCpuTime();

        // Measure memory usage
        long memoryUsage = getMemoryUsage();
        System.out.println("");
        System.out.println("Elapsed Time: " + elapsedTime + " nanoseconds");
        System.out.println("CPU Time: " + cpuTime + " nanoseconds");
        System.out.println("Memry Usage: " + memoryUsage + " bytes");
    }
    private static void performTask() {
        // SieveOfEratosthenes
        int n=134217727;
        System.out.println(n);

        // Initialize the boolean array for prime number marking
        boolean[] prime = new boolean[ (n + 1)];
        // Assume all numbers are prime initially
        for (int i = 0; i <= n; i++)
            prime[i] = true;

        // Perform the Sieve of Eratosthenes
        for (int p = 2; p*p>0 && p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    if(i<0) {
                        break ;
                    }
                    prime[i] = false;
                }
            }
        }

        // Printing all prime numbers
        //for (int i = 2; i <= n; i++)
        //    if (prime[i]) {
        //        System.out.print(i + " ");
        //    }
    }
    private static long getCpuTime() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        if (osBean instanceof com.sun.management.OperatingSystemMXBean) {
            return ((com.sun.management.OperatingSystemMXBean) osBean).getProcessCpuTime();
        } else {
            return 0L;
        }
    }

    private static long getMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        return memoryBean.getHeapMemoryUsage().getUsed();
    }

}
