import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class PdfToDocx {

	public static void main(String[] args) {
		
		try {
			
			XWPFDocument doc = new XWPFDocument();
			String pdf = "D:\\doctiger\\aspose_lib\\docx_files\\downloadedFiles\\Vendor Registration Form-TPSSL.pdf";
			PdfReader reader = new PdfReader(pdf);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
			
			for (int i = 1; i <= reader.getNumberOfPages(); i++) {
				System.out.println("i: "+i);
			    TextExtractionStrategy strategy =
			      parser.processContent(i, new SimpleTextExtractionStrategy());
			    String text = strategy.getResultantText();
			    XWPFParagraph p = doc.createParagraph();
			    XWPFRun run = p.createRun();
			    run.setText(text);
			    run.addBreak(BreakType.PAGE);
			}
			
			FileOutputStream out = new FileOutputStream("D:\\doctiger\\aspose_lib\\docx_files\\downloadedFiles\\Vendor Registration Form-TPSSL.docx");
			doc.write(out);
			doc.close();
			// Close all open files
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
