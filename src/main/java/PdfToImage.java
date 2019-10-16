
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.xmlgraphics.util.MimeConstants;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class PdfToImage {

	public static void main(String[] args) {

		try {
			
			/*String inPath="D:\\doctiger\\aspose_lib\\docx_files\\T_and_CArbitration.docx";
		    String outPath="D:\\doctiger\\aspose_lib\\docx_files\\pdftoimagesaved\\T_and_CArbitration.png";
		    
		    File theFile = new File(inPath);
		    File outile=new File(outPath);
		    
		    WordprocessingMLPackage wordMLPckg = Docx4J.load(theFile);
		    OutputStream os = new FileOutputStream(outile);
		    
		    FOSettings settings = Docx4J.createFOSettings();
		   
		    settings.setWmlPackage(wordMLPckg);
//		    settings.setApacheFopMime("images/png");
		    settings.setApacheFopMime(MimeConstants.MIME_PNG);
//		     Docx4J.toFO(settings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);
		    Docx4J.toFO(settings, os, Docx4J.FLAG_NONE);
		    os.close();
		    System.out.println("DOne");*/
			System.out.println("data");
			
			XWPFDocument docx = new XWPFDocument(new FileInputStream("D:\\doctiger\\aspose_lib\\docx_files\\T_and_CArbitration.docx"));
                  System.out.println(docx);
		        List<XWPFPictureData> piclist = docx.getAllPictures();
		        System.out.println(piclist.size());
		        // traverse through the list and write each image to a file
		        Iterator<XWPFPictureData> iterator = piclist.iterator();
		        int i = 0;
		        while (iterator.hasNext()) {
		            XWPFPictureData pic = iterator.next();
		            byte[] bytepic = pic.getData();
		            BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytepic));
		            ImageIO.write(imag, "jpg", new File("D:\\doctiger\\aspose_lib\\docx_files\\pdftoimagesaved\\" + pic.getFileName()));
		            i++;
		            System.out.println(i);
		        }
		        docx.close();
			
			
			
	       /* String sourceDir = "D:\\doctiger\\aspose_lib\\docx_files\\T_and_CArbitration.pdf"; // Pdf files are read from this folder
	        String destinationDir = "D:\\doctiger\\aspose_lib\\docx_files\\pdftoimagesaved\\"; // converted images from pdf document are saved here

	        File sourceFile = new File(sourceDir);
	        File destinationFile = new File(destinationDir);
	        if (!destinationFile.exists()) {
	            destinationFile.mkdir();
	            System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
	        }
	        if (sourceFile.exists()) {
	            System.out.println("Images copied to Folder: "+ destinationFile.getName());             
	            PDDocument document = PDDocument.load(sourceDir);
	            List<PDPage> list = document.getDocumentCatalog().getAllPages();
	            System.out.println("Total files to be converted -> "+ list.size());

	            String fileName = sourceFile.getName().replace(".pdf", "");             
	            int pageNumber = 1;
	            for (PDPage page : list) {
	                BufferedImage image = page.convertToImage();
	                File outputfile = new File(destinationDir + fileName +"_"+ pageNumber +".png");
	                System.out.println("Image Created -> "+ outputfile.getName());
	                ImageIO.write(image, "png", outputfile);
	                pageNumber++;
	            }
	            document.close();
	            System.out.println("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
	        } else {
	            System.err.println(sourceFile.getName() +" File not exists");
	        }
*/
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		
	}

}
