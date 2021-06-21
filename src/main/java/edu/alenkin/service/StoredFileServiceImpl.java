package edu.alenkin.service;

import edu.alenkin.model.StoredFile;
import edu.alenkin.repository.StoredFileRepository;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@NoArgsConstructor
public class StoredFileServiceImpl extends BaseService<Long, StoredFile> implements StoredFileService {
    public StoredFileServiceImpl(StoredFileRepository repository) {
        super(repository);
    }


    @Override
    public List<StoredFile> getAllForUser(Long userId) {
        return ((StoredFileRepository) repository).getAllForUser(userId);
    }
}
