package com.example.news;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.news.presenter.DetailNewsPresenter;
import com.example.news.view.NewsDetailView;

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailView {

    private ProgressBar progressBar;
    private WebView webView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_detail);

        progressBar = findViewById(R.id.detailProgress);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        toolbar = findViewById(R.id.detail_toolbar);

        setSupportActionBar(toolbar);


        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(progress);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient());
        DetailNewsPresenter presenter = new DetailNewsPresenter(this);
        presenter.showDetail();

    }

    //判断返回
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                // System.exit(0);
            }


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showDetail() {
        String url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }

    @Override
    public void showFailure() {

    }
    //
    //    @Override
    //    public boolean onOptionsItemSelected(MenuItem item) {
    //        switch (item.getItemId()){
    //            case android.R.id.home:
    //                Intent intent=new Intent(this,MainActivity.class);
    //                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    //                startActivity(intent);
    //               // finish();
    //                return true;
    //                default:
    //                    return super.onOptionsItemSelected(item);
    //        }
    //
    //    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);
                break;
            case R.id.other:
                Toast.makeText(this, "other", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (data !=null) {
                    Uri result = data.getData();

                    String phoneName = getPhoneContacts(result);
                    String smsContent = getIntent().getStringExtra("url");

                    sendSMS(phoneName, smsContent);
                }else {
                  //  startActivity(new Intent(this,MainActivity.class));
                    onBackPressed();
                }

        }
    }

    private String getPhoneContacts(Uri contactId) {
        Cursor cursor = null;
        String phoneName ;
        String phoneNum="" ;
        String phoneID ;
        try {
            cursor = getContentResolver().query(contactId, null, null, null, null);
            if (cursor.moveToNext()) {
                phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                phoneID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + phoneID, null, null);
                while (c.moveToNext()) {
                    phoneNum = c.getString(c.getColumnIndex("data1"));
                }
            } else {
                Toast.makeText(this, "找不到该联系人", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return phoneNum;
    }

    private void sendSMS(String phoneNum, String smsBody) {
        Uri smsToUri = Uri.parse("smsto:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        startActivity(intent);

    }
}