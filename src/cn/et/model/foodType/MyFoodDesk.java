package cn.et.model.foodType;

import java.util.List;
import java.util.Map;

import cn.et.model.MysalDBUtils;
import cn.et.model.PageTools;

public class MyFoodDesk {
	/**
	 * 根据传递过来的参数，查出数据库中符合条件的总数据数
	 * @param name
	 * @return
	 */
	public Integer getTableListCount(String name){
		if(name==null){
			name="";
		}
		String sql="select count(rownum) as cr from FOODTYPE where TYPENAME like '%"+name+"%'";
		List<Map> list=MysalDBUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	
	public PageTools getTablePage(String name,Integer curpage){
		if(name==null){
			name="";
		}
		Integer totalCount=getTableListCount(name);
		PageTools pageTools=new PageTools(curpage, totalCount, null);
		List<Map> list=MysalDBUtils.query("select * from (select t.*,rownum rw from FOODTYPE t where t.TYPENAME like '%"+name+"%') where rw>="+pageTools.getStartIndex()+" and rw<="+pageTools.getEndIndex()+"");
		pageTools.setData(list);
		return pageTools;
	}
	
	public void savaDesk(String deskName){
		System.out.println(deskName);
		String sql="insert into FOODTYPE values((select max(TYPEID)+1 from FOODTYPE),'"+deskName+"')";
		try {
			MysalDBUtils.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFoodType(String id){
		String sql="delete from FOODTYPE where TYPEID="+id;
		try {
			MysalDBUtils.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateFoodType(String tname,String tid){
		String sql="update foodtype set typename='"+tname+"' where typeid="+tid;
		try {
			MysalDBUtils.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Map> getAllFoodType(){
		String sql="select * from foodType";
		return MysalDBUtils.query(sql);
	}
	
}
