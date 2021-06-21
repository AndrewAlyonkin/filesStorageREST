package edu.alenkin;

import edu.alenkin.model.Event;
import edu.alenkin.model.StoredFile;
import edu.alenkin.model.User;
import edu.alenkin.util.JsonConverter;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class TestData {
    private final static Long startSeq = 10000L;
    public final static Long chuck_id = startSeq + 0;
    public final static Long lee_id = startSeq + 1;

    public final static Long file11_id = startSeq + 2;
    public final static Long file12_id = startSeq + 3;

    public final static Long file21_id = startSeq + 4;
    public final static Long file22_id = startSeq + 5;

    public final static Long event11_id = startSeq + 6;
    public final static Long event12_id = startSeq + 7;

    private final static Timestamp event11_ts = Timestamp.valueOf("2021-06-20 19:10:25");
    private final static Timestamp event12_ts = Timestamp.valueOf("2021-06-20 19:10:25");

    public final static User CHUCK = new User("Chuck Norris", chuck_id);
    public final static User LEE = new User("Bruce Lee", lee_id);

    public final static StoredFile chFile1 = new StoredFile(file11_id, "Chuck://test/testDir/testFile.pdf", 9000, CHUCK);
    public final static StoredFile chFile2 = new StoredFile(file12_id, "Chuck://directory/dir/file.txt", 15000, CHUCK);
    public final static StoredFile leFile1 = new StoredFile(file21_id, "Lee://BruceDir/testFile.exe", 25000, LEE);
    public final static StoredFile leFile2 = new StoredFile(file22_id, "Lee://BruceDir/virusFile.jpeg", 35000, LEE);

    public final static Event chEvent = new Event(event11_id, event11_ts, chFile1, CHUCK);
    public final static Event leEvent = new Event(event12_id, event12_ts, leFile1, LEE);

    public static User newUser(){
        return new User("NEW");
    }

    public static StoredFile newFile(){
        return new StoredFile(null, "NEW TEST FILE", 35000, LEE);
    }

    public static Event newEvent() {
        return new Event(null, event12_ts, leFile2, LEE);
    }


    @Test
    public void print() {
        JsonConverter<User> converter= new JsonConverter(User.class);
        User newUser =  new User(CHUCK);
        newUser.setStoredFiles(List.of(chFile1, chFile2));
        newUser.setEvents(List.of(chEvent));
        System.out.println(converter.toJson(newUser));
        System.out.println(converter.toJson(List.of(newUser, newUser)));


    }
 }
