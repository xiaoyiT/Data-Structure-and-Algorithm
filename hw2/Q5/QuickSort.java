import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class QuickSort {
	private int[] a;

	public QuickSort() {
		//a = new int[]{1, 4, 3, 2, 7, 9, 20, 18, 15};
		initialize("data0_1024");
	}

	public void initialize(String filename) {
		List<Integer> data = new ArrayList<Integer>();
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()) {
				int num = Integer.parseInt(scanner.next());
				data.add(num);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		int len = data.size();
		a = new int[len];
		for(int i = 0; i < len; i++) {
			a[i] = data.get(i);
		}
	}

	public int getLength() {
		return a.length;
	}

	public int partition(int low, int high) {
		int mid = (low + high) / 2;
		if(a[low] > a[high])
			swap(low, high);
		if(a[low] > a[mid])
			swap(low, mid);
		if(a[mid] > a[low])
			swap(mid, low);

		int pivotValue = a[low];

		while(low < high) {
			while(low < high && a[high] >= pivotValue) {
				high--;
			}
			swap(high, low);
			while(low < high && a[low] <= pivotValue) {
				low++;
			}
			swap(high, low);
		}
		return low;
	}

	public void swap(int m, int n) {
		int temp = a[m];
		a[m] = a[n];
		a[n] = temp;
	}

	public void sort(int start, int end) {
		if(start >= end)
			return;
		int cufoff = 7;
		if(end <= start + cufoff - 1) {
			insertionSort(start, end);
			return;
		}
		int pivotIndex = partition(start, end);
		sort(start, pivotIndex - 1);
		sort(pivotIndex + 1, end);
	}

	public void insertionSort(int start, int end) {
		for(int i = start + 1; i <= end; i++) {
			int key = a[i];
			int j = i - 1;
			while(j >= start && a[j] > key) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
	}

	public void print() {
		System.out.println(Arrays.toString(a));
	}

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		QuickSort qs = new QuickSort();
		//qs.print();

		int len = qs.getLength();
		qs.sort(0, len - 1);
		//qs.print();
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		double seconds = (double)totalTime / 1000000000.0;
		System.out.println("Total running time is " + seconds + " s.");
	}
}