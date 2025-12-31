# Spring Boot 기본기

---

## Spring MVC 기반 REST API 설계

### 실습 과제

- [x] 간단한 도서 관리 REST API 구현하기
- [x] CRUD 작업을 모두 포함하는 컨트롤러 작성
- [x] 적절한 HTTP 상태 코드 반환하도록 구현
- [x] 예외 처리 핸들러 추가
- [x] Postman으로 API 테스트

---

## 계층분리 및 테스트 가능성 확보
### 실습과제

- [x]  **계층 분리 원칙 준수:** Controller는 HTTP 처리만, Service는 비즈니스 로직만, Repository는 데이터 접근만 담당하며 각 계층의 책임이 명확히 구분되어 있는가?
- [x]  **의존성 방향 및 주입 방식:** Controller → Service → Repository 순서로 단방향 의존성을 유지하고, 생성자 주입을 사용하여 순환 참조를 방지했는가?
- [x]  **트랜잭션 및 예외 처리:** Service 계층에 @Transactional이 적절히 적용되어 있고, 전역 예외 처리(@ControllerAdvice)로 일관된 에러 응답을 제공하는가?
- [x]  **DTO와 Entity 분리:** Controller와 Service 간에는 DTO를 사용하고, Entity는 영속성 계층에만 사용하며, Mapper를 통해 명확히 변환하는가?
- [x]  **테스트 커버리지 확보:** 각 계층별로 적절한 테스트(@WebMvcTest, @ExtendWith, @DataJpaTest)를 구현하고, 통합 테스트로 전체 플로우를 검증했는가?

---
## DTO와 Mapper 설계
### 실습과제

- [x]  DTO와 Entity의 차이점 이해 및 분리 설계 실습
- [x]  수동 Mapper 클래스 제작
- [x]  MapStruct 설정 및 기본 매핑 실습
- [x]  DTO Validation(@Valid, @NotNull 등) 적용
- [x]  실제 프로젝트에 DTO/Mapper 패턴 적용

---
## Validation/CustomValidation 및 통합 예외처리
### 실습과제

- [x]  DTO에 Validation 설계 및 필드/클래스 검증 Custom Validation 설계
- [x]  에러 응답이 항상 동일한 ErrorResponse 구조로 내려옴
- [x]  PathVariable 검증 실패가 전역 처리로 잡혀 400으로 내려옴
- [x]  404가 전역 처리로 잡혀 동일한 구조로 내려옴
- [x]  Validation과 Custom Validation 실패가 필드단위 정보로 내려와 응답구조에 담김