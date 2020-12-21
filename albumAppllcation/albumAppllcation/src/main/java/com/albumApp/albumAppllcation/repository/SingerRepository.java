package com.albumApp.albumAppllcation.repository;

import com.albumApp.albumAppllcation.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM album_singer WHERE singer_id = ?", nativeQuery = true)
    void deleteAlbumSingerAssociation(Long id);
}
