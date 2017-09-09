package com.op.solr.util;

/**
 * 
 * @author WinZhong
 *
 */
public class SolrUtils {

	
	  /**
	   * See: {@link org.apache.lucene.queryparser.classic queryparser syntax} 
	   * for more information on Escaping Special Characters
	   * @author WinZhong
	   */
	 public static String escapeQueryChars(String s) {
		    StringBuilder sb = new StringBuilder();
		    for (int i = 0; i < s.length(); i++) {
		      char c = s.charAt(i);
		      // These characters are part of the query syntax and must be escaped
		      if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
		        || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
		        || c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/') {
		        sb.append('\\');
		      }
		      sb.append(c);
		    }
		    return sb.toString();
		  }
	
}
