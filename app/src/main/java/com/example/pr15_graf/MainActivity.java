package com.example.pr15_graf;

import android.os.Bundle;
import android.os.Environment;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private MediaRecorder mediaRecorder;

    private MediaPlayer mediaPlayer;

    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/record.3gpp";
    }

    private void releaseRecorder(){
        if(mediaRecorder != null){
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    // Начало записи
    public void recordStart(View v) {
        try {
            releaseRecorder(); // Останавливаем запись
            File outFile = new File(fileName); // Создаём новый файл
            if (outFile.exists()) { // если файл существует
                outFile.delete(); // Удаляем файл
            }

            mediaRecorder = new MediaRecorder(); // Инициализируем MediaRecorder
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); // Указываем AudioSource
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // указываем формат
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // Указываем encoder
            mediaRecorder.setOutputFile(fileName); // указываем куда записывать информацию
            mediaRecorder.prepare(); // Подготавливает рекордер к началу захвата и кодирования данных.
            mediaRecorder.start(); // начинаем запись
        } catch (Exception e) {
            e.printStackTrace(); // Выводим стек ошибки
        }
    }
    public void recordStop(View v)
    {
        if (mediaRecorder != null)
        {
            mediaRecorder.stop();
        }
    }

    private void releasePlayer()
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void playStart(View v) {
        try {
            releasePlayer(); // Останавливаем воспроизведение
            mediaPlayer = new MediaPlayer(); // Инициализируем новый плеер
            mediaPlayer.setDataSource(fileName); // Подаём на вход файл для воспроизведения
            mediaPlayer.prepare(); // подготавливает плеер к началу захвата и кодирования данных.
            mediaPlayer.start(); // Начинаем воспроизведение
        } catch (Exception e) {
            e.printStackTrace(); // Выводим стек ошибки
        }
    }

}