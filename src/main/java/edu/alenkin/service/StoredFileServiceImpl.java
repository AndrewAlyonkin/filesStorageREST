package edu.alenkin.service;

import edu.alenkin.model.StoredFile;
import edu.alenkin.repository.StoredFileRepository;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class StoredFileServiceImpl extends BaseService<Long, StoredFile> implements StoredFileService {
    public StoredFileServiceImpl(StoredFileRepository repository) {
        super(repository);
    }
}
