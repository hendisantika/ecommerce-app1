# 🔐 Admin Access Guide

## How to Fix 403 Error on `/admin/categories`

### Problem

When accessing `http://localhost:8080/admin/categories` directly, you get **HTTP 403 Forbidden**.

### Root Cause

Spring Security requires admin authentication before accessing any `/admin/**` routes (except `/admin/login`).

### Solution Steps

1. **Start the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Access Admin Login Page**
   ```
   http://localhost:8080/admin/login
   ```

3. **Login with Admin Credentials**
    - **Username**: `admin`
    - **Password**: `admin123`

4. **After Successful Login**
    - You'll be redirected to: `http://localhost:8080/admin/`

5. **Now Access Categories**
   ```
   http://localhost:8080/admin/categories
   ```
   **Expected Result**: ✅ **HTTP 200 OK** with categories page

### Security Configuration Explanation

```java
// AdminConfigurationAdapter (Order 1)
http.securityMatcher("/admin/**")
    .

authorizeHttpRequests(requests ->requests
        .

requestMatchers("/admin/login").

permitAll()     // ✅ Public
        .

requestMatchers("/admin/**").

hasRole("ADMIN")   // 🔒 Requires ADMIN role
    )
```

### Available Admin Endpoints

| Endpoint            | Method | Authentication | Description      |
|---------------------|--------|----------------|------------------|
| `/admin/login`      | GET    | None           | Admin login page |
| `/admin/`           | GET    | ADMIN          | Admin dashboard  |
| `/admin/categories` | GET    | ADMIN          | View categories  |
| `/admin/categories` | POST   | ADMIN          | Add category     |
| `/admin/products`   | GET    | ADMIN          | View products    |
| `/admin/customers`  | GET    | ADMIN          | View customers   |

### Default Admin Account

- **Username**: `admin`
- **Password**: `admin123`
- **Role**: `ROLE_ADMIN`
- **Database ID**: Check `customer` table

### Troubleshooting

1. **Still getting 403?**
    - Clear browser cookies/session
    - Restart application
    - Verify admin user exists in database

2. **Login not working?**
    - Check password is `admin123`
    - Verify form posts to `/admin/loginvalidate`

3. **Redirect loops?**
    - Check success handler redirects to `/admin/`
    - Verify admin has `ROLE_ADMIN` in database

### Testing with cURL

```bash
# 1. Get login page and extract session cookie
curl -c cookies.txt http://localhost:8080/admin/login

# 2. Login with admin credentials
curl -b cookies.txt -X POST \
  -d "username=admin&password=admin123" \
  http://localhost:8080/admin/loginvalidate

# 3. Access protected admin page
curl -b cookies.txt http://localhost:8080/admin/categories
```

### Database Verification

```sql
-- Check admin user exists
SELECT username, role
FROM customer
WHERE username = 'admin';

-- Expected result:
-- username | role
-- admin    | ROLE_ADMIN
```