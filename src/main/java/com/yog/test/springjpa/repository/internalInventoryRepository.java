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
public interface internalInventoryRepository extends RevisionRepository<Inventory, Integer, Integer>, JpaRepository<Inventory, Integer> {
}
