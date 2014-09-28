# Kết nối cơ sở dữ liệu SQLite trong Android

## SQLite là gì ?
  SQLite là một thư viện thực thi các chức năng của một database engine với đặc điểm giống như một phần mềm portable, không cần cài đặt, không cần cấu hình, không cần server, những điểm này rất khác so với việc sử dụng SQL Server hoặc Oracle, nhưng nó vẫn có transaction để đảm bảo tính toàn vẹn và an toàn trong quá trình thao tác dữ liệu.
  SQLite là hệ thống cơ sở dữ liệu quan hệ nhỏ gọn, hoàn chỉnh, có thể cài đặt bên trong các
trình ứng dụng khác. Vì vậy nó là một loại cơ sở dữ liệu được sử dụng trên mobile trong đó có Android.

## Thao tác và các thành phần để thao tác với SQLite
  Cũng tương tự như các hệ cơ sở dữ liệu khác, SQLite cũng thực hiện được các thao tác như: 
Tạo 1 cơ sở dữ liệu
Kết nối, mở cơ sở dữ liệu đó
Thêm, sửa, xoá giá trị vào trong các bảng của cơ sở dữ liệu
Truy vấn lấy ra các dữ liệu trong bảng
Đóng kết nối với cơ sở dữ liệu

### Tạo và cập nhật cơ sở dữ liệu
    Tạo một lớp, lớp này sử dụng phương thức _super()_ của lớp kế thừa _SQLiteOpenHelper_ để tạo ra 
  một cơ sở dữ liệu, với database name và phiên bản hiện tại của cơ sở dữ liệu.
  ```java
  super(context, DATABASE_NAME, null, DATABASE_VERSION);
  ```
    Trong lớp này chúng ta cần override các phương thức sau để tạo và cập nhật cơ sở dữ liệu.
    - **onCreate():** được gọi bởi framework, nếu database được truy cập nhưng
    chưa được tạo ra.
    -**onUpgrade():** được gọi, nếu phiên bản database được làm mới trong ứng dụng của bạn.
    Phương thức này cho phép cập nhật một lược đồ cơ sở dữ liệu đã tồn tại hoặc drop một 
    cơ sở dữ liệu và tạo lại nó thông qua phương thức `onCreate()`
      Cả 2 phương thức trên đều nhận một đối tượng `SQLiteDatabase` như một tham số đại diện 
    của cơ sở dữ liệu.
      `SQLiteOpenHelper` cung cấp 2 phương thức là `getReadableDatabase()` và `getWriteableDatabase`
    để truy cập vào đối tượng `SQLiteDatabase` trong chế độ đọc hoặc ghi.

### SQLiteDatabase
    `SQLiteDatabase` là lớp cơ sở để làm việc với một cơ sở dữ liệu SQLite trong Android và cung cấp 
  các phương thức `open`, `query`, `update` và `close` cơ sở dữ liệu.
    Cụ thể hơn, `SQLiteDatabase` cung cấp các phương thức `insert()`, `update()` và `delete()`
    Ngoài ra còn có phương thức `execSQL()` cho phép thực thi các câu lệnh SQL.
    Doi tượng `ContentValue` cho phép định nghĩa một cặp key/values. Trong đó, chúng ta dùng 
  `key` để thể hiện tên cột trong bảng cơ sở dữ liệu và `value` thể hiện nội dung trong cột đó của
  một bản ghi. `ContentValue` có thể được sử dụng để inserts và updates cho nội dung của database.
    Chúng ta có thể tạo các truy vẫn thông qua phương thức `rawQuery()` và `query()` hoặc qua
  class `SQLiteQueryBuilder`.
  - _rawQuery()_ trực tiếp nhận một câu truy SQL select như một đầu vào
    ```java
      Cursor cursor = getReadableDatabase().
        rawQuery("select * from todo where _id = ?", new String[] { id });
    ```
  - _query()_ cung cấp một cấu trúc để xác định các truy vấn SQL
    ```java
      return database.query(DATABASE_TABLE, 
        new String[] { KEY_ROWID, KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION }, 
        null, null, null, null, null);
    ```
  - _SQLiteQueryBuilder_ là một lớp hỗ trợ để chúng ta xây dựng các truy vấn SQL
  
  **Table 1. Parameters of the query() method**
  
  |**Tham số**| **Mô tả** |
  |:--------:|:--------|
  |String dbName| Tên bảng để biên dịch |
  |String[] columnNames| Một danh sách các cột của bảng để trả về|
  |String whereClause|Trường hợp mệnh đề, tức là bộ lọc cho việc chọn của dữ liệu, `null` là chọn tất cả dữ liệu|
  |String[] groupBy|Bộ lọc nhóm các hàng, nếu `null` thì các hàng sẽ không được nhóm lại|
  |String[] having|điều kiện lọc cho các nhóm, `null` có nghĩa là không lọc|
  |String[] orderBy|Sắp xếp thứ tự các hàng theo điều kiện, nếu `null` là không có thứ tự|

### Cusor
    Một truy vấn sẽ trả về một đối tượng `cursor`. Một `cursor` thể hiện kết quả của một câu truy vấn. 
  Để có được số lượng thành phần của kết quả câu truy vấn sử dụng phương thức `getCount()`
  Để có thể di chuyển dữ liệu giữa các hàng, sử dụng phương thức `moveToFirst()` và `moveToNext()`.
  Phương thức `isAfterLast()` kiểm tra nếu kết thúc của kết quả truy vấn đã đạt đến.
  `Curser` cung cấp phương thức `get*()`, ví dụ: `getInt(columnIndex)`, để truy cập dữ liệu cột cho vị trí hiện tại của kết quả. `columnIndex` là chỉ số của cột đang truy cập.


## Ví dụ:
  Để thực hiện các thao tác trên ta đi vào một ví dụ đơn giản: tạo cơ sở dữ liệu với bảng **”Status”** bao gồm các thuộc tính `id`, `content`. Sau đó chúng ta sẽ thực hiện các thao tác đối với sơ cở dữ liệu này như thêm, sửa, xoá, truy vấn, đóng cơ sở dữ liệu.

**Bước 1:**
  Khởi tạo project:   
- Application Name: "DemoSQLite"
- Project Name: "DemoSQLite"
- Packetname: "com.trananh.example"
 Folder project như sau:
![folder](https://github.com/nvtanh/trainning_android/blob/master/doc/Folder_SQLite.PNG)


**Bước 2:**
  Tạo model `Status` với file name `status.java`

```java
package com.trananh.example;

public class Status {
	public int mId;
	public String mContent;
	
	public Status() {
	}
	
	public Status(int id, String title, String content) {
		mId = id;
		mContent = content;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmContent() {
		return mContent;
	}

	public void setmContent(String mContent) {
		this.mContent = mContent;
	}
}
```
**Bước 3:**
  Tạo class `StatusColumns` để chứa các thông số tên bảng và tên các cột của bảng dữ liệu `Status`
  ```java
package com.trananh.database;

public class StatusColumns {
	  public static final String TABLE_STATUS = "status";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_CONTENT = "content";
}
  ```
**Bước 4:**
    Tạo class `MySQLiteHelper` để tạo và cập nhật database trong ứng dụng Android.
    
  ```java
package com.trananh.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.trananh.database.StatusColumns;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "status.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table "
		      + StatusColumns.TABLE_STATUS + "(" + StatusColumns.COLUMN_ID
		      + " integer primary key autoincrement, " + StatusColumns.COLUMN_CONTENT
		      + " text not null);";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + StatusColumns.TABLE_STATUS);
	    onCreate(db);
	  }
}
```
  **Bước 5:**
    Tạo class _StatusDataSource_. Trong class này chúng ta viết các hàm để mở, đóng kết nối và hỗ trợ thêm mới,
  sửa, xoá đối tượng vào cơ sở dữ liệu, lấy dữ liệu ra từ bảng trong cơ sở dữ liệu.
  
  ```java
package com.trananh.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import com.trananh.database.StatusColumns;
import com.trananh.example.Status;

public class StatusDataSource {
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { StatusColumns.COLUMN_ID,
	    StatusColumns.COLUMN_CONTENT };

	  public StatusDataSource(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Status createStatus(String status) {
		    ContentValues values = new ContentValues();
		    values.put(StatusColumns.COLUMN_CONTENT, status);
		    long insertId = database.insert(StatusColumns.TABLE_STATUS, null,
		        values);
		    Cursor cursor = database.query(StatusColumns.TABLE_STATUS,
		        allColumns, StatusColumns.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Status newStatus = cursorToStatus(cursor);
		    cursor.close();
		    return newStatus;
		  }

	  public void deleteStatus(Status status) {
	    int id = status.getmId();
	    System.out.println("Status deleted with id: " + id);
	    database.delete(StatusColumns.TABLE_STATUS, StatusColumns.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Status> getAllStatus() {
	    List<Status> statuses = new ArrayList<Status>();

	    Cursor cursor = database.query(StatusColumns.TABLE_STATUS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Status status = cursorToStatus(cursor);
	      statuses.add(status);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return statuses;
	  }

	  private Status cursorToStatus(Cursor cursor) {
	    Status status = new Status();
	    status.setmId(cursor.getInt(cursor.getColumnIndex(StatusColumns.COLUMN_ID)));
	    status.setmContent(cursor.getString(cursor.getColumnIndex(StatusColumns.COLUMN_CONTENT)));
	    return status;
	  }
}
  ```
  
  **Bước 6:**
    Tạo giao diện cho chương trình với file `activity_main.xml` để hiển thị danh sách các status
  và 2 button, một button để thêm bản ghi và một button để xoá đi bản ghi
  
  ```android
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical" >

    <LinearLayout
        android:id="@+id/group"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" >

        <Button
            android:id="@+id/add"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Add New" 
            android:onClick="onClick"/>

        <Button
            android:id="@+id/delete"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Delete First" 
            android:onClick="onClick"/>
        
    </LinearLayout>

    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/hello" />

</LinearLayout> 
  ```
  
  **Bước 7:**
  Tạo Activity `MainActivity` tương ứng với `activity_main.xml`
  
```android
package com.trananh.example;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.trananh.database.StatusDataSource;;

public class MainActivity extends ListActivity  {
	private StatusDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource = new StatusDataSource(this);
	    datasource.open();

	    List<Status> values = datasource.getAllStatus();

	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Status> adapter = new ArrayAdapter<Status>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	}

	public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<Status> adapter = (ArrayAdapter<Status>) getListAdapter();
	    Status status = null;
	    switch (view.getId()) {
	    case R.id.add:
	      String[] statuses = new String[] { "Cool", "Very nice", "Hate it" };
	      int nextInt = new Random().nextInt(3);
	      // save the new comment to the database
	      status = datasource.createStatus(statuses[nextInt]);
	      adapter.add(status);
	      break;
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	    	  status = (Status) getListAdapter().getItem(0);
	        datasource.deleteStatus(status);
	        adapter.remove(status);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }
}
```

### Kết quả
Chương trình hiển thị danh sách các status, và 1 button add, 1 button delete.
Khi ấn button add thì random tạo ra một status, và ấn nút delete thì sẽ xoá đi bản ghi đầu tiên.
![result](https://github.com/nvtanh/trainning_android/blob/master/doc/main_SQLite.PNG)
 
