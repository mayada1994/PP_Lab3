package Task2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CustomArray {
    public int[] array;
    public int currentSize;

    public void createArray(int size) {
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        currentSize = array.length;
    }

    public void printArray() {
        for (int j = 0; j < currentSize; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println("");
    }

    public int sumPair(int leftIndex, int rightIndex) {
        return array[leftIndex] + array[rightIndex];
    }

    public synchronized void addSum(int sum, int index) {
        array[index] = sum;
        notify();
    }

    public void sumArray() {
        int currentElement = this.currentSize - 1;
        while (currentSize > 1) {
            CyclicBarrier barrier = new CyclicBarrier(currentSize / 2 + 1, new Runnable() {
                @Override
                public void run() {
                    System.out.print("Array after change : ");
                    printArray();
                    System.out.println("");
                }
            });
            for (int i = 0; i < currentSize / 2; i++) {
                ThreadSum thread = new ThreadSum(this, i, currentElement, barrier);
                thread.start();
                currentElement--;
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            currentSize = currentSize / 2 + currentSize % 2;
        }
        System.out.println("Total sum of elements: " + array[0]);
    }

}
