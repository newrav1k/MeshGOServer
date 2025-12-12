package ru.mirea.newrav1k.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.newrav1k.authservice.model.entity.Tracker;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, UUID> {

    Optional<Tracker> findByUsername(String username);

}