package com.georgeasilveira.bibliothecam.ui.activity;

import android.os.Bundle;
import android.os.Environment;

import com.georgeasilveira.bibliothecam.R;
import com.georgeasilveira.bibliothecam.filter.FileExtensionFinder;
import com.georgeasilveira.bibliothecam.model.Book;
import com.georgeasilveira.bibliothecam.ui.adapter.BookListAdapter;

import java.io.File;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class EPubManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epub_manager);
        setupRecycler();

        FileExtensionFinder finder = new FileExtensionFinder(".epub");
        File directory = Environment.getExternalStorageDirectory();
        List<File> files = finder.findFiles(directory);

        List<Book> books = StreamSupport
                .stream(files)
                .map(file -> new Book()
                        .setTitle(file.getName())
                        .setAuthor("")
                        .setPage(0)
                        .setLocation(file.getAbsolutePath()))
                .collect(Collectors.toList());

        setBookList(books);
    }

    private void setupRecycler() {
        recyclerView = findViewById(R.id.epub_manager_list_books);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setBookList(List<Book> books) {
        BookListAdapter adapter = new BookListAdapter(books, false);
        recyclerView.setAdapter(adapter);
    }
}
