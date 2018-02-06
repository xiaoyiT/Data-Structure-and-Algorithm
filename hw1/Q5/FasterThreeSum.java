import java.util.Arrays;

class FasterThreeSum {
	public static void main(String[] args) {
		int[] data = {-1, -2, 0, 1, 2};
		Arrays.sort(data);
		int count = 0;
		if(data.length < 3) {
			System.out.println("Count: " + count);
		} else {
			count = getcount(data);
			System.out.println("Count " + count);
		}

	}

	public static int getcount(int[] data) {
		int count = 0;
		for(int i = 0; i < data.length; i++) {
			int j = i + 1, k = data.length - 1;
			while(j < k) {
				int sum = data[i] + data[j] + data[k];
				if(sum == 0) {
					count++;
					j++;
					k--;
				} else if(sum < 0) {
					j++;
				} else {
					k--;
				}
			}
		}
		return count;
	}

}