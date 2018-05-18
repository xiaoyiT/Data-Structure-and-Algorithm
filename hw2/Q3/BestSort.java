import java.util.Arrays;

class BestSort {
	private int[] a;

	public BestSort() {
		a = new int[8192];
		int[] size = new int[]{1024, 2048, 4096, 1024};
		int[] value = new int[]{1, 11, 111, 1111};
		int k = 0;

		for(int i = 0; i < size.length; i++) {
			int len = size[i];
			for(int j = 0; j < len; j++) {
				a[k] = value[i];
				k++;
			}
		}
	}

	public void sort() {
		for(int i = 1; i < a.length; i++) {
			int key = a[i];
			int j = i - 1;
			while(j >= 0 && a[j] > key) {
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
		BestSort bs = new BestSort();
		bs.sort();
		bs.print();
	}
}