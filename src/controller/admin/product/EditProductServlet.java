package controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.service.ProductServices;

@WebServlet("/admin/edit_product")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductServices pServices = new ProductServices(request, response);
		pServices.editProduct();
	}

}
