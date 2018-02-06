import java.util.Arrays;

class FarthestPair {
	public static void main(String args[]) {
		double[] array = {1.0, -1.0, 3.0, 5.0};
		if(array.length < 2)
			System.out.println("No result.");
		else {
			double[] result = findPair(array);
			System.out.println("The farthest pair is: " + result[0] + " and " + result[1]);
		}
	}

	public static double[] findPair(double[] a) {
		int len = a.length;
		double smallest = a[0];
		double biggest = a[0];
		double[] result = new double[2];
		for(int i = 0 ; i < len ; i++) {
			if(a[i] < smallest) 
				smallest = a[i];
			else if(a[i] > biggest)
				biggest = a[i];
		}
		result[0] = smallest;
		result[1] = biggest;
		return result;
	}
}