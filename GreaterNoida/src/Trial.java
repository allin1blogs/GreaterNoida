import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

public class Trial 
{
	public static void main(String...s)throws IOException, COSVisitorException
	{
		int i=2;
		PDFMergerUtility pdfMerger=new PDFMergerUtility();
		pdfMerger.setDestinationFileName("C:/Resources/HCL.pdf");
		pdfMerger.addSource("D:\\HCL\\Single_Document\\Part_1 of 16.pdf");
		pdfMerger.addSource("D:\\HCL\\Single_Document\\Part_1_of_16.pdf");
		for(i=2;i<=16;i++)
			pdfMerger.addSource("D:\\HCL\\Single_Document\\Part_"+i+" of 16.pdf");
		pdfMerger.mergeDocuments();
	}
}
