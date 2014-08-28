# Intent trong android

## Intent là gì?
  Các ứng dụng Android nói chung thường không quá đơn giản khi chúng chỉ hoạt động trên 1 activity, 
toàn bộ các hoạt động xuất/nhập, xử lý dữ liệu không chỉ diễn ra chỉ trên đúng 1 activity đó. 
Mà các ứng dụng Android thực tế thường có một số activity, mỗi activity đó đều có thể hoạt động độc lập
với nhau. Tuy nhiên chúng ta có thể liên kết, gắn kết chúng thành một hệ thống nhất để tạo ra được những
ứng dụng phức tạp hơn. Và Android đã cung cấp cho chúng ta một khái niệm đó là "Intent". Android sử dụng 
Intents như là những thông điệp bất đồng bộ để cho phép các thành phần ứng dụng giao tiếp được với nhau: 
chúng có thể yêu cầu chức năng, trao đổi dữ liệu, tương tác với các ứng dụng khác.

## Các loại Intent trong Android
  Trong Android thì Intent được chia thành 2 dạng chính, đó là:
- **Explicit Intent:** là loại Intent được khai báo 1 cách tường minh, chính xác thành phần sẽ nhận và xử lý 
Intent bằng cách thiết lập giá trị phù hợp qua _setComponent(Componentname)_ hoặc _setClass(Context, Class)_. 
Explicit Intent thường được sử dụng để khởi chạy các activity trong cùng 1 ứng dụng.

  **Ví dụ:** 
```android
Intent action_list = new Intent(this, ListBook.class);
startActivity(action_list);
```

- **Implicit Intent:** là loại Intent không cần chỉ rõ component xử lý mà chỉ cần cung cấp đủ các thông tin 
cần thiết để hệ thống có thể tự xác định xem nên dùng các thành phần(component) có sẵn nào để chạy là tốt nhất.

  **Ví dụ:** 
```android
Intent intent = new Intent(Intent.ACTION_DIAL, tel: 123);
startActivity(intent);
```

## Các thuộc tính của Intent
  Có 2 thuộc tính quan trọng trong Intent đó là _action_ và _data_:
- action: quy định hành động sẽ được thực hiện. VD: Action_view, action_call...
- data: quy định dữ liệu sẽ được xử lý trong action, thường được diễn tả là một Uri.
VD: tel:123 , "http://google.com”, ...

  **Ví dụ:**
```
ACTION_VIEW content://contacts/people/1 - Hiển thị thông tin về người với mã danh 1
ACTION_DIAL tel:123 - Hiển thị màn hình gọi với số gọi là 123
```

  Ngoài ra còn có 1 số thuộc tính như:
- category: bổ sung thêm thông tin cho các action của Intent. VD: CATEGORY_LAUNCHER thông báo sẽ thêm vào Launcher 
như là một ứng dụng top-level.
- type: định dạng kiểu của dữ liệu(chuẩn MIME) thường là tự xác định.
- component: chỉ rõ thành phần sẽ nhận và xử lý intent. Khi thuộc tính này được xác định thì các thuộc tính khác 
sẽ trở thành thuộc tính phụ.
- extras: chứa tất cả các cặp (key,value) do ứng dụng thêm vào để truyền qua Intent (cấu trúc Bundle)

  Một số action thường sử dụng trong Intent:

|**Tên thuộc tính**| **Mô tả** |
|:--------:|:--------|
|ACTION_ANSWER|Mở Activity để xử lý cuộc gọi tới, thường là Phone Dialer của Android|
|ACTION_CALL|mở 1 Phone Dialer (mặc định là PD của Android) và ngay lập tức thực hiện cuộc gọi dựa vào thông tin trong data URI|
|ACTION_DELETE|mở Activity cho phép xóa dữ liệu mà địa chỉ của nó chứa trong data URI|
|ACTION_DIAL|mở 1 Phone Dialer (mặc định là PD của Android) và điền thông tin lấy từ địa chỉ chứa trong data URI|
|ACTION_EDIT|mở 1 Activity cho phép chỉnh sửa dữ liệu mà địa chỉ lấy từ data URI|
|ACTION_SEND|mở 1 Activity cho phép gửi dữ liệu lấy từ data URI, kiểu của dữ liệu xác định trong thuộc tính type|
|ACTION_SENDTO|mở 1 Activity cho phép gửi thông điệp tới địa chỉ lấy từ data URI|
|ACTION_VIEW|action thông dụng nhất, khởi chạy activity thích hợp để hiển thị dữ liệu trong data URI|
|ACTION_MAIN|sử dụng để khởi chạy 1 Activity|

## Xây dựng một demo nhỏ về Intent
  Mô tả:
Chương trình bao gồm 2 Activity: một Activity dùng để nhập tài khoản và mật khẩu, sau đó ấn nút "Sign In" để đi đến Activity số 2.
Nếu tài khoản và mật khẩu đúng ở Activity số 2 sẽ hiện lên thông tin tài khoản, còn nếu tài khoản và mật khẩu sai thì sẽ hiện lên 
thông báo lỗi. Cơ chế gửi và khởi chạy Activity sử dụng thông qua Intent.

**Bước 1:**
  Khởi tạo project:   
- Application Name: "DemoIntent"
- Project Name: "DemoIntent"
- Packetname: "com.trananh.example"
- Activity số 1: MainActivity
- Activity số 2: Home
![Folder](https://github.com/nvtanh/trainning_android/blob/Task%232-IntentAndroid/doc/Folder.PNG)

**Bước 2:**
Tạo giao diện cho Activity số 1: activity_main.xml
Bao gồm:
- 2 "EditText" để nhập _ID_ và _Password_ cùng với 2 "TextView" tương ứng để hiển thị label cho chúng
- 1 "Button" để submit và chuyển sang Activity số 2

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:tools="http://schemas.android.com/tools"
  android:orientation="vertical"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  tools:context=".MainActivity" >

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/id"
    />
    
    <EditText
	   android:id="@+id/uid"
	   android:layout_width="match_parent"
       android:layout_height="wrap_content"
	   android:inputType="text"
	/>
    
    <TextView
       android:layout_width="match_parent"
     	android:layout_height="wrap_content"
       android:text="@string/password" 
    />
    <EditText
	    android:id="@+id/password"
	    android:layout_width="match_parent"
      	android:layout_height="wrap_content"
	    android:inputType="textPassword" 
    />
    
    <Button 
        android:id="@+id/btnLogin"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/login"/>

</LinearLayout>
```

**Bước 3:**
Tạo giao diện cho Activity số 2: home.xml
Chỉ có một TextView" để hiển thị thông báo khi người dùng nhập đúng hoặc sai _ID_ hoặc _Password_

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >
    
    <TextView
        android:id="@+id/notice"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
    />

</LinearLayout>
```

**Bước 4:**
Tạo controller cho Activity main: MainActivity.java với nội dung như sau:
Trong controller này ta sẽ tạo một Intent để chuyển thông tin của "ID" và "Password" nhập vào.
Sau đó sử dụng một biến BUndle để đóng gói thông tin đó trong Intent để chuyển sang Activity số 2(Home).

```java
package com.trananh.example;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText uid = (EditText) findViewById(R.id.uid);
		final EditText password = (EditText) findViewById(R.id.password);
		Button btnLogin = (Button) findViewById(R.id.btnLogin);
		
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String uidString = uid.getText().toString();
				String pass = password.getText().toString();
				
				Bundle sendBundle = new Bundle();
				sendBundle.putString("uid", uidString);
				sendBundle.putString("password", pass);
				
				Intent intent = new Intent(MainActivity.this, Home.class);
				intent.putExtras(sendBundle);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

```

**Bước 5:**
Tạo controller cho Activity main: Home.java với nội dung như sau:
Trong controller này chúng ta đơn giản chỉ là bắt thông tin Intent của MainActivity truyền sang.
Sau đó dùng, dữ liệu này để kiểm tra xem _ID_ và _Password_ nhập vào đã đúng chưa, sau đó hiện ra
thông báo tương ứng.

```java
package com.trananh.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        final TextView txtNotice = (TextView) findViewById(R.id.notice);
        
        Bundle receiveBundle = this.getIntent().getExtras();
        final String receiveUid = receiveBundle.getString("uid");
        final String receivePass = receiveBundle.getString("password");
        
        if( receiveUid.equals("trananh") && receivePass.equals("123456")){
        	txtNotice.setText("Chao mung " + receiveUid + " da dang nhap thanh cong");
        }else{
        	txtNotice.setText("ID hoac Password khong dung!!!!");
        }
	}
}

```

**Bước 6:** 
  Bổ sung thêm thông tin về component mới "Home" Activity vào AndroidManifest.xml như sau:
  
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.trananh.example"
    android:versionCode="1"
    android:versionName="1.0" >

    <uses-sdk
        android:minSdkVersion="8"
        android:targetSdkVersion="17" />

    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme" >
        <activity
            android:name="com.trananh.example.MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity 
            android:name=".Home">
        </activity>
    </application>

</manifest>
```

## Kết quả:
  Khi nhập đúng ID và Password:
  
![login ok](https://github.com/nvtanh/trainning_android/blob/Task%232-IntentAndroid/doc/login.PNG)

![success](https://github.com/nvtanh/trainning_android/blob/Task%232-IntentAndroid/doc/success.PNG)

  Khi nhập sai ID hoặc password:
  
![success](https://github.com/nvtanh/trainning_android/blob/Task%232-IntentAndroid/doc/login_failure.PNG)

![success](https://github.com/nvtanh/trainning_android/blob/Task%232-IntentAndroid/doc/failure.PNG)







