package com.example.kojar.rp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import Datenhaltung.Buffer;


public class Informationen extends AppCompatActivity {

    private Buffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informationen);
        buffer = buffer.getInstance();
        webview();
    }

    public void webview() {
       WebView w = (WebView) findViewById(R.id.webViewInfos);
        WebSettings websettings = w.getSettings();
        websettings.setDefaultFontSize(12);
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setBuiltInZoomControls(true);
        w.loadDataWithBaseURL("http://bar", "<script type='text/x-mathjax-config'>"
                + "MathJax.Hub.Config({ showMathMenu: false, "
                + "jax: ['input/TeX','output/HTML-CSS'], "
                + "extensions: ['tex2jax.js'], "
                + "TeX: { extensions: ['AMSmath.js','AMSsymbols.js',"
                + "'noErrors.js','noUndefined.js'] } "
                + "});</script>"
                + "<script type='text/javascript' "
                + "src='file:///android_asset/MathJax/MathJax.js'"
                + "></script><span id='text'>" + schritteZuruckGeben() + "</span> <span id='math'></span>", "text/html", "utf-8", "");
        w.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);");
    }

    public String schritteZuruckGeben() {
        return buffer.infoListeZurueckgeben();
    }
}
