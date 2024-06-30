package com.salgend.pause.reservationsManagement.repositories;

import com.salgend.pause.reservationsManagement.entities.TableReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface ITableReservationRepository extends JpaRepository<TableReservation, Long> {
    @Query("SELECT COALESCE(MAX(tr.number), 0) FROM TableReservation tr")
    Integer findMaxNumber();
}
