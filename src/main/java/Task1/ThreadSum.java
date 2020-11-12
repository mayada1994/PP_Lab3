package Task1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadSum extends Thread {
    private final CustomArray arr;
    private final int leftIndex;
    private final int rightIndex;
    private final CyclicBarrier barrier;

    public ThreadSum(CustomArray arr, int leftIndex, int rightIndex, CyclicBarrier barrier) {
        this.arr = arr;
        this.leftIndex = leftIndex;
        this.rightIndex = rightIndex;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int tmpSum = arr.sumPartOfArray(leftIndex, rightIndex);
        System.out.println("Sum of current chunk with left index " + leftIndex + " and right index " + rightIndex + ": " + tmpSum);
        arr.addSum(tmpSum);
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
