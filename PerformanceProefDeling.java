package test;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;

public class PerformanceProefDeling {

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
        System.out.println("Memory Usage: " + memoryUsage + " bytes");
    }
    private static void performTask() {
        int start = 0;
        int end=268435455;
        int numParts = 5; // Change this to adjust the number of parts

        List<Integer> primes = findPrimesInRange(start, end, numParts);
        System.out.println("Prime numbers from " + start + " to " + end + ":");

    }
    public static List<Integer> Proefdeling (int start, int end, int numParts) {
        List<Integer> primes = new ArrayList<>();

        int rangeSize = (end - start + 1);
        int partSize = rangeSize / numParts;
        int remainingNumbers = rangeSize % numParts;

        for (int i = 0; i < numParts; i++) {
            int partStart = start + i * partSize + Math.min(i, remainingNumbers);
            int partEnd = partStart + partSize - 1 + (i < remainingNumbers ? 1 : 0);

            for (int num = partStart; num <= partEnd; num++) {
                if (isPrime(num)) {
                    primes.add(num);
                }
            }
        }

        return primes;
    }
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
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
