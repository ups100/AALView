package pl.edu.pw.elka.aal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class DataReader {

	private Vector<Integer> data;
	private Vector<Integer> value;
	
	DataReader()
	{
		data = new Vector<Integer>();
		value = new Vector<Integer>();
	}
	
	void readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		
		Scanner scaner = new Scanner(file);
		
		Vector<Integer> tmp = new Vector<Integer>();
		int lastSize = 0;
		while(scaner.hasNextInt())
		{
			int size = scaner.nextInt();
			
			if(!scaner.hasNextInt()) {
				return;
			} else {
				int nextValue = scaner.nextInt();
				if(lastSize != size) {
					if(lastSize != 0) {
						long buffer = 0;
						for(int i : tmp) {
							buffer +=i;
						}
						
						int endValue = (int)(buffer/tmp.size());
						data.add(lastSize);
						value.add(endValue);
						tmp.clear();
					}
					
					lastSize = size;
					
				}
				
				tmp.add(nextValue);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	Vector<Integer> getData()
	{
		return (Vector<Integer>)data.clone();
	}
	
	@SuppressWarnings("unchecked")
	Vector<Integer> getValues()
	{
		return (Vector<Integer>)value.clone();
	}
}
