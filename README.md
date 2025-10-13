# Stature App - Android Application

Ứng dụng Android được xây dựng với Jetpack Compose, tái tạo thiết kế từ Figma với 4 màn hình chính:

## 🎨 Thiết kế

Ứng dụng bao gồm 4 màn hình theo thiết kế Figma:

1. **Start Screen** - Màn hình giới thiệu với logo "Stature" và nút "Let's get started"
2. **Create Account Screen** - Form đăng ký với các trường Email, Password, và Phone Number
3. **Login Screen** - Màn hình đăng nhập với trường Email
4. **Password Screen** - Màn hình nhập mật khẩu với avatar người dùng

## 🚀 Cách Build và Chạy

### Yêu cầu hệ thống:

- Android Studio Arctic Fox hoặc mới hơn
- JDK 11 hoặc mới hơn
- Android SDK API 30+

### Các bước build:

1. **Mở project trong Android Studio:**

   ```
   File -> Open -> Chọn thư mục myapp
   ```

2. **Sync Gradle:**

   - Android Studio sẽ tự động sync
   - Nếu có lỗi, click "Sync Now"

3. **Build APK:**

   ```
   Build -> Build Bundle(s) / APK(s) -> Build APK(s)
   ```

4. **Chạy trên emulator/device:**
   - Chọn device/emulator
   - Click nút Run (▶️) hoặc Shift+F10

### Build từ command line (nếu có Java setup đúng):

```bash
# Windows
.\gradlew.bat assembleDebug

# Linux/Mac
./gradlew assembleDebug
```

## 📱 Tính năng đã implement

✅ **Theme và Colors:**

- Màu sắc theo thiết kế Figma (Blue Primary, Light, Dark)
- Typography với các kích thước phù hợp
- Material 3 design system

✅ **Navigation:**

- Jetpack Navigation Compose với bottom navigation
- Auth flow: Start → Create Account/Login → Password → Shop
- Main app flow: Shop ↔ Search ↔ Cart ↔ Profile
- Product detail navigation với back button

✅ **UI Components:**

- Custom buttons với rounded corners
- Text fields với validation states
- Background decorative shapes
- Responsive layout
- Search bar với placeholder
- Bottom navigation bar
- Product cards và image carousels

✅ **Authentication Screens:**

- StartScreen với logo và branding
- CreateAccountScreen với form validation
- LoginScreen với email input
- PasswordScreen với avatar và keyboard

✅ **E-commerce Screens:**

- ShopScreen (Home) với banner "Big Sale", categories grid, top products
- SearchResultsScreen với product grid layout
- ProductDetailScreen với image carousel và product info
- ProductVariationsScreen với color/size selection và quantity picker

## 🛠️ Cấu trúc code

```
app/src/main/java/com/example/myapplication/
├── MainActivity.kt                    # Main activity với navigation
├── navigation/
│   └── AppNavigation.kt              # Navigation setup
├── ui/
│   ├── theme/                        # Colors, Typography, Theme
│   │   ├── Color.kt
│   │   ├── Type.kt
│   │   └── Theme.kt
│   └── screens/                      # Tất cả màn hình
│       ├── StartScreen.kt
│       ├── CreateAccountScreen.kt
│       ├── LoginScreen.kt
│       └── PasswordScreen.kt
```

## 🎯 Các bước tiếp theo

Để hoàn thiện ứng dụng, bạn có thể:

1. **Thêm validation logic** cho form inputs
2. **Tích hợp API** cho authentication
3. **Thêm state management** với ViewModel
4. **Custom icons** thay cho placeholder
5. **Animations** cho transitions
6. **Unit tests** và UI tests

## 🔧 Troubleshooting

Nếu gặp lỗi build:

1. Kiểm tra Java version (cần JDK 11+)
2. Sync Gradle trong Android Studio
3. Clean và Rebuild project
4. Kiểm tra Android SDK version

## 📞 Hỗ trợ

Nếu có vấn đề gì, hãy kiểm tra:

- Android Studio logs
- Gradle build logs
- Device/Emulator compatibility

---

**Chúc bạn build thành công! 🎉**
