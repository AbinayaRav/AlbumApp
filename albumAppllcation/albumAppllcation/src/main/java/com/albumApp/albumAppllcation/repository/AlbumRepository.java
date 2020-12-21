package com.albumApp.albumAppllcation.repository;

import com.albumApp.albumAppllcation.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

   /* @Modifying
    @Transactional
    @Query(value = "DELETE FROM album WHERE album_id = ?", nativeQuery = true)
    void Q1(Long id);*/

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM album_singer WHERE album_id=?", nativeQuery = true)
    void deleteAlbumSingerAssociation(Long id);

}
