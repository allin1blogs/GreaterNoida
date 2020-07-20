package com.ak.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.Splitter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ak.modals.EM;
import com.ak.modals.EM2;
import com.ak.modals.EM3;
import com.ak.modals.Finance;
import com.ak.modals.General;
import com.ak.modals.HR;
import com.ak.modals.Health;
import com.ak.modals.Land;
import com.ak.modals.Law;
import com.ak.modals.Marketing;
import com.ak.modals.Planning;
import com.ak.modals.Planning2;
import com.ak.modals.Project;
import com.ak.modals.ProjectTech;
import com.ak.modals.Systems;
import com.ak.modals.UE;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;

/*
 *	@Author
 *	Swapril Tyagi 
 *  Updated By:Preeti Rani
 *  Started 20-Jun-2020 to till
 *
*/

public class FileUtils
{	
	public static void createFailedFile(StringBuffer errors)
	{
		String msg=errors.toString();
		byte [] msgdata = msg.getBytes();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(msgdata)));
    	String msgLine = "";
    	String errorFileLocation="C:/Resources/ErrorFile.txt";
    	File file=new File(errorFileLocation+File.separator);
    	if(file.exists())
    		file.delete();
    	try
    	{
    		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
        	while((msgLine=reader.readLine())!=null) 
        	{
    			writer.write(msgLine);
    			writer.newLine();
    		}
        	writer.flush();
    		writer.close();
    	}
    	catch(IOException ioe)
    	{ioe.printStackTrace();}
	}
	
	public static void createErrorFile(File csvFile,List<Integer> lines)
	{
		File errorFile=new File("C:/Resources/ErrorRecords.csv"+File.separator);
		String[] allLines=readLines(csvFile);
		try
		{
			FileWriter writer=new FileWriter(errorFile.getAbsolutePath());
			writer.write(allLines[0]);
			for(int line:lines)
			{
				writer.write("\n");
				writer.write(allLines[line]);
			}
			writer.flush();
			writer.close();
		}
		catch(IOException ioe)
		{ioe.printStackTrace();}
	}
	
	private static String[] readLines(File file)
	{
		String line;
		List<String> lines=new ArrayList<>();
        try
        {
        	FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            while((line=bufferedReader.readLine())!=null)
                lines.add(line);
            bufferedReader.close();
        }
        catch(IOException ioe)
        {ioe.printStackTrace();}
        return lines.toArray(new String[lines.size()]);
    }
	
	public static String viewFile(String name,String webLocation,String fileLocation,String uId,boolean agenda)
	{   System.out.println("viewFile");
	     
		File copyFrom=new File(fileLocation+name);
		
		 System.out.println("fileLocation:1:"+fileLocation);
		 System.out.println("name:1:"+name);
		System.out.println("name:2:"+copyFrom);
		
		File copyTo=new File(webLocation+name);		
		System.out.println("3:"+copyTo);
		
		BufferedInputStream bin=null;
		System.out.println("4");
		BufferedOutputStream bout=null;
		System.out.println("5");
		FileInputStream fin=null;
		System.out.println("6");
		FileOutputStream fout=null;
		System.out.println("7");
		int count=1;
		System.out.println("7");
		try
		{
			if(uId!=null && uId.indexOf(",")!=-1)
			{
				if(agenda)
				{
					fin=new FileInputStream(copyFrom);
					System.out.println("8");
					bin=new BufferedInputStream(fin);
					System.out.println("9");
					fout=new FileOutputStream(copyTo);
					System.out.println("10");
					bout=new BufferedOutputStream(fout);
					System.out.println("11");
					byte[]  buff=new byte[8192];
					System.out.println("12");
					int numChars;
					System.out.println("13");
					while((numChars=bin.read(buff,0,buff.length))!=-1)
						bout.write(buff,0,numChars);
					bout.flush();
					System.out.println("14");
					bout.close();
					System.out.println("15");
					fout.flush();
					System.out.println("16");
					fout.close();
					System.out.println("17");
					bin.close();
					System.out.println("18");
					fin.close();
				}
				
				else
				{
					
					System.out.println(fileLocation+name);
					PDDocument document=PDDocument.load(fileLocation+name);//?????
					System.out.println("19"+document);
					
					System.out.println("20");
					Splitter splitter=new Splitter();
					System.out.println("21");
					List<PDDocument> documents=splitter.split(document);
					System.out.println("22");
					for(Iterator<PDDocument> it=documents.iterator();it.hasNext();)
					{
						PDDocument page=it.next();
						System.out.println("23");
						if(name.indexOf("L.pdf")!=-1) 
							page.save(webLocation+name.substring(0,name.indexOf(".pdf"))+"@"+count+"L.pdf");
						else if(name.indexOf("R.pdf")!=-1)
							page.save(webLocation+name.substring(0,name.indexOf(".pdf"))+"@"+count+"R.pdf");
						else
							page.save(webLocation+name.substring(0,name.indexOf(".pdf"))+"@"+count+".pdf");
						page.close();
						if(name.indexOf("L.pdf")!=-1)
							addWaterMark(webLocation,webLocation,name.substring(0,name.indexOf(".pdf"))+"@"+count+"L.pdf",uId,true);
						else if(name.indexOf("R.pdf")!=-1)
							addWaterMark(webLocation,webLocation,name.substring(0,name.indexOf(".pdf"))+"@"+count+"R.pdf",uId,true);
						else
							addWaterMark(webLocation,webLocation,name.substring(0,name.indexOf(".pdf"))+"@"+count+".pdf",uId,true);
						count++;
					}
					document.close();
				}
			}
			else
				addWaterMark(fileLocation,webLocation,name,uId,false);
		}
		catch(Exception e)
		{e.printStackTrace();}
		return String.valueOf(count);
	}
	
	public static void downloadFile(HttpServletResponse response,String name,String fileLocation,boolean merger,String webLocation,String id)
	{
		if(merger)
		{
			mergeFiles(fileLocation+name+"L.pdf",fileLocation+name+"R.pdf","C:/Resources/"+name+".pdf");
			fileLocation="C:/Resources/";
			name=name+".pdf";
		}
		File file=null;
		BufferedInputStream bin=null;
		FileInputStream fin=null;
		ServletOutputStream sos=null;
		try
		{
			if(name.indexOf(".pdf")!=-1)
				fileLocation=addWaterMark(fileLocation,webLocation,name,id,false);
			file=new File(fileLocation+name);
			fin=new FileInputStream(file);
			bin=new BufferedInputStream(fin);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","Attachment; filename=\""+name+"\"");
			response.setHeader("Pragma","public");
			response.setHeader("Cache-Control","max-age=-1");
			response.setContentLengthLong((long)file.length());
			sos=response.getOutputStream();
			byte[]  buff=new byte[8192];
			int numChars;
			while((numChars=bin.read(buff,0,buff.length))!=-1)
				sos.write(buff,0,numChars);
		}
		catch(Exception e)
		{e.printStackTrace();}
		finally
		{
			try
			{
				if(bin!=null)
					bin.close();
				if(fin!=null)
					fin.close();
				if(sos!=null)
					sos.flush();
			}
			catch(Exception e)
			{e.printStackTrace();}
		}
	}
	
	public static void newDownloadFile(HttpServletResponse response,String name,String fileLocation,boolean merger,String webLocation,String id)
	{
		String[] fileName = name.split("\\.");
		if(merger)
		{
            System.out.println("-----------"+fileLocation+fileName[0]+"L.pdf");
			mergeFiles(fileLocation+fileName[0]+"L.pdf",fileLocation+fileName[0]+"R.pdf","C:/Resources/"+fileName[0]+".pdf");
			fileLocation="C:/Resources/";
			name=fileName[0]+".pdf";
		}
		File file=null;
		BufferedInputStream bin=null;
		FileInputStream fin=null;
		ServletOutputStream sos=null;
		try
		{
			if(name.indexOf(".pdf")!=-1)
				fileLocation=addWaterMark(fileLocation,webLocation,name,id,false);
			file=new File(fileLocation+name);
			fin=new FileInputStream(file);
			bin=new BufferedInputStream(fin);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","Attachment; filename=\""+name+"\"");
			response.setHeader("Pragma","public");
			response.setHeader("Cache-Control","max-age=-1");
			response.setContentLengthLong((long)file.length());
			sos=response.getOutputStream();
			byte[]  buff=new byte[8192];
			int numChars;
			while((numChars=bin.read(buff,0,buff.length))!=-1)
				sos.write(buff,0,numChars);
		}
		catch(Exception e)
		{e.printStackTrace();}
		finally
		{
			try
			{
				if(bin!=null)
					bin.close();
				if(fin!=null)
					fin.close();
				if(sos!=null)
					sos.flush();
			}
			catch(Exception e)
			{e.printStackTrace();}
		}
	}
	
	private static String addWaterMark(String location,String webLocation,String file,String id,boolean view)throws Exception
	{
		String waterMarkedPath=webLocation;
		PdfReader pdfReader=new PdfReader(new RandomAccessFileOrArray(location+file),null);
		Document document=new Document();
		PdfWriter pdfWriter=null;
		if(view)
		{
			pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(webLocation+file.substring(0,file.indexOf(".pdf"))+"@print.pdf"));
			id=id.split(",")[0];
		}
		else
			pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(webLocation+file));
		document.setPageSize(pdfReader.getPageSize(1));
		document.open();
		PdfContentByte pdfContent=pdfWriter.getDirectContent();
		PdfImportedPage page=null;
		Font font=new Font(Font.HELVETICA,25);
		Phrase waterMark=new Phrase("Scanned print by:"+id,font);
		PdfGState pdfGS=new PdfGState();
		pdfGS.setFillOpacity(0.5f);
		for(int i=1;i<=pdfReader.getNumberOfPages();i++)
		{
			page=pdfWriter.getImportedPage(pdfReader,i);
			pdfContent.addTemplate(page,0.0F,0.0F);
			pdfContent.saveState();
			pdfContent.setGState(pdfGS);
			ColumnText.showTextAligned(pdfContent,Element.ALIGN_CENTER,waterMark,297,470,45);
			pdfContent.restoreState();
			if(i!=pdfReader.getNumberOfPages())
				document.setPageSize(pdfReader.getPageSize(i+1));
			document.newPage();
		}
		document.close();
		pdfReader.close();
		return waterMarkedPath;
	}
	
	public static boolean mergeFiles(String file1,String file2,String destinationFile)
	{
		if(!new File(file1).exists() && !new File(file2).exists())
			return false;
		org.apache.pdfbox.util.PDFMergerUtility pdfMerger=new org.apache.pdfbox.util.PDFMergerUtility();
		System.out.println("----1--------");
		try
		{
			if(new File(file1).exists())
				pdfMerger.addSource(file1);
			if(new File(file2).exists())
				pdfMerger.addSource(file2);
			pdfMerger.setDestinationFileName(destinationFile);
			System.out.println(destinationFile);
			pdfMerger.mergeDocuments();
		}
		catch(Exception e)
		{e.printStackTrace();}
		return true;
	}
	
	public static void generateGenReport(ArrayList<General> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of "+records.get(0).getDepartment());
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Scheme Code");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Allotment No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Applicant Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Plot No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Plot Size");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Block");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("Master Plot No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("FTS No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("Relevant Office Order No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(12);
        cell.setCellValue("Date of Order issued");
        cell.setCellStyle(style);
        cell=rowHead.createCell(13);
        cell.setCellValue("Agenda No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(14);
        cell.setCellValue("Relevant Board Meeting No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(15);
        cell.setCellValue("Date of Board Meeting");
        cell.setCellStyle(style);
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getScheme());
            r++;
            row.createCell(r).setCellValue(records.get(j).getAllotmentNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getAlloteeName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPlotNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPlotSize());
            r++;
            row.createCell(r).setCellValue(records.get(j).getBlock());
            r++;
            row.createCell(r).setCellValue(records.get(j).getMasterPlot());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOfficeOrder());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOrderDate());
            r++;
            row.createCell(r).setCellValue(records.get(j).getAgenda());
            r++;
            row.createCell(r).setCellValue(records.get(j).getBoardMeeting());
            r++;
            row.createCell(r).setCellValue(records.get(j).getMeetingDate());
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	public static void generateFinReport(ArrayList<Finance> records,String location,HttpServletResponse response)
	{   System.out.println("generateFinReport:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		  System.out.println("2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("3");
        HSSFFont titleFont=workBook.createFont();
        System.out.println("4");
        titleFont.setBold(true);
        System.out.println("5");
        titleFont.setFontHeightInPoints((short)16);
        System.out.println("6");
        HSSFFont font=workBook.createFont();
        System.out.println("7");
        font.setBold(true);
        System.out.println("8");
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        System.out.println("9");
        titleStyle.setFont(titleFont);
        System.out.println("10");
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        System.out.println("11");
        HSSFCellStyle style=workBook.createCellStyle();
        System.out.println("12");
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of Finance("+records.get(0).getSubdepartment()+") Department.");
        HSSFRow rowHead=sheet.createRow(2);
       String subd=records.get(0).getSubdepartment();
        if(subd.equals("Bank Statement") || subd.equals("Loan")) {
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Bank Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Branch Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("FILE NO.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Period of Statement");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Period of Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("PATRAWALI SANKYA");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("SUBJECT");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Clerk Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("Register Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("Sub Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("Account No.");
        cell.setCellStyle(style);
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getBankName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getBranchName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getStatement());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPeriodOfYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPatrawaliSankya());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSubject());
            r++;
            row.createCell(r).setCellValue(records.get(j).getClerkName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getRegisterName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSubdepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getAccountNo());
            i++;
        }
        }
        else {
        	HSSFCell cell=rowHead.createCell(0);
            cell.setCellValue("S.No");
            cell.setCellStyle(style);
            cell=rowHead.createCell(1);
            cell.setCellValue("FILE NO.");
            cell.setCellStyle(style);
            cell=rowHead.createCell(2);
            cell.setCellValue("CODE NO");
            cell.setCellStyle(style);
            cell=rowHead.createCell(3);
            cell.setCellValue("YEAR ");
            cell.setCellStyle(style);
            cell=rowHead.createCell(4);
            cell.setCellValue("SUBJECT");
            cell.setCellStyle(style);
            cell=rowHead.createCell(5);
            cell.setCellValue("DESIGNATION");
            cell.setCellStyle(style);
            cell=rowHead.createCell(6);
            cell.setCellValue("TYPE OF FILE");
            cell.setCellStyle(style);
            cell=rowHead.createCell(7);
            cell.setCellValue("Sub Department");
            cell.setCellStyle(style);
            int r=0,i=3;
            for(int j=0;j<records.size();j++)
            {
            	r=0;
                HSSFRow row=sheet.createRow(i);
                row.createCell(r).setCellValue(i-2);
                r++;
                row.createCell(r).setCellValue(records.get(j).getFileNo());
                r++;
                row.createCell(r).setCellValue(records.get(j).getCodeNo());
                r++;
                row.createCell(r).setCellValue(records.get(j).getYear());
                r++;
                row.createCell(r).setCellValue(records.get(j).getSubject());
                r++;
                row.createCell(r).setCellValue(records.get(j).getDesignation());
                r++;
                row.createCell(r).setCellValue(records.get(j).getFileType());
                r++;
                row.createCell(r).setCellValue(records.get(j).getSubdepartment());
                i++;
            }
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	
	
	
	public static void generateProReport(ArrayList<Project> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated "+records.get(0).getDepartment()+" report");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Name of Work");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Contractor Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("OPA/FTS No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Work Circle");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("File No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("Upload Date");
        cell.setCellStyle(style);
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getWorkName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getContractorName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpaFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getWorkCircle());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getUploadDate());
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	public static void generatePlanReport(ArrayList<Planning> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of Planning ("+records.get(0).getSubDepartment()+") Department");
        HSSFRow rowHead=sheet.createRow(2);
        String subd=records.get(0).getSubDepartment();
        if(subd.equals("Industry") || subd.equals("Institutional")) {
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Allotment No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("BP No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("File Type");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Applicant Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Plot Size");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Plot No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Block No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("FTS No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("Sub-Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(12);
        cell.setCellValue("Cleark name");
        cell.setCellStyle(style);
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getAllotmentNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getBpNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileType());
            r++;
            row.createCell(r).setCellValue(records.get(j).getApplicantName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPlotSize());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPlotNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getBlockNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSubDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getClerk_Name());
            i++;
        }
        }
        else
        	if(subd.equals("Building NOC")) {
                HSSFCell cell=rowHead.createCell(0);
                cell.setCellValue("S.No");
                cell.setCellStyle(style);
                cell=rowHead.createCell(1);
                cell.setCellValue("Allotment No.");
                cell.setCellStyle(style);
                cell=rowHead.createCell(2);
                cell.setCellValue("BN No.");
                cell.setCellStyle(style);
                cell=rowHead.createCell(3);
                cell.setCellValue("File Type");
                cell.setCellStyle(style);
                cell=rowHead.createCell(4);
                cell.setCellValue("Applicant Name");
                cell.setCellStyle(style);
                cell=rowHead.createCell(5);
                cell.setCellValue("Plot Size");
                cell.setCellStyle(style);
                cell=rowHead.createCell(6);
                cell.setCellValue("Plot No.");
                cell.setCellStyle(style);
                cell=rowHead.createCell(7);
                cell.setCellValue("Block No.");
                cell.setCellStyle(style);
                cell=rowHead.createCell(8);
                cell.setCellValue("Sector");
                cell.setCellStyle(style);
                cell=rowHead.createCell(9);
                cell.setCellValue("Category");
                cell.setCellStyle(style);
                cell=rowHead.createCell(10);
                cell.setCellValue("FTS No.");
                cell.setCellStyle(style);
                cell=rowHead.createCell(11);
                cell.setCellValue("Sub-Department");
                cell.setCellStyle(style);
                cell=rowHead.createCell(12);
                cell.setCellValue("Clerk name");
                cell.setCellStyle(style);
                cell=rowHead.createCell(13);
                cell.setCellValue("Year");
                cell.setCellStyle(style);
                cell=rowHead.createCell(14);
                cell.setCellValue("File No");
                cell.setCellStyle(style);
                int r=0,i=3;
                for(int j=0;j<records.size();j++)
                {
                	r=0;
                    HSSFRow row=sheet.createRow(i);
                    row.createCell(r).setCellValue(i-2);
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getAllotmentNo());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getBn_no());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getFileType());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getApplicantName());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getPlotSize());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getPlotNo());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getBlockNo());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getSector());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getCategory());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getFts());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getSubDepartment());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getClerk_Name());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getYear());
                    r++;
                    row.createCell(r).setCellValue(records.get(j).getFileNo());
                    i++;
                }
                }
        
        	else if(subd.equals("Residential") || subd.equals("Planning(Group Housing)")){
        	 HSSFCell cell=rowHead.createCell(0);
             cell.setCellValue("S.No");
             cell.setCellStyle(style);
             cell=rowHead.createCell(1);
             cell.setCellValue("Allotment No.");
             cell.setCellStyle(style);
             cell=rowHead.createCell(2);
             cell.setCellValue("BP No.");
             cell.setCellStyle(style);
             cell=rowHead.createCell(3);
             cell.setCellValue("File Type");
             cell.setCellStyle(style);
             cell=rowHead.createCell(4);
             cell.setCellValue("Applicant Name");
             cell.setCellStyle(style);
             cell=rowHead.createCell(5);
             cell.setCellValue("Plot Size");
             cell.setCellStyle(style);
             cell=rowHead.createCell(6);
             cell.setCellValue("Plot No.");
             cell.setCellStyle(style);
             cell=rowHead.createCell(7);
             cell.setCellValue("Block No.");
             cell.setCellStyle(style);
             cell=rowHead.createCell(8);
             cell.setCellValue("Sector");
             cell.setCellStyle(style);
             cell=rowHead.createCell(9);
             cell.setCellValue("Category");
             cell.setCellStyle(style);
             cell=rowHead.createCell(10);
             cell.setCellValue("FTS No.");
             cell.setCellStyle(style);
             cell=rowHead.createCell(10);
             cell.setCellValue("Sub-Department");
             cell.setCellStyle(style);
             int r=0,i=3;
             for(int j=0;j<records.size();j++)
             {
             	r=0;
                 HSSFRow row=sheet.createRow(i);
                 row.createCell(r).setCellValue(i-2);
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getAllotmentNo());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getBpNo());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getFileType());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getApplicantName());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getPlotSize());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getPlotNo());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getBlockNo());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getSector());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getCategory());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getFts());
                 r++;
                 row.createCell(r).setCellValue(records.get(j).getSubDepartment());
                 i++;
             }
        }
        	
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	
	public static void generateLawReport(ArrayList<Law> records,String location,HttpServletResponse response)
	{    System.out.println("generateLawReport:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		System.out.println("2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("3");
        HSSFFont titleFont=workBook.createFont();
        System.out.println("4");
        titleFont.setBold(true);
        System.out.println("5");
        titleFont.setFontHeightInPoints((short)16);
        System.out.println("6");
        HSSFFont font=workBook.createFont();
        System.out.println("7");
        font.setBold(true);
        System.out.println("8");
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        System.out.println("9");
        titleStyle.setFont(titleFont);
        System.out.println("10");
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        System.out.println("11");
        HSSFCellStyle style=workBook.createCellStyle();
        System.out.println("12");
        style.setFont(font);
        System.out.println("13");
        style.setLocked(true);
        System.out.println("14");
        HSSFRow titleRow=sheet.createRow(0);
        System.out.println("15");
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        System.out.println("16");
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        System.out.println("17");
        HSSFCell titleCell=titleRow.createCell(3);
        System.out.println("18");
        titleCell.setCellStyle(titleStyle);
        System.out.println("19");
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        System.out.println("20");
        titleRow=sheet.createRow(1);
        System.out.println("21");
        titleCell=titleRow.createCell(4);
        System.out.println("22");
        titleFont.setFontHeightInPoints((short)13);
        System.out.println("23");
        titleStyle.setFont(titleFont);
        System.out.println("24");
        titleCell.setCellStyle(titleStyle);
        System.out.println("25");
        titleCell.setCellValue("Software generated report of Law Department");
        System.out.println("26");
        HSSFRow rowHead=sheet.createRow(2);
        System.out.println("27");
        HSSFCell cell=rowHead.createCell(0);
        System.out.println("28");
        cell.setCellValue("S.No");
        System.out.println("29");
        cell.setCellStyle(style);
        System.out.println("30");
        cell=rowHead.createCell(1);
        System.out.println("31");
        cell.setCellValue("Name of Court");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Petition No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Name of Party");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Petitioner's Advocate");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Respondent's Advocate");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Related Department");
        cell.setCellStyle(style);
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getCourtName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPetitionNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPartyName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPetitionerAdvocate());
            r++;
            row.createCell(r).setCellValue(records.get(j).getRespondentAdvocate());
            r++;
            row.createCell(r).setCellValue(records.get(j).getRelatedDepartment());
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	
	public static void generateEMReport(ArrayList<EM> records,String location,HttpServletResponse response)
	{    System.out.println("generateLawReport:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		System.out.println("2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("3");
        HSSFFont titleFont=workBook.createFont();
        System.out.println("4");
        titleFont.setBold(true);
        System.out.println("5");
        titleFont.setFontHeightInPoints((short)16);
        System.out.println("6");
        HSSFFont font=workBook.createFont();
        System.out.println("7");
        font.setBold(true);
        System.out.println("8");
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        System.out.println("9");
        titleStyle.setFont(titleFont);
        System.out.println("10");
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        System.out.println("11");
        HSSFCellStyle style=workBook.createCellStyle();
        System.out.println("12");
        style.setFont(font);
        System.out.println("13");
        style.setLocked(true);
        System.out.println("14");
        HSSFRow titleRow=sheet.createRow(0);
        System.out.println("15");
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        System.out.println("16");
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        System.out.println("17");
        HSSFCell titleCell=titleRow.createCell(3);
        System.out.println("18");
        titleCell.setCellStyle(titleStyle);
        System.out.println("19");
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        System.out.println("20");
        titleRow=sheet.createRow(1);
        System.out.println("21");
        titleCell=titleRow.createCell(4);
        System.out.println("22");
        titleFont.setFontHeightInPoints((short)13);
        System.out.println("23");
        titleStyle.setFont(titleFont);
        System.out.println("24");
        titleCell.setCellStyle(titleStyle);
        System.out.println("25");
        titleCell.setCellValue("Software generated report of EM Department");
        System.out.println("26");
        HSSFRow rowHead=sheet.createRow(2);
        System.out.println("27");
        HSSFCell cell=rowHead.createCell(0);
        System.out.println("28");
        cell.setCellValue("S.No");
        System.out.println("29");
        cell.setCellStyle(style);
        System.out.println("30");
        cell=rowHead.createCell(1);
        System.out.println("31");
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Opa/Fts");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Work Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("File No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Contractor Name");
        cell.setCellStyle(style);
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpaFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getWorkName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getContractorName());
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
/*----------------------System -----------------------------------*/
	public static void generateSystemReport(ArrayList<Systems> records,String location,HttpServletResponse response)
	{  
		System.out.println("generateSystem Report:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		System.out.println("generateSystem Report:2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("generateSystem Report:3");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of System Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);         
        cell.setCellValue("Date");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);        
        cell.setCellValue("LotNo");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Clerk_Name");//2
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("File_No");//3
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Department");//4
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("FTS_NO_OPA_NO");//5
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("SubjectName");//6
        cell.setCellStyle(style);        
        cell=rowHead.createCell(8);
        cell.setCellValue("Year");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(9);
        cell.setCellValue("No_Of_NoteSheet");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(10);
        cell.setCellValue("No_Of_Cros");
        cell.setCellStyle(style);        
		/*
		 * cell=rowHead.createCell(11); cell.setCellValue("No_Of_A0_Pages");
		 * cell.setCellStyle(style); cell=rowHead.createCell(12);
		 * cell.setCellValue("No_Of_A1_Pages"); cell.setCellStyle(style);
		 * cell=rowHead.createCell(13); cell.setCellValue("No_Of_A2_Pages");
		 * cell.setCellStyle(style); cell=rowHead.createCell(14);
		 * cell.setCellValue("No_Of_A3_Pages"); cell.setCellStyle(style);
		 */       
        cell=rowHead.createCell(11);
        cell.setCellValue("Opt_Name");
        cell.setCellStyle(style);  
        cell=rowHead.createCell(12);
        cell.setCellValue("Total_No_Of_Pages");
        cell.setCellStyle(style);
      
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getDate());
            r++; 
            row.createCell(r).setCellValue(records.get(j).getLotNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getClerk_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFile_No());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOptFts());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getSubjectName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_NoteSheet());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getNo_Of_Cros());
            r++;    
            
			/*
			 * row.createCell(r).setCellValue(records.get(j).getNo_Of_A0_Pages()); r++;
			 * row.createCell(r).setCellValue(records.get(j).getNo_Of_A1_Pages()); r++;
			 * row.createCell(r).setCellValue(records.get(j).getNo_Of_A2_Pages()); r++;
			 * row.createCell(r).setCellValue(records.get(j).getNo_Of_A3_Pages()); r++;
			 */
            row.createCell(r).setCellValue(records.get(j).getOpt_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTotal_No_Of_Pages());
            i++;      
           
            
            
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	
	/*------------------------EM3------------------------------------*/
	public static void generateEM3Report(ArrayList<EM3> records,String location,HttpServletResponse response)
	{  
		System.out.println("generateEM3 Report:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		System.out.println("generateEM3 Report:2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("generateEM3 Report:3");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of "+records.get(0).getDepartment());
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);         
        cell.setCellValue("SECTOR NAME");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);        
        cell.setCellValue("CATEGORY");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("OPA/FTS");//2
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("NAME OF WORK");//3
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("CONTRACTOR NAME");//4
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("DEPARTMENT");//5
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("FILE NUMBER");//6
        cell.setCellStyle(style);        
        cell=rowHead.createCell(8);
        cell.setCellValue("YEAR");
        cell.setCellStyle(style);        
		/*
		 * cell=rowHead.createCell(9); cell.setCellValue("NO. OF NOTSHEET");
		 * cell.setCellStyle(style); cell=rowHead.createCell(10);
		 * cell.setCellValue("NO. OF CROS."); cell.setCellStyle(style);
		 * cell=rowHead.createCell(11); cell.setCellValue("NO. OF A3 PAGES");
		 * cell.setCellStyle(style); cell=rowHead.createCell(12);
		 * cell.setCellValue("NO. OF A2 PAGES"); cell.setCellStyle(style);
		 * cell=rowHead.createCell(13); cell.setCellValue("NO. OF A1 PAGES");
		 * cell.setCellStyle(style); cell=rowHead.createCell(14);
		 * cell.setCellValue("NO. OF A0 PAGES"); cell.setCellStyle(style);
		 * cell=rowHead.createCell(15); cell.setCellValue("TOTAL NO. OF PAGES");
		 * cell.setCellStyle(style);
		 */
        
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++; 
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpa_Fts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getWorkName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getContractorName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
			/*
			 * r++; row.createCell(r).setCellValue(records.get(j).getNoOfNoteSheet()); r++;
			 * 
			 * row.createCell(r).setCellValue(records.get(j).getNoOfCros()); r++;
			 * 
			 * row.createCell(r).setCellValue(records.get(j).getNoOfA0Pages()); r++;
			 * row.createCell(r).setCellValue(records.get(j).getNoOfA1Pages()); r++;
			 * row.createCell(r).setCellValue(records.get(j).getNoOfA2Pages()); r++;
			 * row.createCell(r).setCellValue(records.get(j).getNoOfA3Pages()); r++;
			 * row.createCell(r).setCellValue(records.get(j).getTotalNoOfPages());
			 */
            i++;
                
           
            
            
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	

	/*----------------------Land -----------------------------------*/
	public static void generateLandReport(ArrayList<Land> records,String location,HttpServletResponse response)
	{  
		System.out.println("generateLand Report:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		System.out.println("generateLand Report:2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("generateLand Report:3");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of Land Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);         
        cell.setCellValue("Date");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);        
        cell.setCellValue("Account No");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("File No");//2
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("File Subject");//3
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("File Type");//4
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Line");//5
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Opa/Fts No");//6
        cell.setCellStyle(style);        
        cell=rowHead.createCell(8);
        cell.setCellValue("Village");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(9);
        cell.setCellValue("Year");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(10);
        cell.setCellValue("No_Of_Notsheet");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(11);
        cell.setCellValue("No_Of_Cos");
        cell.setCellStyle(style);  
        cell=rowHead.createCell(12);
        cell.setCellValue("No_Of_A3");
        cell.setCellStyle(style);
        cell=rowHead.createCell(13);
        cell.setCellValue("No_Of_A2");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(14);
        cell.setCellValue("No_Of_A1");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(15);
        cell.setCellValue("No_Of_A0");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(16);
        cell.setCellValue("Total_Pages");
        cell.setCellStyle(style);  
        cell=rowHead.createCell(17);
        cell.setCellValue("Servey_Letter");
        cell.setCellStyle(style);
        cell=rowHead.createCell(18);
        cell.setCellValue("Notifection");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(19);
        cell.setCellValue("Morgeg_Letter");
        cell.setCellStyle(style);  
        cell=rowHead.createCell(20);
        cell.setCellValue("Map_11");
        cell.setCellStyle(style);
      
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getDate());
            r++; 
            row.createCell(r).setCellValue(records.get(j).getAccountNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFilesub());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileType());
            r++;
            row.createCell(r).setCellValue(records.get(j).getLine());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getOpaFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getVillage());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getNo_of_notsheet());
            r++;    
            row.createCell(r).setCellValue(records.get(j).getNo_of_cos());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_of_a3());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_of_a2());
            r++;    
            row.createCell(r).setCellValue(records.get(j).getNo_of_a1());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_of_a0());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTotal_pages());
            r++;    
            row.createCell(r).setCellValue(records.get(j).getServey_letter());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNotifection());
            r++;
            row.createCell(r).setCellValue(records.get(j).getMorgeg_letter());
            r++;    
            row.createCell(r).setCellValue(records.get(j).getMap_11());
            i++;      
           
            
            
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	/*----------------------Marketing -----------------------------------*/
	public static void generateMarketingReport(ArrayList<Marketing> records,String location,HttpServletResponse response)
	{  
		System.out.println("generateMarketing Report:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		System.out.println("generateMarketing Report:2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("generateMarketing Report:3");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of Marketing Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);         
        cell.setCellValue("Date");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);        
        cell.setCellValue("Lot_No");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Clerk_Name");//2
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("File_No");//3
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Department");//4
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("FTS_NO_OPA_NO");//5
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Applicant_Name");//6
        cell.setCellStyle(style);        
        cell=rowHead.createCell(8);
        cell.setCellValue("Year");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(9);
        cell.setCellValue("No_Of_NoteSheet");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(10);
        cell.setCellValue("No_Of_Cros");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(15);
        cell.setCellValue("Opt_Name");
        cell.setCellStyle(style);  
        cell=rowHead.createCell(16);
        cell.setCellValue("Total_No_Of_Pages");
        cell.setCellStyle(style);
      
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getDate());
            i++; 
            row.createCell(r).setCellValue(records.get(j).getLot_No());
            r++;
            row.createCell(r).setCellValue(records.get(j).getClerk_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFile_No());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFts_No_Opa_No());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getApplicant_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_NoteSheet());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getNo_Of_Cros());
            r++;    
            row.createCell(r).setCellValue(records.get(j).getOpt_Name());
            r++;
            
            row.createCell(r).setCellValue(records.get(j).getTotal_No_Of_Pages());
            i++;      
           
            
            
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	/*----------------------Health -----------------------------------*/
	public static void generateHealthReport(ArrayList<Health> records,String location,HttpServletResponse response)
	{  
		System.out.println("generateHealth Report:1");
		HSSFWorkbook workBook=new HSSFWorkbook();
		System.out.println("generateHealth Report:2");
        HSSFSheet sheet=workBook.createSheet("Records Report");
        System.out.println("generateHealth Report:3");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of Health Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("Sno");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);         
        cell.setCellValue("Category");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);        
        cell.setCellValue("ContractorName");//1
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Department");//2
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("FileNo");//3
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Opa_Fts");//5
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Sector");//6
        cell.setCellStyle(style);        
        cell=rowHead.createCell(7);
        cell.setCellValue("WorkName");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(8);
        cell.setCellValue("Year");
        cell.setCellStyle(style);        
        cell=rowHead.createCell(9);
        cell.setCellValue("ContrName");
        cell.setCellStyle(style);  
        cell=rowHead.createCell(10);
        cell.setCellValue("Scheme");
        cell.setCellStyle(style);
        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++; 
            row.createCell(r).setCellValue(records.get(j).getContractorName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpa_fts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getWorkName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;    
            row.createCell(r).setCellValue(records.get(j).getContrName());
            r++;  
            row.createCell(r).setCellValue(records.get(j).getScheme());
            i++;
           
            
            
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	
	
	
	/*---------------------------New Departments----------------------------------*/
	



	
	
	
	
	public static void generateHRReport(ArrayList<HR> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of HR Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Line No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Date");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("FileNo.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("File code");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("File Subjects");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Opa/Fts");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("No. Of Noteeing");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("No. Of Coss");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("Total No. Of Pages");
        cell.setCellStyle(style);
      

        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getLine_No());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDate());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileCode());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFile_Subject());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpaFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_Noteeing());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNop_Of_Coss());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTotal_No_Of_Pages());
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	public static void generatePlanning2Report(ArrayList<Planning2> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of Planning2 Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Date");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Lot No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Clerk Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Type Of Files");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("  Bp No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Allotment No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Applicant Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("Plot No./House No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("Plot Size(Sqm)");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("Block No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(12);
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(13);
        cell.setCellValue("Fts No./Opa No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(14);
        cell.setCellValue("No. Of NotSheet");
        cell.setCellStyle(style);
        cell=rowHead.createCell(15);
        cell.setCellValue("No. Of Cross");
        cell.setCellStyle(style);
        cell=rowHead.createCell(16);
        cell.setCellValue("No. Of A3");
        cell.setCellStyle(style);
        cell=rowHead.createCell(17);
        cell.setCellValue("No. Of A2");
        cell.setCellStyle(style);
        cell=rowHead.createCell(18);
        cell.setCellValue("No. Of A1");
        cell.setCellStyle(style);
        cell=rowHead.createCell(19);
        cell.setCellValue("No. Of A0");
        cell.setCellStyle(style);
        cell=rowHead.createCell(15);
        cell.setCellValue(" Total No. Of Pages");
        cell.setCellStyle(style);
        

        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getDate());
            r++;
            row.createCell(r).setCellValue(records.get(j).getLot_No());
            r++;
            row.createCell(r).setCellValue(records.get(j).getClerk_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTypes_Of_Files());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getBP_No());
            r++;
            row.createCell(r).setCellValue(records.get(j).getAllotmentNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getApplicantName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPlotNo_HouseNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getPlot_Size());
            r++;
            row.createCell(r).setCellValue(records.get(j).getBlock_No());

            r++;
            row.createCell(r).setCellValue(records.get(j).getSecotr());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpaFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_NoteSheet());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_Cros());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A3());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A2());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A1());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A0());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTotal_No_Of_Pages());
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	public static void generateEM2Report(ArrayList<EM2> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of EM2 Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Opa/Fts");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Name Of Work");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Contractor Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("File No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue(" No. Of NotSheet");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("No. Of Cros.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("No. Of A3 Pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(12);
        cell.setCellValue("No. Of A2 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(13);
        cell.setCellValue("No. Of A1 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(14);
        cell.setCellValue("No. Of A0 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(15);
        cell.setCellValue("Total No. Of Pages");
        cell.setCellStyle(style);
       
        

        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpaFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getName_Of_Work());
            r++;
            row.createCell(r).setCellValue(records.get(j).getContractor_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFile_number());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_NoteSheet());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_Cros());

            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A3_PAGES());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNO_OF_A2_PAGES());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNO_OF_A1_PAGES());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNO_OF_A0_PAGES());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTotal_No_Of_Pages());
          
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	public static void generateEM3Report(String department,ArrayList<EM3> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of"+department+" Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Opa/Fts");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Name Of Work");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Contractor Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("File No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue(" No. Of NotSheet");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("No. Of Cros.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("No. Of A3 Pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(12);
        cell.setCellValue("No. Of A2 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(13);
        cell.setCellValue("No. Of A1 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(14);
        cell.setCellValue("No. Of A0 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(15);
        cell.setCellValue("Total No. Of Pages");
        cell.setCellStyle(style);
       
        

        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpa_Fts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getWorkName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getContractorName());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNo());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNoOfNoteSheet());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNoOfCros());

            r++;
            row.createCell(r).setCellValue(records.get(j).getNoOfA0Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNoOfA1Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNoOfA2Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNoOfA3Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTotalNoOfPages());
          
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	
	public static void generateProjectTechReport(ArrayList<ProjectTech> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of ProjectTech Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("Line No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("Date");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("Sector");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("Category");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("Opa/Fts");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("Name Of Work");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("Contractor Name");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("Department");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("File Number");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("Year");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("No. Of NotSheet");
        cell.setCellStyle(style);
        cell=rowHead.createCell(12);
        cell.setCellValue("No. Of Cros.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(13);
        cell.setCellValue("No. Of A3 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(14);
        cell.setCellValue("No. Of A2 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(15);
        cell.setCellValue("No. Of A1 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(16);
        cell.setCellValue("No. Of A0 pages");
        cell.setCellStyle(style);
        cell=rowHead.createCell(17);
        cell.setCellValue("Total No. Of pages");
        cell.setCellStyle(style);       

        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getLine_No());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDate());
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpaFts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getName_Of_Work());
            r++;
            row.createCell(r).setCellValue(records.get(j).getContractor_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFileNumber());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_NoteSheet());

            r++;
            row.createCell(r).setCellValue(records.get(j).getNO_Of_Cros());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A3_Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A2_Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A1_Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A0_Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getTotal_No_Of_Pages());
          
            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}
	
	public static void generateUEReport(ArrayList<UE> records,String location,HttpServletResponse response)
	{
		HSSFWorkbook workBook=new HSSFWorkbook();
        HSSFSheet sheet=workBook.createSheet("Records Report");
        HSSFFont titleFont=workBook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)16);
        HSSFFont font=workBook.createFont();
        font.setBold(true);
        HSSFCellStyle titleStyle=workBook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFCellStyle style=workBook.createCellStyle();
        style.setFont(font);
        style.setLocked(true);
        HSSFRow titleRow=sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,11));
        sheet.addMergedRegion(new CellRangeAddress(1,1,4,10));
        HSSFCell titleCell=titleRow.createCell(3);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Greater Noida Industrial Development Authority");
        titleRow=sheet.createRow(1);
        titleCell=titleRow.createCell(4);
        titleFont.setFontHeightInPoints((short)13);
        titleStyle.setFont(titleFont);
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue("Software generated report of UE Department");
        HSSFRow rowHead=sheet.createRow(2);
        HSSFCell cell=rowHead.createCell(0);
        cell.setCellValue("S.No");
        cell.setCellStyle(style);
        cell=rowHead.createCell(1);
        cell.setCellValue("SECTOR NAME");
        cell.setCellStyle(style);
        cell=rowHead.createCell(2);
        cell.setCellValue("CATEGORY");
        cell.setCellStyle(style);
        cell=rowHead.createCell(3);
        cell.setCellValue("OPA/FTS No.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(4);
        cell.setCellValue("NAME OF WORK");
        cell.setCellStyle(style);
        cell=rowHead.createCell(5);
        cell.setCellValue("CONTRACTOR NAME");
        cell.setCellStyle(style);
        cell=rowHead.createCell(6);
        cell.setCellValue("DEPARTMENT");
        cell.setCellStyle(style);
        cell=rowHead.createCell(7);
        cell.setCellValue("FILE NO.");
        cell.setCellStyle(style);
        cell=rowHead.createCell(8);
        cell.setCellValue("YEAR");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue(" NO_OF_NOTSHEET");
        cell.setCellStyle(style);
        cell=rowHead.createCell(10);
        cell.setCellValue("NO_OF_COS");
        cell.setCellStyle(style);
        cell=rowHead.createCell(11);
        cell.setCellValue("NO. OF A3 PAGES");
        cell.setCellStyle(style);
        cell=rowHead.createCell(12);
        cell.setCellValue("NO. OF A2 PAGES");
        cell.setCellStyle(style);
        cell=rowHead.createCell(13);
        cell.setCellValue("NO. OF A1 PAGES");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("NO. OF A0 PAGES");
        cell.setCellStyle(style);
        cell=rowHead.createCell(9);
        cell.setCellValue("TOTAL NO. OF PAGES");
        cell.setCellStyle(style);


        int r=0,i=3;
        for(int j=0;j<records.size();j++)
        {
        	r=0;
            HSSFRow row=sheet.createRow(i);
            row.createCell(r).setCellValue(i-2);
            r++;
            row.createCell(r).setCellValue(records.get(j).getSector());
            r++;
            row.createCell(r).setCellValue(records.get(j).getCategory());
            r++;
            row.createCell(r).setCellValue(records.get(j).getOpa_fts());
            r++;
            row.createCell(r).setCellValue(records.get(j).getName_Of_Work());
            r++;
            row.createCell(r).setCellValue(records.get(j).getContractor_Name());
            r++;
            row.createCell(r).setCellValue(records.get(j).getDepartment());
            r++;
            row.createCell(r).setCellValue(records.get(j).getFile_Number());
            r++;
            row.createCell(r).setCellValue(records.get(j).getYear());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_Cros());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A3_Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A2_Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A1_Pages());
            r++;
            row.createCell(r).setCellValue(records.get(j).getNo_Of_A0_Pages());

            r++;
            row.createCell(r).setCellValue(records.get(j).getTotal_NO_Of_Pages());

            i++;
        }
        try
        {
        	FileOutputStream out=new FileOutputStream(location+"Report.xls");
        	workBook.write(out);
        	out.close();
        	workBook.close();
        	downloadFile(response,"Report.xls",location,false,"","");
        }
        catch(Exception e)
        {e.printStackTrace();}
	}  
}