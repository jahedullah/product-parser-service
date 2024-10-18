package com.jahedul.productparserservice.util;

import com.jahedul.productparserservice.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductExcelParser {

    public List<Product> parseFileToProducts(MultipartFile file) throws IOException {
        List<Product> products = new ArrayList<>();

        if (file.isEmpty()) {
            throw new IOException("Uploaded file is empty");
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue; // Skip empty rows
                }

                Product product = createProductFromRow(row);
                products.add(product);
            }
        }

        return products;
    }

    private Product createProductFromRow(Row row) {
        String sku = getCellValue(row.getCell(0));
        String title = getCellValue(row.getCell(1));
        BigDecimal price = new BigDecimal(getCellValue(row.getCell(2)));
        int quantity = (int) Math.floor(Double.parseDouble(getCellValue(row.getCell(3))));
        ZonedDateTime lastUpdated = ZonedDateTime.now();

        Product product = new Product();
        product.setSku(sku);
        product.setTitle(title);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setLastUpdated(lastUpdated);

        return product;
    }

    private String getCellValue(Cell cell) {
        if (Objects.isNull(cell)) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
