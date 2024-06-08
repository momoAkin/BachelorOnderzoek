package test;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.MemoryMXBean;

public class PerformanceFibonaci {

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
        // Fibonaci
        int n = 51;
        long result = fibonacci(n);
        System.out.println("Fibonacci(" + n + ") = " + result);
    }
    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
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
