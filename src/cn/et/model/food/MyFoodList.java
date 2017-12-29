package cn.et.model.food;

import java.util.List;
import java.util.Map;

import cn.et.model.MysalDBUtils;
import cn.et.model.PageTools;

public class MyFoodList {
	public Integer getFoodListCount(String name){
		if(name==null){
			name="";
		}
		String sql="select count(rownum) as cr from FOOD where FOODNAME like '%"+name+"%'";
		List<Map> list=MysalDBUtils.query(sql);
		return Integer.parseInt(list.get(0).get("CR").toString());
	}
	
	
	public PageTools getFoodList(String name,Integer curpage){
		if(name==null){
			name="";
		}
		Integer totalCount=getFoodListCount(name);
		PageTools pageTools=new PageTools(curpage, totalCount, null);

		 StringBuffer sb=new StringBuffer();
	     String sql="select * from (select t.*,ft.typename,rownum rn from food t inner join foodtype ft on t.typeid=ft.typeid where t.foodname like '%"+name+"%')"
	               + " where rn>="+pageTools.getStartIndex()+" and rn<="+pageTools.getEndIndex();
	       List<Map> result=MysalDBUtils.query(sql);
	       pageTools.setData(result);
	       return pageTools;
	}
	
	public void savaFood(String typeID,String foodName,String price,String path){
		String sql="insert into FOOD values((select nvl(max(FOODID),0)+1 from FOOD),"+typeID+",'"+foodName+"',"+price+",'"+path+"')";
		try {
			MysalDBUtils.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFood(String id){
		String sql="delete from FOOD where FOODID="+id;
		try {
			MysalDBUtils.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
