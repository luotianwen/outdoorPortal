package com.op.util.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.wltea.analyzer.cfg.WinDefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * 
 * @author WinZhong
 *
 */
public class WordsAnalyzer {

	private static Set<String> placeSet = new HashSet<String>();
	private static Set<String> badWordSet = new HashSet<String>();	
	
	private static void initplace(String dictionary,Set<String> set){
		if(set.size()==0){
			System.out.println("加载词典："+dictionary);
			loadDict(dictionary, set);
			/*try {
				InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(dictionary);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
				while(true){
					String word = reader.readLine();
					if(word==null || word.equals(""))
						break;
					else
						place.add(word);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
		}
	}
	
	private static void loadDict(String dictionary,Set<String> set){
		//读取词典文件
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(dictionary);
        if(is == null){
        	throw new RuntimeException("Dictionary not found!!!");
        }
        
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is , "UTF-8"), 512);
			String theWord = null;
			do {
				theWord = br.readLine();
				if (theWord != null && !"".equals(theWord.trim())) {
					set.add(theWord.trim().toLowerCase());
				}
			} while (theWord != null);
			
		} catch (IOException ioe) {
			System.err.println("Dictionary loading exception.");
			ioe.printStackTrace();
		}finally{
			try {
				if(is != null){
                    is.close();
                    is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 敏感词提取
	 * @param text
	 * @return
	 */
	public static Set<String> badWord(String text){
		initplace("words/badWord.dic",badWordSet);
		return analyzer(text,"words/badWord.dic",badWordSet,false);
	}
	
	/**
	 * 地点提取
	 * @param text
	 * @return
	 */
	public static Set<String> place(String text){
		initplace("words/place.dic",placeSet);
		return analyzer(text,"words/place.dic",placeSet,false);
	}
	
	/**
	 * 去掉html标签后地点提取
	 * @param text
	 * @return
	 */
	public static Set<String> placeFilterHTML(String text){
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);   
		return place(p_html.matcher(text).replaceAll(""));
	}	
	/**
	 * 文字分析
	 * @param text   文本
	 * @param dictionary   词典位置
	 * @param is_refresh   是否刷新词库
	 * @return
	 */
	private static Set<String> analyzer(String text,String dictionary,Set<String> dictionarySet,boolean is_refresh){
		
		WinDefaultConfig mycfg =  new  WinDefaultConfig(); 
		//设置为智能分词   
		mycfg.setUseSmart(true);  
		//是否刷新词库
		mycfg.setIS_REFRESH(is_refresh);
		//动态设置自定义的词库
		mycfg.setMainDictionary(dictionary); 
		IKSegmenter seg = new IKSegmenter(new StringReader(text) ,mycfg);
		Lexeme lex=null;  
		Set<String> set = new HashSet<String>();
        try {
			while((lex=seg.next())!=null){  
				String item = lex.getLexemeText();
				if(item.length()>1){
					//System.out.print(item+"|"); 
					if(dictionarySet.contains(item.toLowerCase())){
						set.add(item);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
		
		return set;
	}
	
	public static void main(String[] args) throws IOException {
		
/*		List<String> list = new ArrayList<String>();  
        list.add("a");  
        list.add("b");   
        list.add("b");   
        list.add("b");   
        list.add("b");   
        list.add("b");  
        list.add("c");  
        list.add("d");  
        list.add("b");  
        list.add("c");  
        list.add("a");  
        list.add("a");  
        list.add("a");  
        Set<String> uniqueSet = new TreeSet<String>(list); 
        //统计出现次数
        for (String temp : uniqueSet) {  
           log.info(temp + ": " + Collections.frequency(list, temp));  
        }  */
		
		System.out.println(WordsAnalyzer.placeFilterHTML("我在马尔代夫旅游，北京市下一站准备去马来西亚，然后回到中国河南省信阳故乡……125465878  454545 inf<img  src=\"http://qq1234.org/uploads/allimg/141117/3_141117102438_4.jpg\">"));
		
		System.out.println(WordsAnalyzer.badWord("帮办理文凭"));
		
		System.out.println(WordsAnalyzer.place("我在马尔代夫旅游，下一站准备去马来西亚，然后回到中国故乡……"));
	}
}
