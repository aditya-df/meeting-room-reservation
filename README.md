---
runme:
  id: 01HM2TVJTPESASKVJRM4BMAYRG
  version: v2.2
---

Running spring-boot
mvnw spring-boot:run -Dspring-boot.run.profiles=dev

run migration
mvnw clean flyway:migrate