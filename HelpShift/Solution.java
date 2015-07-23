import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.io.FileWriter;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.NavigableMap;

public class Solution {
	static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded);
	}

	public static void main(String args[]) throws IOException {
		String current = new File(".").getCanonicalPath();
		File stop_words_file = new File(current + "/stop_words.txt");
		FileReader fr = new FileReader(stop_words_file);
		BufferedReader br = new BufferedReader(fr);
		List<String> list = new ArrayList<String>();
		String str2;
		while ((str2 = br.readLine()) != null) {
			list.add(str2.toUpperCase());
		}
		String input = readFile(current + "/input_file.txt");
		StringBuffer sb = new StringBuffer(input);
		List<String> list2 = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		StringTokenizer st = new StringTokenizer(input);
		int i = 0;
		while (st.hasMoreTokens()) {
				list2.add(st.nextToken());
			}
			for(;i<list2.size();i++) {
				
				if(list.contains(list2.get(i).toUpperCase())) {
					list2.remove(list2.get(i));
				}
			}
	

	
			for(i =0 ;i<list2.size();i++) {
			String x  = list2.get(i).replaceAll("[\\[(){}\\,\\.\\;!:\\?<>%\\d\\]]","").toUpperCase();
			if(map.get(x)!= null) {
				map.put(x,map.get(x).intValue() + 1);
					}
					else {
				map.put(x,1);
			}
			}
			Map<Integer,String> reverseMap = new HashMap<Integer,String>();
			
			for(Map.Entry<String,Integer> entry : map.entrySet()) {
				reverseMap.put(entry.getValue(),entry.getKey());
			}
			
			String res = "";
			for(String str : list2 ) {
				res+= str + " ";
				
			}
			TreeMap<Integer,String> treeMap = new TreeMap<Integer,String>(reverseMap);
			System.out.println("Five least commonly used words");
			List<String> set = new ArrayList<String>(treeMap.values());
			i=0;
			for(String s : set){
				System.out.println(s);
				i++;
				if(i==5) {
					break;
				}
			}
			NavigableMap<Integer,String> reverseTreeMap = treeMap.descendingMap();
			i=0;
			System.out.println("Five most commonly used words");
			List<String> set2 = new ArrayList<String>(reverseTreeMap.values());
			for(String s : set2){
				System.out.println(s);
				i++;
				if(i==5) {
					break;
				}
			}
			PrintWriter out = new PrintWriter("output_file.txt");
			out.println(res);
			System.out.println("Output file written to output_file.txt");
	}
	
}