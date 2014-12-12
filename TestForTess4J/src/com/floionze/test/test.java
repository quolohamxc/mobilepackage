package com.floionze.test;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

	/**
	 * this class test the ability of Tess4J.
	 * @author Floion Z
	 * @version 1.0 2014-09-30
	 */
public class test {
	/**
	 * 
	 * @param args
	 * 
	 */
	public static void main(String args[]) {
		File imageFile = new File("201409121004321979485488.jpg");
        Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	}
}
