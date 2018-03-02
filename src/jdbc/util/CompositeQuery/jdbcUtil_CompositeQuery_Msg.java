package jdbc.util.CompositeQuery;

import java.io.PrintStream;
import java.util.*;

public class jdbcUtil_CompositeQuery_Msg {
	
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		if ("empname".equals(columnName)) {
			aCondition = "e." + columnName + " like '%" + value + "%'";
		}
		if ("title".equals(columnName)) {
			aCondition = "m." + columnName + " like '%" + value + "%'";
		}
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		
		for (String key : keys) {
			String value = ((String[]) map.get(key))[0];
			if ((value != null) && (value.trim().length() != 0) && (!"action".equals(key))) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());
				if (count == 1) {
					whereCondition.append(" where " + aCondition);
				} else {
					whereCondition.append(" and " + aCondition);
				}
				//System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		return whereCondition.toString();
	}

	public static void main(String[] argv) {

		Map<String, String[]> map = new TreeMap();

		map.put("title", new String[] { "討論" });
		map.put("empname", new String[] { "林語書" });

		//在MsgbroadDAO List<MsgbroadVO> getAll(Map<String, String[]> map)有用到
		String finalSQL =

				"select m.msgno, m.empno, m.title, m.msg, to_char(time,'HH24:MI') hour,to_char(time,'YYYY') year, to_char(time,'MM/DD') day "

			  + "from msgbroad m join employee e on m.empno = e.empno "

			  +  jdbcUtil_CompositeQuery_Msg.get_WhereCondition(map)
			  
			  + "order by msgno desc";

		System.out.println("finalSQL = " + finalSQL); 
	}
}