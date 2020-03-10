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

import org.springframework.web.bind.annotation.GetMapping;
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

//  class OrignailFile {
//    String file;
//
//  
//    public String getFile() {
//      return file;
//    }
//   
//    public void setFile(String file) {
//      this.file = file;
//    }
//    
//  }
  @GetMapping(value = "/pdf")
  public void generating(@RequestParam String file) {
    PDFileValidation.validateInputFile(file);
    String filename = getFileNameWithouttExtension(getFileName(file));
    
    GenericConvertor genericConv = new GenericConvertor(); 
    
    genericConv.convert(file,filename + ".pdf");    
    
//    
//    WordToPdf wordToPdf = new WordToPdf(); 
//    wordToPdf.setPdfExportOptimizeFor(PdfExportOptimizeFor.PDF_EXPORT_OPTIMIZE_FORPRINT);
    
   
  }
  
  public static String getFileNameWithouttExtension(String fileName) {
    return fileName.substring(fileName.lastIndexOf(".") + 1);    
  }
  
  public static String getFileName(String fileURL) {
    if (fileURL.lastIndexOf("/") > -1) {
      return fileURL.substring(fileURL.lastIndexOf("/") + 1);
    } else {
      return fileURL;
    }
  }
}
