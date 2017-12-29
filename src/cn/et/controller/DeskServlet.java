package cn.et.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyDesk;
import cn.et.model.PageTools;

/**
 * Servlet implementation class DeskServlet
 */
public class DeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DeskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyDesk md=new MyDesk();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ύ������dname
				response.setContentType("text/html;charset='UTF-8'");
				request.setCharacterEncoding("UTF-8");
				String name=request.getParameter("dname");
				String curPage=request.getParameter("curPage");
				Integer curPageInt=1;
				if(curPage!=null){
					curPageInt=Integer.valueOf(curPage);
				}
				PageTools tableList=md.getTablePage(name, curPageInt);
				//��list���ϴ���request�У�
				request.setAttribute("tableList", tableList);
				//ͨ������ת����request�еļ��ϴ���jspҳ����
				request.getRequestDispatcher("/detail/boardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
