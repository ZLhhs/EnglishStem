import java.io.*;
import java.util.*;




public class dictionarySegmentation {

	int LengthOfDict = 204440;
	String [] dict = new String [LengthOfDict]; // 
	String fileInputAddress = "c:/dictNew.txt";
	
	
	
	public void loadDict () throws Exception {
		File fileRead = new File(fileInputAddress);
		Scanner input = new Scanner (fileRead);
		for (int i = 0 ; input.hasNext(); i++ ) {
			dict[i] = input.nextLine();
			//System.out.println(i);
		}
		input.close();
	}
	
	public String segmentation(StringBuffer str) {
		// this is StringBuffer
		int count =0, start = 0, i ;
		int [] index = new int [str.length()];
		while (start < str.length()) {
			for ( i = 4 ; i >= 2; i-- ) {
				if ( exist(str, start, i) ) { // be careful, may be out of bound
					index[count++] = (start+i);
					start += i;
					break;
				}
			}
			if (i == 1) {
				index[count++] = (start+1);
				start += 1;
			}
		}
		for ( i = count-1 ; i>=0 ; i-- ) {
			str.insert(index[i], ' ');
		}
		//System.out.println(str);
		return str.toString();
	}
	
	public boolean exist(StringBuffer str, int start, int L) {
		if (start + L > str.length())
			return false;
		String s = str.substring(start, start+L);
		//str.s
		for (int i =0 ; i < LengthOfDict ; i++ ) {
			if (dict[i].equals(s))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		dictionarySegmentation CNCut = new dictionarySegmentation();
		CNCut.loadDict();
		StringBuffer s = new StringBuffer ("�й��������쵼���й������ž��ǿ���ս�����м��������������˶�����������");
		CNCut.segmentation(s);

	}

}
