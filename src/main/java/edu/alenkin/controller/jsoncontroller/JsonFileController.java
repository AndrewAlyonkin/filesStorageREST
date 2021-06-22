package edu.alenkin.controller.jsoncontroller;

import edu.alenkin.model.StoredFile;
import edu.alenkin.repository.StoredFileRepositoryImpl;
import edu.alenkin.service.StoredFileServiceImpl;
import edu.alenkin.util.JsonConverter;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonFileController extends JsonBaseController<Long, StoredFile> {
    public JsonFileController() {
        super(new StoredFileServiceImpl(new StoredFileRepositoryImpl()), new JsonConverter<StoredFile>(StoredFile.class));
    }

}
