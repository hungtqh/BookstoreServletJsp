package controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.ProductServices;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/admin/update_product")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, // over 10 KB data will be stored on disk, instead of memory
		maxFileSize = 1024 * 300,      // max file size is 300 KB
		maxRequestSize = 1024 * 1024   // max mass data send to this servlet is 1 MB
)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateProductServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductServices pServices = new ProductServices(request, response);
		pServices.updateProduct();
	}

}
