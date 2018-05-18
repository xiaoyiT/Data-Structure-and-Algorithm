import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

class BottomUpMS {
	private int count;
	private int[] a;

	public BottomUpMS() {
		count = 0;
		readFile("data0_2048");
		//a = new int[]{3, 1, 9, 2, 7, 5};
	}

	public void readFile(String filename) {
		List<Integer> data = new ArrayList<Integer>();

		//read data from text file and store the data in list
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

		//transfer data into the private array a
		int len = data.size();
		a = new int[len];
		for(int i = 0; i < len; i++) {
			a[i] = data.get(i);
		}
	}

	public int getLength() {
		return a.length;
	}

	public void merge(int start, int mid, int end) {
		//print();

		int len1 = mid - start + 1;
		int len2 = end - mid;

		int[] a1 = new int[len1];
		int[] a2 = new int[len2];

		for(int i = 0; i < len1; i++)
			a1[i] = a[start + i];
		for(int i = 0; i < len2; i++)
			a2[i] = a[mid + 1 + i];

		int i = 0, j = 0, k = start;
		while(i < len1 && j < len2) {
			if(a1[i] <= a2[j]) {
				a[k] = a1[i];
				i++;
			} else {
				a[k] = a2[j];
				j++;
			}
			k++;
			count++;
		}

		while(i < len1) {
			a[k] = a1[i];
			k++;
			i++;
		}

		while(j < len2) {
			a[k] = a2[j];
			j++;
			k++;
		}
		//print();
	}

	public void sort() {
		int totalLength = getLength();
		for(int len = 1; len < totalLength; len *= 2) {
			for(int start = 0; start < totalLength - len; start += 2 * len) {
				int end = Math.min(start + len + len - 1, totalLength - 1);
				int mid = start + len - 1;
				merge(start, mid, end);
			}
		}
	}

	public int getCount() {
		return count;
	}

	public void print() {
		System.out.println(Arrays.toString(a));
	}

	public static void main(String[] args) {
		BottomUpMS bums = new BottomUpMS();
		bums.sort();

		int count = bums.getCount();
		System.out.println("comparison count: " + count);
		//bums.print();
	}
}