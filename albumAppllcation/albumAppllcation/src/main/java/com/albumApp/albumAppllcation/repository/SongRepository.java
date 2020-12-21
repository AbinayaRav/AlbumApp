package com.albumApp.albumAppllcation.repository;

import com.albumApp.albumAppllcation.model.Song;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(value = "SELECT * FROM song WHERE song_id=:custom", nativeQuery = true)
    Song Q2(@Param(value = "custom") String s);

    List<Song> findByAlbumAlbumId(String albumId);
    List<Song> findBySingerSingerId(Long singerId);
    Optional<Song> findBySongIdAndSingerSingerId(Long songId, Long singerId);

}
