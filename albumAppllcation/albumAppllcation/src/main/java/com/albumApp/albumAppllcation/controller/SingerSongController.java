package com.albumApp.albumAppllcation.controller;

import com.albumApp.albumAppllcation.ResourceNotFoundException;
import com.albumApp.albumAppllcation.model.Album;
import com.albumApp.albumAppllcation.model.Singer;
import com.albumApp.albumAppllcation.model.Song;
import com.albumApp.albumAppllcation.repository.AlbumRepository;
import com.albumApp.albumAppllcation.repository.SingerRepository;
import com.albumApp.albumAppllcation.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class SingerSongController {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    SingerRepository singerRepository;
    @Autowired
    AlbumController albumController;

    @GetMapping("/Singer/{id}/Songs")
    List<Song> getAllSongsOfSinger(@PathVariable(value = "id") Long id) {
        return songRepository.findBySingerSingerId(id);
    }

    @PostMapping("/Singer/{id}/addSong")
    Song addSongForSinger(@PathVariable(value = "id") Long id, @RequestBody Song song) {
        Album album = albumRepository.findById(song.getAlbum().getAlbumId()).orElseThrow(() -> new ResourceNotFoundException("Album Not found ", "Album Id : ", song.getAlbum().getAlbumId()));
        song.setAlbum(album);
        Singer singer = singerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Singer not found ", "Singer id : ", id));
        song.setSinger(singer);
        return songRepository.save(song);
    }

    @PutMapping("/Singer/{singerId}/Song/{songId}")
    Song updateSongOfSinger(@PathVariable(value = "singerId") Long singerId, @PathVariable(value = "songId") Long songId, @RequestBody Song updatedSong) {
        System.out.println(songId + "    " + singerId);
        Optional<Song> song = songRepository.findBySongIdAndSingerSingerId(songId, singerId);
        if (song.isPresent()) {
            song.get().setBillBoard(updatedSong.getBillBoard());
            song.get().setStyle(updatedSong.getStyle());
            song.get().setCertification(updatedSong.getCertification());
            song.get().setTitle(updatedSong.getTitle());
            song.get().setTrackLength(updatedSong.getTrackLength());
        } else {
            throw new ResourceNotFoundException("Song Not Found ", "Song id ", songId);
        }
        return songRepository.save(song.get());
    }

    @DeleteMapping("/Singer/{singerId}/Song/{songId}")
    ResponseEntity<?> deleteSongOfSinger(@PathVariable(value = "singerId") Long singerId, @PathVariable(value = "songId") Long songId, @RequestBody Song updatedSong) {
        Optional<Song> song = songRepository.findBySongIdAndSingerSingerId(songId, singerId);
        if (song.isPresent()) {
            songRepository.delete(song.get());
        } else {
            throw new ResourceNotFoundException("Song Not Found ", "Song id ", songId);
        }
        if (song.get().getAlbum().getListOfSongs().isEmpty()) {
            albumController.deleteAlbum(song.get().getAlbum().getAlbumId());
        }
        return ResponseEntity.ok().build();

    }

}


