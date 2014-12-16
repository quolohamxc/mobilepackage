package ws.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sleepycat.bind.serial.ClassCatalog;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

public class deleteInfo {
	public static void deleteinfo(String path) throws IOException {
		HashMap<String, String> map = new HashMap();
		
		//String dir = "./db/";
		
		File InputDir = new File(path);
		File[] files = InputDir.listFiles();
		
		//File f = new File(dir, "log.txt");
		
		for(File file : files) {
			FileInputStream fis = new FileInputStream(file);
			//System.out.println(file.getName());
			InputStreamReader reader = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(reader);
			String tt = "";
			String tmp = "";
			String group = "";
			while((tmp = buffer.readLine()) != null) {
				tt += group;
				group = tmp;
			}
			
			buffer.close();
			reader.close();
			fis.close();
			
			if(map.containsKey(tt)) {
				file.delete();
				System.out.println(map.get(tt) + " " + file.getName());
			} else {
				map.put(tt, file.getName());
			}
			
		}
	}
	
	
	public static void test(String path) {
		File sourcefile = new File(path, "sourcepages");
		File infofile = new File(path, "info");
		File indexfile = new File(path, "index");
		
		
		for(int i = 1; i <= 91270; i++) {
			File source = new File(sourcefile, Integer.toString(i)+ ".html");
			File info = new File(infofile, Integer.toString(i) + ".txt");
			File index = new File(indexfile, Integer.toString(i) + ".txt");
			
			//
			if(source.exists()) {
				if(info.exists()) {
					if(index.exists()) {
						//
					} else {
						source.renameTo(new File(sourcefile, Integer.toString(i)));
						info.renameTo(new File(infofile, Integer.toString(i)));
					}
				} else {
					if(index.exists()) {
						source.renameTo(new File(sourcefile, Integer.toString(i)));
						index.renameTo(new File(indexfile, Integer.toString(i)));
					} else {
						source.renameTo(new File(sourcefile, Integer.toString(i)));
					}
				}
			} else {
				if(info.exists()) {
					if(index.exists()) {
						info.renameTo(new File(infofile, Integer.toString(i)));
						index.renameTo(new File(indexfile, Integer.toString(i)));
					} else {
						info.renameTo(new File(infofile, Integer.toString(i)));
					}
				} else {
					if(index.exists()) {
						index.renameTo(new File(indexfile, Integer.toString(i)));
					} else {
						
					}
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		//删除info中相同的
		//deleteinfo("./download/info");
		//提取对应不上的
		test("./download");
		System.out.println("finished!");
	}
}
