import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class MergeSort {
	private int[] a;
	private int count;	//count of comparison

	public MergeSort() {
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

	//the function of sort arr[start, ...., end]
	public void sort(int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;

			//sort first and second half
			sort(start, mid);
			sort(mid + 1, end);

			//merge two halves
			merge(start, mid, end);
		}
	}

	//first part[start, ...., mid]
	//second part[mid + 1, ..., end]
	public void merge(int start, int mid, int end) {
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
			}else {
				a[k] = a2[j];
				j++;
			}
			count++;
			k++;
		}

		if(i < len1) {
			while(i < len1) {
				a[k] = a1[i];
				i++;
				k++;
			}
		}

		if(j < len2) {
			while(j < len2) {
				a[k] = a2[j];
				k++;
				j++;
			}
		}
	}

	public int getCount() {
		return count;
	}

	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		int len = ms.getLength();
		ms.sort(0, len - 1);

		int count = ms.getCount();
		System.out.println("comparison count: " + count);
	}
}