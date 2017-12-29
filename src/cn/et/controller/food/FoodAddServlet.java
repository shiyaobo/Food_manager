package cn.et.controller.food;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.et.model.food.MyFoodList;

/**
 * Servlet implementation class FoodAddServlet
 */
public class FoodAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    MyFoodList mfl=new MyFoodList();
    String absPath="D:/myImage";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//���������һ���ļ��ϴ�����
		ServletFileUpload.isMultipartContent(request);
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//����һ���µ��ļ��ϴ�������� 
		ServletFileUpload upload = new ServletFileUpload(factory); //�����ϴ����ļ���С�����ֵ upload.setSizeMax( maxFileSize ); 
		upload.setHeaderEncoding("UTF-8");
		
		//�������󣬻�ȡ�ļ��� 
		
		String foodName="";
		String price="";
		String typeId="";
		ServletContext sc=this.getServletContext();
		List fileItems;
//		String absPath=sc.getRealPath("/myImage");
		String spath="/";
		try {
			fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator(); 
			while(i.hasNext()){
				// �����ϴ����ļ��� 
				FileItem fi = (FileItem)i.next(); 
				if(fi.isFormField()){
					if(fi.getFieldName().equals("foodName")){
						foodName=fi.getString("UTF-8");
					}
					if(fi.getFieldName().equals("price")){
						price=fi.getString();
					}
					if(fi.getFieldName().equals("typeId")){
						typeId=fi.getString();
					}
				}else{
					InputStream is=fi.getInputStream();
					String name=fi.getName();
					String destPath=absPath+"/"+name;
					spath=spath+name;
					FileOutputStream fis=new FileOutputStream(destPath);
					byte[] bt=new byte[1024];
					int x=-1;
					while((x=is.read(bt))!=-1){
						fis.write(bt,0,x);
					}
					fis.close();
					is.close();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		mfl.savaFood(typeId, foodName, price, spath);
		request.getRequestDispatcher("/FoodList").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
