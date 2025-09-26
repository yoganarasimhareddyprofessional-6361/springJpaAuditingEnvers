# 5) SPRING JPA AUDITING (ENVERS)

Hibernate Envers is a module of Hibernate that allows you to:

- Automatically create audit tables.
- Store revisions of entities on every change.
- Query entity changes over time.

| Feature | Envers Capability |
| --- | --- |
| Auto auditing | ✅ via `@Audited` |
| Track changed fields | ✅ with `global_with_modified_flag` |
| Store deletion data | ✅ with `store_data_at_delete` |
| Who made the change | ✅ via custom `RevisionListener` |
| Custom audit queries | ✅ with `AuditReader` |

NOTE : we will reuse the iventory code to build on top of this

Step 1: add dependency on pom.xml

```java
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-envers</artifactId>
</dependency>
```

- Step 2:
    - on entity Inventory add 4 params as below
    -

    ```java
    @CreatedBy
        private String createdBy;
    
        @CreatedDate
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdDate;
    
        @LastModifiedBy
        private String modifiedBy;
    
        @LastModifiedDate
        @Temporal(TemporalType.TIMESTAMP)
        private Date modifiedDate;
    ```

    ```java
    package com.yog.test.springjpa.entity;
    
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.data.annotation.CreatedBy;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedBy;
    import org.springframework.data.annotation.LastModifiedDate;
    
    import java.util.Date;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    //@Entity(name = "inventory_tbl")
    @Entity
    public class Inventory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String name;
        private Double price;
        //@Column(name = "product_desc")
        private String description;
        //@Column(name = "productType")
        private String productType;
    
        @CreatedBy
        private String createdBy;
    
        @CreatedDate
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdDate;
    
        @LastModifiedBy
        private String modifiedBy;
    
        @LastModifiedDate
        @Temporal(TemporalType.TIMESTAMP)
        private Date modifiedDate;
    
    }
    
    ```

- Step 3:
    - NOTE : if ur not using spring security created by and modified by will be set manually now but later we  get user from context and use it here.
    - Create a common package an dcreate new class AuditorAwareImpl as below.
    -

    ```java
    
    ```

- Step4:
    - create a bean of AuditorAwareImpl on main class for now since we dont have spring security and mark it as @Bean
    -

    ```java
     @Bean
        public AuditorAware<String> auditorAware() {
            return new AuditorAwareImpl();
        }
    ```

- Step 5:
    - on top of entity class annotate
        - `@EntityListeners(AuditingEntityListener.class)`
    - and on top of main class annotate with
        - `@EnableJpaAuditing(auditorAwareRef = "auditorAware")`
    -

    ```java
    @SpringBootApplication
    @EnableJpaAuditing(auditorAwareRef = "auditorAware")
    public class SpringJpaApplication {
    
        @Bean
        public AuditorAware<String> auditorAware() {
            return new AuditorAwareImpl();
        }
    
        public static void main(String[] args) {
            SpringApplication.run(SpringJpaApplication.class, args);
        }
    
    }
    
    ```


- Step 6:
    - for REVISION  feature use @Audited on entity class
    - on reposiory create a new inteface as below.
    - What is `RevisionRepository<T, ID, N>`?

  `RevisionRepository` is a **Spring Data interface** that allows you to easily access **audit/revision history** of an entity without directly using `AuditReader`.

  ### 📌 Type Parameters:

    - `T` – the entity type
    - `ID` – the entity's ID type
    - `N` – the revision number type (usually `Integer` or `Long`)

    ```java
    package com.yog.test.springjpa.repository;
    
    import com.yog.test.springjpa.entity.Inventory;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.repository.history.RevisionRepository;
    
    //What is RevisionRepository<T, ID, N>?
    //RevisionRepository is a Spring Data interface that allows you to easily access audit/revision history of an entity without directly using AuditReader.
    // Type Parameters:
    //T – the entity type
    //ID – the entity's ID type
    //N – the revision number type (usually Integer or Long)
    public interface internalInventoryRepository extends 
    RevisionRepository<Inventory, Integer, Integer>
    , JpaRepository<Inventory, Integer> {
    }
    
    ```

    ```java
    pom.xml 
    
     <dependency>
                <groupId>org.springframework.data</groupId>
                <artifactId>spring-data-envers</artifactId>
            </dependency>
    ```

    - On main class add `@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)`

    ```java
    package com.yog.test.springjpa;
    
    import com.yog.test.springjpa.common.AuditorAwareImpl;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.context.annotation.Bean;
    import org.springframework.data.domain.AuditorAware;
    import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
    import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
    import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
    
    @SpringBootApplication
    @EnableJpaAuditing(auditorAwareRef = "auditorAware")
    @EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
    public class SpringJpaApplication {
    
        @Bean
        public AuditorAware<String> auditorAware() {
            return new AuditorAwareImpl();
        }
    
        public static void main(String[] args) {
            SpringApplication.run(SpringJpaApplication.class, args);
        }
    
    }
    
    ```

- Start server (drop tables created bfr or use new schema on application.properties.
- 3 Tables will be Created Below
    - Inventory (with all invetory data )
    - Inventory_AUD ( audits of each inventory product change will be stored here )
    - REVINFO