package com.leancloud_text;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.jakewharton.rxbinding2.view.RxView;
import com.leancloud_text.obj.DialogBox;
import com.leancloud_text.obj.ToastUnity;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.functions.Consumer;

public class RegisterPage extends AppCompatActivity {

    public TextInputLayout til1,til2;
    public TextInputEditText ed1;
    public EditText ed2;
    private DialogBox dialogBox;

    private Button bt1,bt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        fid();
        vitin();




    }

    private void vitin() {
        til1.setHint("帳號");
        til2.setHint("密碼");
        //设置可以计数
        til1.setCounterEnabled(true);
        til2.setCounterEnabled(true);

        //计数的最大值
        til1.setCounterMaxLength(10);
        til2.setCounterMaxLength(5);

        RxView.clicks(bt1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object arg0) throws Exception {
                        attemptRegister();
                    }
                });
        RxView.clicks(bt2)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object arg0) throws Exception {

                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser!=null)
                            Log.i("成功",currentUser.getObjectId());
                            ToastUnity.ShowTost(RegisterPage.this,currentUser.getObjectId());
                    }
                });
    }



    private void fid() {
        til1 = (TextInputLayout) findViewById(R.id.til1);
        til2 = (TextInputLayout)findViewById(R.id.til2);
        ed1 = (TextInputEditText)findViewById(R.id.ed1);
        ed2 = (EditText)findViewById(R.id.ed2);
        dialogBox = new DialogBox(this);
        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);


    }

    private void attemptRegister()
    {
        til1.setError(null);
        til2.setError(null);
        if(!TextUtils.isEmpty(ed1.getText().toString())) {
            if (!TextUtils.isEmpty(ed2.getText().toString())  && isPasswordValid(ed2.getText().toString()))
            {
                dialogBox.ShoDi();
                AVUser user = new AVUser();// 新建 AVUser 对象实例
                user.setUsername(ed1.getText().toString());// 设置用户名
                user.setPassword(ed2.getText().toString());// 设置密码
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            // 注册成功，把用户对象赋值给当前用户 AVUser.getCurrentUser()
                            Log.i("成功","成功");

                        } else {
                            // 失败的原因可能有多种，常见的是用户名已经存在。
                            Log.i("失败",e.getCode()+"");
                            if (e.getCode()==202){
                                DialogBox.getAlertDialog1(RegisterPage.this,"錯誤","用戶名已被使用");
                            }
                            ToastUnity.ShowTost(RegisterPage.this, e.getMessage());
                        }
                        dialogBox.DisDi();
                    }
                });
            }
            else{
                til2.setError("密碼長度須大於4");
            }
        }
        else {
            til1.setError("未填帳號");
        }
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



    //--驗證字串--
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
