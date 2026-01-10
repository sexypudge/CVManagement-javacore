**Final Project – Java Core (Console Application)**

## 1. Mục tiêu project

Project này nhằm giúp sinh viên:

- Áp dụng **Java Core** vào một bài toán nghiệp vụ thực tế
- Rèn tư duy **OOP, clean code, business logic**
- Chuẩn bị nền tảng để học **Spring Boot + Database** sau này

⚠️ Project **KHÔNG dùng**:

- ~~File IO~~
- ~~Thread / Concurrency~~
- ~~Database~~
- ~~Spring~~

T**ất cả dữ liệu lưu trong bộ nhớ (in-memory).**

---

## 2. Mô tả nghiệp vụ

Hệ thống quản lý **CV của ứng viên** cho một công ty IT.

Hệ thống cho phép:

- Quản lý ứng viên
- Quản lý CV
- Gán CV vào các vị trí tuyển dụng
- Đánh giá CV
- Thống kê & lọc dữ liệu

---

## 3. Phạm vi kiến thức bắt buộc

### BẮT BUỘC PHẢI DÙNG

- OOP (encapsulation, inheritance, polymorphism)
- Interface & abstract class
- Collection (`List`, `Map`, `Set`)
- Generic
- Enum
- Custom Exception
- Java 8 Stream API

### KHÔNG ĐƯỢC DÙNG

- **Logic trong** `main`

---

## 4. Các thực thể chính (Domain Model)

### 4.1 Candidate

```java
id: String (unique)
fullName: String
email: String
yearsOfExperience: int
status: ACTIVE / INACTIVE

```

---

### 4.2 CV

```java
id: String (unique)
candidateId: String
skills: List<String>
level: INTERN / FRESHER / JUNIOR / MIDDLE / SENIOR
status: DRAFT / SUBMITTED / APPROVED / REJECTED

```

---

### 4.3 JobPosition

```java
id: String (unique)
title: String
requiredLevel: Level
requiredSkills: Set<String>

```

---

### 4.4 CVSubmission

```java
cvId: String
jobPositionId: String
score: Double (0 – 10)
result: PASS / FAIL / PENDING

```

---

## 5. Chức năng bắt buộc

### 5.1 Quản lý Candidate

- Thêm ứng viên
- Cập nhật thông tin
- Deactivate ứng viên (soft delete)
- Tìm theo:
    - id
    - tên (contains)
- Liệt kê ứng viên ACTIVE

---

### 5.2 Quản lý CV

- Tạo CV cho ứng viên
- Cập nhật skill / level
- Không cho tạo CV nếu candidate INACTIVE
- 1 candidate có thể có nhiều CV

---

### 5.3 Quản lý Job Position

- Thêm vị trí tuyển dụng
- Cập nhật yêu cầu
- Không cho xóa nếu đã có CV apply

---

### 5.4 Apply CV vào Job (Apply CV to job)

- CV chỉ được apply khi status = SUBMITTED
- Không apply trùng 1 job
- Tự động đánh giá:
    - Skill match
    - Level match

---

### 5.5 Đánh giá CV (Evaluate CV)

- Nhập score
- Tự động PASS / FAIL theo score
- Không cho sửa khi đã APPROVED / REJECTED

---

### 5.6 Show candidate report

- Mục đích: Xem tổng hợp thông tin ứng viên
    - Tìm thông tin ứng viên tương ứng với **candidateId**
    - Load tất cả CV của candidate
    - Với mỗi CV:
        - list job đã apply
        - result & score
        - **Output** (ví dụ):
        
        ```jsx
        Candidate: Nguyen Van A
        CV: CV001 (SUBMITTED)
          - Java Backend: PASS (8.5)
          - DevOps: FAIL (4.0)
        ```
        

---

### 5.7 Show Statistics (***Nice to have***)

- **Các thống kê:**
    - Tổng số candidate
    - Tổng số CV
    - % CV PASS / FAIL
    - Job có nhiều CV apply nhất
    - Skill phổ biến nhất
- **Luồng xử lý dùng Java 8 Stream:**
    - groupBy
    - count
    - max

---

## 6. Business rules quan trọng (bắt buộc xử lý)

- Candidate INACTIVE → không tạo CV
- CV chưa SUBMITTED → không apply
- Không apply 1 CV vào cùng 1 job nhiều lần
- Score phải trong khoảng 0–10
- Status transition phải hợp lệ
- 

---

## 7. Menu console (gợi ý)

```
1. Add candidate
2. Create CV
3. Submit CV
4. Add job position
5. Apply CV to job
6. Evaluate CV
7. Show candidate report
8. Statistics
9. Exit

```

---

## 8. Yêu cầu thiết kế code

### 8.1 Cấu trúc package (bắt buộc)

```
domain/
repository/
service/
exception/
util/

```

---

### 8.2 Repository

- Interface + Implementation
- Dữ liệu lưu bằng `Map<ID, Entity>`

---

### 8.3 Service

- Chứa toàn bộ business logic
- Validate dữ liệu
- Throw custom exception

---

### 8.4 Exception (ví dụ)

- `CandidateNotFoundException`
- `InvalidCVStatusException`
- `DuplicateSubmissionException`
- `InvalidScoreException`

---

## 9. Stream API – yêu cầu bắt buộc

Sinh viên phải dùng Stream cho:

- Show Candidate Report
- *Show statistics* (**Nice to Have**)
