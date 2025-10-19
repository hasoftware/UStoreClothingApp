# Splash Screen Implementation

## Tổng quan

Đã tạo thành công trang splash screen cho ứng dụng Android dựa trên thiết kế SVG được cung cấp.

## Các tính năng đã implement:

### 1. SplashScreen.kt

- **Logo tròn màu xanh** với mũi tên trắng ở giữa (giống thiết kế SVG)
- **Hiệu ứng animation**:
  - Scale animation từ 0.8f đến 1f
  - Alpha animation từ 0f đến 1f
  - Fade out animation trước khi chuyển màn hình
- **Text "Socks X"** và "Fashion Store"
- **Indicator dots** ở dưới cùng
- **Delay 2 giây** trước khi chuyển màn hình

### 2. Navigation Updates

- Thêm `Screen.Splash` vào navigation
- Đặt SplashScreen làm `startDestination`
- Tự động chuyển đến StartScreen sau khi splash kết thúc

### 3. Màu sắc và thiết kế

- Background: Trắng (#FFFFFF)
- Logo: Xanh dương (#004CFF)
- Text: Đen (#202020)
- Hiệu ứng shadow cho logo

## Cách hoạt động:

1. Ứng dụng khởi động với SplashScreen
2. Logo xuất hiện với hiệu ứng scale và fade in
3. Text "Socks X" và "Fashion Store" hiển thị
4. Sau 2 giây, màn hình fade out
5. Tự động chuyển đến StartScreen (trang đăng nhập/đăng ký)

## Files đã tạo/sửa đổi:

- `app/src/main/java/com/example/myapplication/ui/screens/SplashScreen.kt` (mới)
- `app/src/main/java/com/example/myapplication/navigation/AppNavigation.kt` (cập nhật)

## Cách test:

1. Build và chạy ứng dụng
2. Splash screen sẽ hiển thị ngay khi mở app
3. Sau 2 giây sẽ tự động chuyển đến trang Start

## Tùy chỉnh:

- Có thể thay đổi thời gian delay bằng cách sửa `delay(2000)`
- Có thể thay đổi màu sắc trong các constant Color
- Có thể thay đổi text hiển thị
- Có thể thêm logo thật thay vì text "→"
