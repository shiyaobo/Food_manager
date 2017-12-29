package cn.et.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.MyDesk;
import cn.et.model.PageTools;
import cn.et.model.foodType.MyFoodDesk;

/**
 * Servlet implementation class FoodServlet
 */
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
   MyFoodDesk mfd=new MyFoodDesk();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset='UTF-8'");
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("dname");
		String curPage=request.getParameter("curPage");
		Integer curPageInt=1;
		if(curPage!=null){
			curPageInt=Integer.valueOf(curPage);
		}
		PageTools tableList=mfd.getTablePage(name, curPageInt);
		//将list集合存在request中，
		request.setAttribute("tableList", tableList);
		//通过情求转发将request中的集合传到jsp页面中
		request.getRequestDispatcher("/detail/cuisineList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
