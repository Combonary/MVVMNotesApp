package pamtech.com.mvvmnotesapp.repository.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDataBase extends RoomDatabase {
   private static NoteDataBase instance;

   public abstract NoteDao  noteDao();

   public static synchronized NoteDataBase getInstance(Context context){
       if(instance == null){
           instance = Room.databaseBuilder(context.getApplicationContext(),
                   NoteDataBase.class, "note_database")
                   .fallbackToDestructiveMigration()
                   .build();
       }
       return instance;
   }
}
