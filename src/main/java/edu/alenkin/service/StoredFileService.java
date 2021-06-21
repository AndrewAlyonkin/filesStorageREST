package edu.alenkin.service;

import edu.alenkin.model.StoredFile;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface StoredFileService extends Service<Long, StoredFile> {
    public List<StoredFile> getAllForUser(Long userId);
}
