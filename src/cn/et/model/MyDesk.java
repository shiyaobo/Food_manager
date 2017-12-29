package cn.et.model;

import java.util.List;
import java.util.Map;

public class MyDesk {
	/**
	 * 根据传递过来的参数，查出数据库中符合条件的总数据数
	 * @param name
	 * @return
	 */
	public Integer getTableListCount(String name){
		if(name==null){
			name="";
		}
		String sql="select count(rownum) as cr from DESK where DNAME like '%"+name+"%'";
		List<Map> list=MysalDBUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	
	public PageTools getTablePage(String name,Integer curpage){
		if(name==null){
			name="";
		}
		Integer totalCount=getTableListCount(name);
		PageTools pageTools=new PageTools(curpage, totalCount, null);
		List<Map> list=MysalDBUtils.query("select * from (select t.*,rownum rw from DESK t where t.DNAME like '%"+name+"%') where rw>="+pageTools.getStartIndex()+" and rw<="+pageTools.getEndIndex()+"");
		pageTools.setData(list);
		return pageTools;
	}
	
	public void savaDesk(String deskName){
		System.out.println(deskName);
		String sql="insert into DESK values((select max(deskid)+1 from desk),'"+deskName+"',0,'')";
		try {
			MysalDBUtils.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteDest(String id){
		String sql="delete from desk where DESKID="+id;
		try {
			MysalDBUtils.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
