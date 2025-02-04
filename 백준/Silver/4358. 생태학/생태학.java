import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeMap;

public class Main {

	// 백준알고리즘 4358번 생태학
	static int allCount = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<String, Integer> tree = new TreeMap<String, Integer>();
		while (true) {
			String str = br.readLine();
			if (str == null || str.length() == 0) {
				break;
			}
			tree.put(str, tree.getOrDefault(str, 0) + 1);
			allCount++;
		}
		
		Object[] keys = tree.keySet().toArray();
		Arrays.sort(keys);
		
		StringBuilder sb = new StringBuilder();
		for(Object key : keys) {
			String keyStr = (String) key;
			int count = tree.get(keyStr);
			double per = (double)(count * 100.0) / allCount;
			
			sb.append(keyStr + " " + String.format("%.4f", per) + "\n");	// 소수점 4번 째 자리까지 출력 
		}
		
		System.out.println(sb.toString());

	}

}
