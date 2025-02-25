package com.example.prj.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class PaymentReceiptService {

    public byte[] generateReceiptPdf(Long studentId, String studentName, Long roomId, String roomNumber, BigDecimal amount, String paymentMethod, LocalDateTime paymentDate, UUID receiptNumber) throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
        Font regularFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.DARK_GRAY);

        Paragraph title = new Paragraph("Payment Receipt", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(10);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedPaymentDate = paymentDate != null ? paymentDate.format(formatter) : "N/A";

        addCell(table, "Receipt Number:", headerFont, false);
        addCell(table, receiptNumber != null ? receiptNumber.toString() : "N/A", regularFont, true);


        addCell(table, "Student ID:", headerFont, false);
        addCell(table, studentId != null ? studentId.toString() : "N/A", regularFont, true);

        addCell(table, "Student Name:", headerFont, false);
        addCell(table, studentName != null ? studentName : "N/A", regularFont, true);

        addCell(table, "Room ID:", headerFont, false);
        addCell(table, roomId != null ? roomId.toString() : "N/A", regularFont, true);

        addCell(table, "Room Number:", headerFont, false);
        addCell(table, roomNumber != null ? roomNumber : "N/A", regularFont, true);

        addCell(table, "Payment Date:", headerFont, false);
        addCell(table, formattedPaymentDate, regularFont, true);

        addCell(table, "Amount:", headerFont, false);
        addCell(table, amount != null ? amount.toString() : "N/A", regularFont, true);

        addCell(table, "Payment Method:", headerFont, false);
        addCell(table, paymentMethod != null ? paymentMethod : "N/A", regularFont, true);


        document.add(table);

        Paragraph footer = new Paragraph("Thank you for your payment!", regularFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);


        document.close();
        return baos.toByteArray();
    }
    private void addCell(PdfPTable table, String text, Font font, boolean isValue) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        if(!isValue) {
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(10);
        }else {
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        }
        table.addCell(cell);
    }
}