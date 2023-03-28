package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dao.CustomerDao;
import dto.BankAccount;
import dto.Customer;
import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;

@WebServlet("/createbankaccount")
public class CreateBankAccount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String banktype = req.getParameter("banktype");

		Customer customer = (Customer) req.getSession().getAttribute("customer");

		List<BankAccount> list = customer.getAccount();
		boolean flag = true;

		for (BankAccount account : list) {
			if (account.getType().equals(banktype)) {
				flag = false;
				break;
			}
		}
		if (flag) {
			BankAccount account = new BankAccount();
			account.setType(banktype);
			if (banktype.equals("saving"))
				account.setAc_limit(10000);
			else
				account.setAc_limit(50000);

			account.setCustomer(customer);

			BankDao bankDao = new BankDao();
			bankDao.save(account);

//			List<BankAccount> list2 = list;
			list.add(account);

			customer.setAccount(list);

			CustomerDao customerDao = new CustomerDao();
			customerDao.update(customer);

			resp.getWriter().print("<h1> Account Created Successfully wait for Mnagement approval</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		} else {
			resp.getWriter().print("<h1>" + banktype + "Account Already Exist</h1>");
			req.getRequestDispatcher("customerHome.html").include(req, resp);
		}

	}
}
