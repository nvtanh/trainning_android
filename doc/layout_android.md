#Layout trong android

Content
Các kiểu layout cơ bản trong android

[Linear layout](#liner-layout)

[Relative layout](#relative layout)

[Absolute layout](#absolute layout)

[Frame layout](#frame layout)

[Table layout](#table layout)

## Layout là gì?
  Layout định nghĩa cấu trúc cho một giao diện người dùng. Có thể tạo layout theo hai cách sau:
- Định nghĩa trong XML.
- Khởi tạo trong thời điểm runtime.

  Nhưng thường thì chúng ta sử dụng cách thứ nhât( sử dụng XML). Sử dụng XML cho phép chúng ta tách riêng phần GUI và controller riêng.
Từ đó chúng ta có thể dễ dàng sửa đổi, điều chỉnh giao diện mà không cần can thiệp và mã nguồn và biên dịch lại. Ngoài ra nó còn giúp 
chúng ta dễ dàng hơn trong việc hình dung ra cấu trúc của giao diện mà bạn muốn tạo. Do dó trong bài này chúng ta chỉ trình bày về cách
tạo một layout với XML.

## Linear layout

  Layout này hay được sử dụng nhất khi làm ứng dụng. Nó đơn giản cho phép ta sắp xếp các phần tử theo một dạng danh sách duy nhất dọc 
hoặc ngang. 
  Một vài thuộc tính của Linear layout hay sử dụng:
  -  **android-orientation**: Dùng để định hướng cách bố trí các control theo chiều ngang hoặc dọc.
	Có 2 giá trị đó là :
	- _vertical_: các thành phần sẽ được sắp xếp theo chiều dọc( là chiều từ trên xuống dưới).
	- _horizontal_: các thành phần sẽ được sắp xếp theo chiều ngang (từ trái qua phải).
  - **android:layout_width**, **android:layout_width**: dùng để xác định chiều rộng và chiều cao. 
    Có các giá trị lựa chọn như: `fill_parent`(lấp đầy thành phần cha) và `wrap_content`(vừa đủ nội dung).
	Ngoài ra có thể xác định kích thước cụ thể sử dụng các giá trị đo như px, ...
  - **android:layout_gravity** : căn vị trí cho các control với các tùy chọn như `left`, `right`, `center`.
  
  View này sẽ tạo ra một thanh cuộn nếu chiều dài của cửa sổ vượt quá chiều dài của màn hình.

  Ví dụ:
```android
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  android:orientation="vertical"
  android:layout_width="match_parent"
  android:layout_height="match_parent">

    <Button
        android:id="@+id/btn_absolutelayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/label_abso_layout" />
  
  <Button
      android:id="@+id/btn_framelayout"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="@string/label_frame_layout" />
  
  <Button
      android:id="@+id/btn_relativelayout"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="@string/label_relative_layout" />
  
  <Button android:id="@+id/btn_tablelayout"
    android:text="@string/label_table_layout"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"/>
  
</LinearLayout>
```

## Relative layout
  Đây là loại Layout cho phép chúng ta hiển thị các thành phần với các vị trí tương đối. Có nghĩa là vị trí của mỗi phần t có có thể được định vị 
vị trí của nó so với thành phần cha chứa nó hoặc các thành phần cạnh nó(thông qua id). Bạn có thể tùy chọn sắp xếp mỗi phần tử sang bên phải, 
bên dưới một phần tử khác, hoặc giữa màn hình, v.v.. 
  **Lưu ý:** trong dạng layout này, chúng ta thường dựa vào `id` để các thành phần làm mốc xác định vị trí tương đối. Vì vậy, khi làm với 
    loại layout này bạn nên chú ý cách đặt `id` cho chuẩn xác, hạn thay đổi `id` vì khi đó vị trí các thành phần trên GUI sẽ dễ bị thay đổi
	(nếu đổi `id` cần đổi các tham chiếu khác sao cho khớp với `id` mới)
  Dưới đây là danh sách các thuộc tính canh phần tử xuất hiện như thế nào so với các phần tử khác:

|**Tên thuộc tính**| **Mô tả** |
|:--------:|:--------|
|android:layout_above| Đặt phần tử hiện tại nằm kế sau phần tử có id được chỉ ra |
|android:layout_alignBaseline| Đặt phần tử này lên cùng dòng với phần tử có id được chỉ ra |
|android:layout_alignBottom| Canh sao cho đáy của phần tử hiện thời trùng với đáy của phần tử có id được chỉ ra |
|android:layout_alignLeft| Đặt cạnh trái của phần tử hiện thời trùng với cạnh trái của phần tử có id được chỉ ra  |
|android:layout_alignParentBottom| Nếu thiết lập là true thì phần tử hiện thời sẽ được canh xuống đáy của phần tử chứa nó |
|android:layout_alignParentLeft| Nếu được thiết lập là true thì phần tử hiện thời sẽ canh trái so với phần tử chứa nó  |
|android:layout_alignParentRight| Nếu được thiết lập là true thì phần tử hiện thời sẽ canh phải so với phần tử chứa nó |
|android:layout_alignParentTop| Nếu được thiết lập là true thì phần tử hiện thời sẽ canh lên đỉnh phần tử chứa nó  |
|android:layout_alignRight| Canh cạnh phải của phần tử hiện thời trùng với cạnh phải của phần tử có id được chỉ ra  |
|android:layout_alignTop| Canh đỉnh của phần tử hiện thời trùng với đỉnh của phần tử có id được chỉ ra |
|android:layout_alignWithParentIfMissing| Nếu thiết lập là true, thì phần tử sẽ được canh theo phần tử chứa nó nếu các thuộc tính canh của phần tử không có.|
|android:layout_below| Đặt phần tử hiện thời ngay sau phần tử có id được chỉ ra |
|android:layout_centerHorizontal| Nếu thiết lập là true thì phần tử hiện thời sẽ được canh giữa theo chiều ngang phần tử chứa nó |
|android:layout_centerInParent| Nếu thiết lập là true thì phần tử hiện thời sẽ được canh chính giữa theo chiều phải trái và trên dưới so với phần tử chứa nó |
|android:layout_centerVertical| Nếu thiết lập là true thì phần tử hiện thời sẽ được canh chính giữa theo chiều dọc phần tử chứa nó |
|android:layout_toLeftOf| Đặt cạnh phải của phần tử hiện thời trùng với cạnh trái của phần tử có id được chỉ ra |
|android:layout_toRightOf| Đặt cạnh trái của phần tử hiện thời trùng với cạnh phải của phần tử có id được chỉ ra |

Ví dụ:
```android
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
   android:layout_width="fill_parent"
   android:layout_height="fill_parent"
   android:paddingLeft="16dp"
   android:paddingRight="16dp" >
   <EditText
      android:id="@+id/name"
      android:layout_width="fill_parent"
      android:layout_height="wrap_content"
      android:hint="@string/reminder" />
   <TextView
      android:id="@+id/dates"
      android:layout_width="0dp"
      android:layout_height="wrap_content"
      android:layout_below="@id/name"
      android:layout_alignParentLeft="true"
      android:layout_toLeftOf="@+id/times" />
   <TextView
      android:id="@id/times"
      android:layout_width="96dp"
      android:layout_height="wrap_content"
      android:layout_below="@id/name"
      android:layout_alignParentRight="true" />
   <Button
      android:layout_width="96dp"
      android:layout_height="wrap_content"
      android:layout_below="@id/times"
      android:layout_centerInParent="true"
      android:text="@string/done" />
</RelativeLayout>
```

## Absolute Layout 
  Đây là loại layout cho phép bạn thiết lập giao diện theo vị trí tuyệt đối dựa trên trục tọa độ x, y.

  Ví dụ:
```android
<AbsoluteLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent">
    <Button 
     android:id="@+id/backbutton"
     android:text="Continue"
     android:layout_x="10px"
     android:layout_y="5px"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content" />
    <TextView
     android:layout_x="10px"
     android:layout_y="110px"
     android:text="User Name"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content" />
    <EditText
     android:layout_x="150px"
     android:layout_y="100px"
     android:width="100px"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content" />
    <TextView
     android:layout_x="10px"
     android:layout_y="160px"
     android:text="Password"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content" />
     <EditText
     android:layout_x="150px"
     android:layout_y="150px"
     android:width="100px"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content" />
</AbsoluteLayout>
```

## Frame layout
  Đây là loại Layout cơ bản nhất, đặc điểm của nó là khi gắn các control lên giao diện thì các control này sẽ luôn được "Neo" ở góc trái 
trên cùng màn hình, nó không cho phép chúng ta thay đổi vị trí của các control theo một Location nào đó.
  
  Các control đưa vào sau nó sẽ đè lên trên và che khuất control trước đó (trừ khi ta thiết lập transparent cho control sau).

  Ví dụ:
```android
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
   android:layout_width="fill_parent"
   android:layout_height="fill_parent">
   
   <ImageView 
   android:src="@drawable/ic_launcher"
   android:scaleType="fitCenter"
   android:layout_height="250px"
   android:layout_width="250px"/>
   
   <TextView
   android:text="Frame Demo"
   android:textSize="30px"
   android:textStyle="bold"
   android:layout_height="fill_parent"
   android:layout_width="fill_parent"
   android:gravity="center"/>
</FrameLayout>
```

## Table layout
  Đây là loại layout cho phép bạn trình bày các thành phần dưới dạng lưới.
  Sử dụng `<TableRow>` để tạo ra các hàng, các cột trong layout.
  Các ô được tạo ra có thể chứa được các thành phần view, layout khác ở trong nó.

Ví dụ:
```android
<TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
   android:layout_width="fill_parent"
   android:layout_height="fill_parent">
   <TableRow>
      <Button
         android:id="@+id/backbutton"
         android:text="Back"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content" />
   </TableRow>
   <TableRow>
      <TextView
         android:text="First Name"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:layout_column="1" />
         <EditText
         android:width="100px"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content" />
   </TableRow>
   <TableRow>
      <TextView
         android:text="Last Name"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:layout_column="1" />
         <EditText
         android:width="100px"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content" /> 
   </TableRow>
</TableLayout>
```

  Trên đây là một số layout cơ bản. Nếu chúng ta muốn có một giao diện đẹp thì chúng ta nên kết hợp các dạng layout lại với nhau một cách hợp lý để tạo 
một giao diện đẹp, thu hút người dùng.
  
