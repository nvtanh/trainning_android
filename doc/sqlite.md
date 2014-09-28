# Kết nối cơ sở dữ liệu SQLite trong Android

## SQLite là gì ?
  SQLite là một thư viện thực thi các chức năng của một database engine với đặc điểm giống như một phần mềm portable, không cần cài đặt, không cần cấu hình, không cần server, những điểm này rất khác so với việc sử dụng SQL Server hoặc Oracle, nhưng nó vẫn có transaction để đảm bảo tính toàn vẹn và an toàn trong quá trình thao tác dữ liệu.
  SQLite là hệ thống cơ sở dữ liệu quan hệ nhỏ gọn, hoàn chỉnh, có thể cài đặt bên trong các
trình ứng dụng khác. Vì vậy nó là một loại cơ sở dữ liệu được sử dụng trên mobile trong đó có Android.

## Thao tác với SQLite
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
  
  |**Tham số**| **Mô tả** |
  |:--------:|:--------|
  |String dbName| Tên bảng để biên dịch |
  |String columnNames| Một danh sách các cột của bảng để trả về|
  |||
  |||
  |||
  |||
  |||

  Để thực hiện các thao tác trên ta đi vào một ví dụ đơn giản: tạo cơ sở dữ liệu với bảng **”Story”** bao gồm các thuộc tính `id`, `category`, `title` và `content`. Sau đó chúng ta sẽ thực hiện các thao tác đối với sơ cở dữ liệu này như thêm, sửa, xoá, truy vấn, đóng cơ sở dữ liệu.

**Bước 1:**
  Khởi tạo project:   
- Application Name: "DemoSQLite"
- Project Name: "DemoSQLite"
- Packetname: "com.trananh.example"
- Activity số 1: MainActivity
- Activity số 2: Home

**Bước 2:**
  Tạo model _Story_ với file name _story.java_

```java
package com.trananh.example;

public class Story {
	public int mCategory;
	public int mId;
	public String mTitle;
	public String mContent;
	
	public Story(int id, int category, String title, String content) {
		mId = id;
		mContent = content;
		mTitle = title;
		mCategory = category;
	}
}
```

 **Bước 3:**
    Tạo class _MySQLiteHelper_ để tạo và cập nhật database trong ứng dụng Android.
    
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
  **Bước 4:**
    Tạo class _StatusDataSource_. Trong class này chúng ta viết các hàm để mở, đóng kết nối và hỗ trợ thêm mới,
  sửa, xoá đối tượng vào cơ sở dữ liệu, lấy dữ liệu ra từ bảng trong cơ sở dữ liệu.
    

 
