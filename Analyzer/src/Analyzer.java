// import HMM.java
// import Stemmer.java


public class Analyzer {
    
	
	public String filter (String str) {
		char [] charList = str.toCharArray();
		for (int i = 0 ; i<charList.length; i++) {
			if ( !isCNorEN(charList[i]) ) {
				charList[i] = ' ';
			}
		}
		return String.valueOf(charList);
	}
	
	public boolean isCNorEN(char c) {
		if (c<='z' && c>='a')
			return true;
		else if (c<='Z' && c>='A')
			return true;
		else if ((int)c<=40869 && (int)c>=19968)  //from 19968 to 40869
	        return true;
		else return false;
	}
	
	public void print(String str) {
		System.out.println(str);
	}
	
	public String splitAndDivide(String str) {
		String [] strList = str.trim().split(" ");
		String keyWord="";
		
		for (int i =0 ; i<strList.length; i++) {
			if (strList[i].length() == 0 || strList[i].equals(" "))
				continue;
			//System.out.println(strList[i]);
			keyWord += (divideToCNEN(strList[i])+" ");
			System.out.println("in splitAndDivide,keyWord="+keyWord);
			//break;
		}
		//System.out.println("end for splitAndDivide,keyWord="+keyWord);
		return keyWord;		
	}
	
	public String divideToCNEN(String str) {
		char [] strCharList = str.toCharArray();
		char [] strCNList = new char[str.length()];
		char [] strENList = new char[str.length()];
		for (int i = 0 ; i<str.length(); i++) {
			if (strCharList[i]<=40869 && strCharList[i]>=19968)  //from 19968 to 40869
		        strCNList[i] = strCharList[i];
			else strENList[i] = strCharList[i];
		}
		String CN = String.valueOf(strCNList);
		String EN = String.valueOf(strENList);
		System.out.println("in divideToCNEN,cn="+CN+" en="+EN);
		return CN+" "+EN;
	}
	
	public String process (String keyWord, HMM myHMM, Stemmer myStemmer) {
		String [] keyWordList = keyWord.split(" ");
		String answer = "";
		for (int i = 0 ; i<keyWordList.length; i++) {
			String target = keyWordList[i].trim();
			if (target.length() == 0)
				continue;
			else if ((int)target.charAt(0)<=40869 && (int)target.charAt(0)>=19968) {
				// CN
				String str = myHMM.segmentation(target);
				answer += (str+" ");
			}
			else { 
				char [] charList = target.toLowerCase().toCharArray();
			    for (int j = 0 ; j<charList.length; j++)
			    	myStemmer.add(charList[j]);
			    myStemmer.stem();
			    answer += (myStemmer.toString()+" ");
			}
			System.out.println("in processfunction:"+answer);
			//break;
		}
		return answer.trim();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Analyzer myAnalyzer = new Analyzer();
        HMM myHMM = new HMM();
        Stemmer myStemmer = new Stemmer();
        String str1 = "刚刚得到最新消息：校招薪资一律降低为10K，等级为P5。Lucy开会时已经明确表示，明年校招名额会卡得很严，全集团加蚂蚁、菜鸟总名额为400人；同时加大对现有老白兔的淘汰力度。";
        String str2 = myAnalyzer.filter(str1);
        String  keyWord ;
        keyWord = myAnalyzer.splitAndDivide(str2);
        String answer = myAnalyzer.process(keyWord, myHMM, myStemmer);
        System.out.println(answer);
        String [] keyWordList = answer.trim().split(" ");
        for (int i = 0 ; i<keyWordList.length; i++) {
        	System.out.println("keyWord "+i+": "+keyWordList[i]);
        }
        //for(int i = 0 ; i<str.length(); i++) {
        	//System.out.println( str.charAt(i)+" "+myAnalyzer.isCNorEN( str.charAt(i) ) );
        //}
        
	}

}
