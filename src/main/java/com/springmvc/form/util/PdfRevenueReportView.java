package com.springmvc.form.util;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.springmvc.form.model.User;

public class PdfRevenueReportView extends AbstractPdfView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map model, Document document,PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<User> userList = (List<User>) model.get("users");
		Table table = new Table(6);
		table.addCell("ID");
		table.addCell("NAME");
		table.addCell("EMAIL");
		table.addCell("PASSWORD");
		table.addCell("LOGGINID");
		table.addCell("SEX");

		for (User user : userList) {
			table.addCell(user.getId().toString());
			table.addCell(user.getName());
			table.addCell(user.getEmail());
			table.addCell(user.getPassword());
			table.addCell(user.getLoginId());
			table.addCell(user.getSex());	
		}

		document.add(table);
	}
}
