package cn.et.model;
import java.util.List;
public class PageTools {

	/**
	 * 当前页(动态由页面传递的)
	 */
	private Integer curPage;
	/**
	 * 每页显示的条数,默认为10
	 */
	private Integer pageCount=10;
	/**
	 * 上一页
	 * 如果是第一页，那它的上一页就是第一页，否则当前页减一
	 * prePage=(prePage=curPage==1?1:curPage-1)
	 */
	private Integer prePage;
	/**
	 * 下一页
	 * 如果是最后一页，那么它的下一页就是当前页，否则当前页加一
	 * nextPage=(curPage==totalPage?totalPage:curPage+1)
	 */
	private Integer nextPage;
	/**
	 * 总页数
	 * 从数据库中查询到的总记录数除以每页显示的条数，如果为0，则总页数是它们两个的商，否则，就是它们的商加1
	 * tatalPage=(totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1)
	 */
	private Integer totalPage;
	/**
	 * 总记录数（从数据库查询）
	 */
	private Integer totalCount;
	/**
	 * 起始索引，就是每页从第几条数据显示
	 * curPage pageCount start-end
	 * 1  		10			1-10
	 * 2		10			11-20
	 * start=(curpage-1)*pageCount+1
	 * end=curpage*pageCount
	 */
	private Integer startIndex;
	/**
	 * 结束索引，每页从第几条数据结束
	 */
	private Integer endIndex;
	/**
	 * 存储最终查询的数据
	 */
	private List data;
	
	public Integer getCurPage() {
		return curPage;
	}
	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPrePage() {
		return prePage;
	}
	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	/**构造参数
	 * @param curPage 传过来的当前页
	 * @param totalCount 数据库查询的总数据数
	 * @param pageCount 每页显示多少条数据
	 */
	public PageTools(Integer curPage,Integer totalCount,Integer pageCount){
		this.curPage=curPage;
		this.totalCount=totalCount;
		this.pageCount=(pageCount==null?this.pageCount:pageCount);
		this.prePage=(curPage==1?1:this.curPage-1);
		this.totalPage=(totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1);
		this.nextPage=(curPage==this.totalPage?this.totalPage:curPage+1);
		this.startIndex=((curPage-1)*this.pageCount+1);
		this.endIndex=(curPage*this.pageCount);
	}
}
