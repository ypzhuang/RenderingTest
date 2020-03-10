/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  HPTiger
 * PROJECT       :  IotHub :: Iot Hub
 * FILENAME      :  SerialExample.java
 *
 * This file is part of the HPTiger project. More information about
 * this project can be found here:  https://www.bdease.com.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2019 - 2019 bdease
 * %%
 * This program is the property of bdease
 * #L%
 */
package com.hptiger.rendering.controller;

import java.io.File;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.zeonpad.pdfcovertor.GenericConvertor;
import com.zeonpad.pdfcovertor.PDFileValidation;
import com.zeonpad.pdfcovertor.PdfExportOptimizeFor;
import com.zeonpad.pdfcovertor.WordToPdf;

/**
 * @author ypzhuang  2020-03-10
 * TODO Descirbe the function of this class
 */

@RestController
@RequestMapping("/api/v1/rendering")
public class RenderingController {


  @PostMapping(value = "/pdf")
  public void generating(@RequestBody OrignailFile file) {
    PDFileValidation.validateInputFile(file.getFile());
    String filename = getFileNameWithouttExtension(getFileName(file.getFile()));
    
    GenericConvertor genericConv = new GenericConvertor(); 
    
    String userHome =  System.getProperty("user.home");
    String output = userHome + File.separator + filename + "." + file.outFormat;
    System.out.println(output);
    try {
      genericConv.convert(file.getFile(),output);
    } catch(Exception e) {
      e.printStackTrace();
    }
    

//    
//    WordToPdf wordToPdf = new WordToPdf(); 
//    wordToPdf.setPdfExportOptimizeFor(PdfExportOptimizeFor.PDF_EXPORT_OPTIMIZE_FORPRINT);
    
   
  }
 
  public static String getFileNameWithouttExtension(String fileName) {
    return fileName.substring(0,fileName.lastIndexOf("."));    
  }
  
  public static String getFileName(String fileURL) {
    if (fileURL.lastIndexOf("/") > -1) {
      return fileURL.substring(fileURL.lastIndexOf("/") + 1);
    } else if (fileURL.lastIndexOf("\\") > -1) {
      return fileURL.substring(fileURL.lastIndexOf("\\") + 1);
    } else {
      return fileURL;
    }
  }
}

class OrignailFile {
  String file;
  String outFormat;
 
  public OrignailFile() {
    super();
  }

  public String getFile() {
    return file;
  }
 
  public void setFile(String file) {
    this.file = file;
  }

  /**
   * @return the outFormat
   */
  public String getOutFormat() {
    return outFormat;
  }

  /**
   * @param outFormat the outFormat to set
   */
  public void setOutFormat(String outFormat) {
    this.outFormat = outFormat;
  }

  @Override
  public String toString() {
    return "OrignailFile [file=" + file + ", outFormat=" + outFormat + "]";
  }
  
  
  
}
