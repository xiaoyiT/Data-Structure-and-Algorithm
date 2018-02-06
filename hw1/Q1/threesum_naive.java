/******* hw1-1 (3-sum problem) ***********/
/**********   O(N^3)  ********************/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

class sum{

	public static void main(String[] args){
		long startTime = System.nanoTime();
		List<Integer> data = new ArrayList<>();
		data = readFile("8192int.txt");
		int count = threeSum(data);
		System.out.println("There is " + count + " integer pairs satisfied.");
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		double seconds = (double)totalTime / 1000000000.0;
		System.out.println("Total running time is " + seconds + " s.");
	}
	
	public static int threeSum(List<Integer> data){
		int len = data.size();
		int count = 0;
		for(int i = 0 ; i < len ; i++){
			for(int j = i + 1 ; j < len ; j++){
				for(int k = j + 1 ; k < len ; k++){
					int sum = data.get(i) + data.get(j) + data.get(k);
					if(sum == 0)
						count++;
				}
			}
		}
		return count;
	}


	public static List<Integer> readFile(String filename) {
		List<Integer> data = new ArrayList<>();
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
		return data;
	}
}