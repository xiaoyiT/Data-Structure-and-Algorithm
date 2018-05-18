import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class ShellSort {

	private int[] a;

	public ShellSort(String filename) {
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

	public void sort() {
		int[] hs = {7, 3, 1};
		int count = 0;
		for(int k = 0; k < 3; k++) {
			int h = hs[k];
			for(int i = h; i < a.length; i++) {
				int temp = a[i];
				int j = i;
				while(j >= h && a[j - h] > temp) {
					count++;
					a[j] = a[j - h];
					j = j - h;
				}
				count++;
				a[j] = temp;
			}
		}
		System.out.println("comparison count: " + count);
	}

	public static void main(String[] args) {
		ShellSort ss = new ShellSort("data1_32768");
		ss.sort();
	}
	
}