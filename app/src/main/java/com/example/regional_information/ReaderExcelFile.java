package com.example.regional_information;

import android.content.res.AssetManager;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ReaderExcelFile {

    String wayToFile;

   public ReaderExcelFile(String way){
        this.wayToFile = way;
    }

    public void getAllCoordinates() throws IOException {
        InputStream myInput;

        AssetManager assetManager = MainActivity.getInstance().getApplicationContext().getAssets();

        myInput = assetManager.open(wayToFile);

        POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

        HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

        HSSFSheet mySheet = myWorkBook.getSheetAt(0);

    }

}
