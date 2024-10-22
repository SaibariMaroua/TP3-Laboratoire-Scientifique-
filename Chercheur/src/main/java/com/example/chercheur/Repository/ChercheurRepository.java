package com.example.chercheur.Repository;

import com.example.chercheur.entities.Chercheur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChercheurRepository extends JpaRepository<Chercheur, Long> {
}
