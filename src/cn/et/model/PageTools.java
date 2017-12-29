package cn.et.model;
import java.util.List;
public class PageTools {

	/**
	 * ��ǰҳ(��̬��ҳ�洫�ݵ�)
	 */
	private Integer curPage;
	/**
	 * ÿҳ��ʾ������,Ĭ��Ϊ10
	 */
	private Integer pageCount=10;
	/**
	 * ��һҳ
	 * ����ǵ�һҳ����������һҳ���ǵ�һҳ������ǰҳ��һ
	 * prePage=(prePage=curPage==1?1:curPage-1)
	 */
	private Integer prePage;
	/**
	 * ��һҳ
	 * ��������һҳ����ô������һҳ���ǵ�ǰҳ������ǰҳ��һ
	 * nextPage=(curPage==totalPage?totalPage:curPage+1)
	 */
	private Integer nextPage;
	/**
	 * ��ҳ��
	 * �����ݿ��в�ѯ�����ܼ�¼������ÿҳ��ʾ�����������Ϊ0������ҳ���������������̣����򣬾������ǵ��̼�1
	 * tatalPage=(totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1)
	 */
	private Integer totalPage;
	/**
	 * �ܼ�¼���������ݿ��ѯ��
	 */
	private Integer totalCount;
	/**
	 * ��ʼ����������ÿҳ�ӵڼ���������ʾ
	 * curPage pageCount start-end
	 * 1  		10			1-10
	 * 2		10			11-20
	 * start=(curpage-1)*pageCount+1
	 * end=curpage*pageCount
	 */
	private Integer startIndex;
	/**
	 * ����������ÿҳ�ӵڼ������ݽ���
	 */
	private Integer endIndex;
	/**
	 * �洢���ղ�ѯ������
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
	/**�������
	 * @param curPage �������ĵ�ǰҳ
	 * @param totalCount ���ݿ��ѯ����������
	 * @param pageCount ÿҳ��ʾ����������
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
