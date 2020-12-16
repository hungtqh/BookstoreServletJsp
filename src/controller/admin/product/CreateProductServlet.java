package controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.ProductServices;

@WebServlet("/admin/create_product")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, // over 10 KB data will be stored on disk, instead of memory
		maxFileSize = 8* 1024 * 1024,      // max file size is 8 MB
		maxRequestSize = 8* 1024 * 1024   // max mass data send to this servlet is 8MB
)
public class CreateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ProductServices pServices = new ProductServices(request, response);
    	pServices.createProduct();
    }

}
