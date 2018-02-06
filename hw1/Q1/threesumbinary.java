/******* hw1-2 (3-sum problem) ***********/
/**********   O(N^2logN)  ********************/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class threesumbinary{

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		int[] sortedarray = readData("8192int.txt");
		int count = threeSumBinary(sortedarray);
		System.out.println("There is " + count + " integer pairs satisfied.");
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		double seconds = (double)totalTime / 1000000000.0;
		System.out.println("Total running time is " + seconds + " s.");
	}

	public static int threeSumBinary(int[] a) {
		int len = a.length;
		int count = 0;
		for(int i = 0 ; i < len ; i++) {
			for(int j = i + 1 ; j < len ; j++) {
				int target = -(a[i] + a[j]);
				int start = j + 1, end = len - 1;
				while(start < end) {
					int mid = (start + end) / 2;
					if(a[mid] == target) {
						count++;
						break;
					}
					else if(a[mid] < target)
						start = mid;
					else
						end = mid;
				}
			}
		}
		return count;
	}

	public static int[] readData(String filename) {
		List<Integer> data = new ArrayList<Integer>();
		try{
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()) {
				int i = Integer.parseInt(scanner.next());
				data.add(i);
			}
			scanner.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		int[] newdata = new int[data.size()];
			for(int i = 0 ; i < data.size() ; i++) {
				newdata[i] = data.get(i);
			}
			newdata = sortArray(newdata);
		return newdata;
	}

	public static int[] sortArray(int[] a) {
		int n = a.length;
		for(int i = 1 ; i < n ; i++) {
			int cur = a[i];
			int j = i - 1;
			while(j >= 0 && a[j] < cur) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = cur;
		}
		return a;
	}
}