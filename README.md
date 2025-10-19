# UStore Backend API

Backend API cho ứng dụng e-commerce UStore được xây dựng bằng Spring Boot.

## 🚀 Tính năng

- **Authentication & Authorization**: JWT-based authentication
- **User Management**: Đăng ký, đăng nhập, quản lý user
- **Product Management**: CRUD operations cho sản phẩm
- **Category Management**: Quản lý danh mục sản phẩma
- **Product Reviews**: Hệ thống đánh giá sản phẩm
- **Search & Filter**: Tìm kiếm và lọc sản phẩm
- **File Upload**: Upload hình ảnh sản phẩm
- **Database Migration**: Flyway migration scripts

## 🛠️ Công nghệ sử dụng

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security**
- **Spring Data JPA**
- **MySQL 8.0**
- **JWT (JSON Web Token)**
- **Flyway Migration**
- **Maven**

## 📋 Yêu cầu hệ thống

- Java 17+
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Cài đặt và chạy

### 1. Clone repository

```bash
git clone <repository-url>
cd backend
```

### 2. Cấu hình database

- Tạo database MySQL: `ustore_db`
- Cập nhật thông tin database trong `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ustore_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
    username: your_username
    password: your_password
```

### 3. Chạy ứng dụng

```bash
# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại: `http://localhost:8080`

## 📚 API Documentation

### Swagger UI

Truy cập: `http://localhost:8080/swagger-ui.html`

### API Endpoints

#### Authentication

- `POST /api/auth/signin` - Đăng nhập
- `POST /api/auth/signup` - Đăng ký

#### Products

- `GET /api/products` - Lấy danh sách sản phẩm
- `GET /api/products/{id}` - Lấy sản phẩm theo ID
- `GET /api/products/search?keyword=...` - Tìm kiếm sản phẩm
- `GET /api/products/category/{categoryId}` - Sản phẩm theo danh mục
- `GET /api/products/featured` - Sản phẩm nổi bật
- `GET /api/products/new` - Sản phẩm mới
- `POST /api/products` - Tạo sản phẩm mới (Admin)
- `PUT /api/products/{id}` - Cập nhật sản phẩm (Admin)
- `DELETE /api/products/{id}` - Xóa sản phẩm (Admin)

#### Categories

- `GET /api/categories` - Lấy danh sách danh mục
- `GET /api/categories/{id}` - Lấy danh mục theo ID
- `GET /api/categories/active` - Danh mục đang hoạt động
- `POST /api/categories` - Tạo danh mục mới (Admin)
- `PUT /api/categories/{id}` - Cập nhật danh mục (Admin)
- `DELETE /api/categories/{id}` - Xóa danh mục (Admin)

## 🔐 Authentication

### Đăng ký

```json
POST /api/auth/signup
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "fullName": "Test User",
  "phone": "0123456789"
}
```

### Đăng nhập

```json
POST /api/auth/signin
{
  "username": "testuser",
  "password": "password123"
}
```

### Sử dụng JWT Token

Thêm header vào các request cần authentication:

```
Authorization: Bearer <your-jwt-token>
```

## 🗄️ Database Schema

### Tables

- `users` - Thông tin người dùng
- `roles` - Vai trò người dùng
- `user_roles` - Liên kết user-role
- `categories` - Danh mục sản phẩm
- `products` - Sản phẩm
- `product_images` - Hình ảnh sản phẩm
- `product_reviews` - Đánh giá sản phẩm

## 🔧 Cấu hình

### JWT Configuration

```yaml
jwt:
  secret: your-secret-key
  expiration: 86400000 # 24 hours
```

### CORS Configuration

```yaml
cors:
  allowed-origins:
    - http://localhost:3000
    - http://localhost:8080
  allowed-methods:
    - GET
    - POST
    - PUT
    - DELETE
    - OPTIONS
```

## 📝 Migration

Database migration được quản lý bởi Flyway:

- Scripts migration: `src/main/resources/db/migration/`
- Tự động chạy khi khởi động ứng dụng
- Versioning: V1**, V2**, V3\_\_...

## 🧪 Testing

```bash
# Chạy tests
mvn test

# Chạy tests với coverage
mvn test jacoco:report
```

## 📦 Build & Deploy

```bash
# Build JAR file
mvn clean package

# Chạy JAR file
java -jar target/ustore-backend-1.0.0.jar
```

## 🤝 Contributing

1. Fork repository
2. Tạo feature branch
3. Commit changes
4. Push to branch
5. Tạo Pull Request
