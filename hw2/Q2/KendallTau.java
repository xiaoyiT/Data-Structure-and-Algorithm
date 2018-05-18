import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class KendallTau {
	private int[] a;
	//private int[] b;
	private int count;

	public KendallTau() {
		readFile("data1_32768");
		// a = new int[]{0, 1, 2, 3, 4};
		// b = new int[]{1, 0, 3, 2, 4};
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

	public int distance() {
		return mergeCount(a);
	}

	public int mergeCount(int[] m) {
		count = 0;
		int len = m.length;
		mergeSort(m, 0, len - 1);
		return count;
	}

	public void mergeSort(int[] m, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;

			//sort first and second half
			mergeSort(m, start, mid);
			mergeSort(m, mid + 1, end);

			//merge two halves
			merge(m, start, mid, end);
		}
	}

	public void merge(int[] m, int start, int mid, int end) {
		int len1 = mid - start + 1;
		int len2 = end - mid;

		int[] a1 = new int[len1];
		int[] a2 = new int[len2];

		for(int i = 0; i < len1; i++)
			a1[i] = m[start + i];
		for(int i = 0; i < len2; i++)
			a2[i] = m[mid + 1 + i];

		int i = 0, j = 0, k = start;
		while(i < len1 && j < len2) {
			if(a1[i] <= a2[j]) {
				m[k] = a1[i];
				i++;
			}else {
				m[k] = a2[j];
				j++;
				count += len1 - i;
			}
			k++;
		}

		if(i < len1) {
			while(i < len1) {
				m[k] = a1[i];
				i++;
				k++;
			}
		}

		if(j < len2) {
			while(j < len2) {
				m[k] = a2[j];
				k++;
				j++;
			}
		}
	}

	public static void main(String[] args) {
		KendallTau kt = new KendallTau();
		int count = kt.distance();
		System.out.println("count is: " + count);
	}
}