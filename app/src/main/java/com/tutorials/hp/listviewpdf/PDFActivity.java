package com.tutorials.hp.listviewpdf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;

public class PDFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        //PDFVIEW SHALL DISPLAY OUR PDFS
        PDFView pdfView= (PDFView) findViewById(R.id.pdfView);
        //SCROLLBAR TO ENABLE SCROLLING
        ScrollBar scrollBar = (ScrollBar) findViewById(R.id.scrollBar);
        pdfView.setScrollBar(scrollBar);
        //VERTICAL SCROLLING
        scrollBar.setHorizontal(false);
        //SACRIFICE MEMORY FOR QUALITY
        //pdfView.useBestQuality(true)

        //UNPACK OUR DATA FROM INTENT
        Intent i=this.getIntent();
        String path=i.getExtras().getString("PATH");

        //GET THE PDF FILE
        File file=new File(path);

        if(file.canRead())
        {
            //LOAD IT
            pdfView.fromFile(file).defaultPage(1).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(PDFActivity.this, String.valueOf(nbPages), Toast.LENGTH_LONG).show();
                }
            }).load();

        }

    }
}
