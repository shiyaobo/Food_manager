package cn.et.controller.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.et.model.PageTools;
import cn.et.model.food.MyFoodList;

/**
 * Servlet implementation class FoodListServlet
 */
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FoodListServlet() {
        super();
    }
    MyFoodList mfl=new MyFoodList();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset='UTF-8'");
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("foodName");
		String curPage=request.getParameter("curPage");
		Integer curPageInt=1;
		if(curPage!=null){
			curPageInt=Integer.valueOf(curPage);
		}
		PageTools tableList=mfl.getFoodList(name, curPageInt);
		//将list集合存在request中，
		request.setAttribute("tableList", tableList);
		//通过情求转发将request中的集合传到jsp页面中
		request.getRequestDispatcher("/detail/foodList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
