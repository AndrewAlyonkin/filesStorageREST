package edu.alenkin.repository;

import edu.alenkin.model.StoredFile;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface StoredFileRepository extends Repository<Long, StoredFile> {
  List<StoredFile> getAllForUser(Long userId);

}
