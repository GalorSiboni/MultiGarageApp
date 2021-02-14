package com.classy.common.room;

import android.content.Context;

        import androidx.room.Room;

import com.classy.common.model.Session;
import com.paz.taskrunnerlib.task_runner.RunnerCallback;
import com.paz.taskrunnerlib.task_runner.TaskRunner;


public class MyDatabase {
    private static AppDatabase appDatabase;
    private static MyDatabase instance;


    private MyDatabase(Context context) {

        String pName = context.getApplicationContext().getPackageName();
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, pName + "-GarageDB.db")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                // .allowMainThreadQueries()
                .build();
    }

    public static MyDatabase getInstance() {
        return instance;
    }

    public static MyDatabase initHelper(Context context) {
        if (instance == null) {
            instance = new MyDatabase(context);
        }

        return instance;
    }


    public interface CallBackGetLast {
        void dataReady(Session session);
    }

    public void getLastSession(CallBackGetLast callBackGetLast) {
        TaskRunner<Session> taskRunner = new TaskRunner<>();
        taskRunner.executeAsync(new RunnerCallback<Session>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public Session call() throws Exception {
                return appDatabase.sessionDao().getLastSession();

            }

            @Override
            public void onPostExecute(Session result) {
                callBackGetLast.dataReady(result);

            }
        });


    }


    public interface CallBackGetTotalSpentTime {
        void dataReady(long time);
    }

    public void getTotalSpendsTime(CallBackGetTotalSpentTime callback) {
        TaskRunner<Long> taskRunner = new TaskRunner<>();
        taskRunner.executeAsync(new RunnerCallback<Long>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public Long call() throws Exception {
                return appDatabase.sessionDao().totalSpendsTime();
            }

            @Override
            public void onPostExecute(Long result) {
                callback.dataReady(result);

            }
        });

    }

    public void insertSession(Session session) {


        new Thread(() -> appDatabase.sessionDao().insert(session)).start();
    }
}