package pamtech.com.mvvmnotesapp.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import pamtech.com.mvvmnotesapp.repository.model.room.Note;
import pamtech.com.mvvmnotesapp.repository.model.room.NoteDao;
import pamtech.com.mvvmnotesapp.repository.model.room.NoteDataBase;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDataBase dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    /**
     * insert note into database using note data access object (DAO)
     * @param note
     */
    public void insert(Note note){
      new InsertNoteAsynctask(noteDao).execute(note);
    }

    /**
     * update note in database
     * @param note
     */
    public void update(Note note){
      new UpdateNoteAsynctask(noteDao).execute(note);
    }

    /**
     * delete note from database
     * @param note
     */
    public void delete(Note note){
      new DeleteNoteAsynctask(noteDao).execute(note);
    }

    /**
     * delete all notes from database
     */
    public void deleteAllNotes(){

    }

    /**
     * retrieve all notes from database
     * @return live data object that can be observed
     */
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    /**
     * asynctask class to help perform the database insert operation in the background
     */
    private static class InsertNoteAsynctask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao; //note dao to perform db operations

        private InsertNoteAsynctask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsynctask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private UpdateNoteAsynctask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsynctask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private DeleteNoteAsynctask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsynctask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private DeleteAllNotesAsynctask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }



}
