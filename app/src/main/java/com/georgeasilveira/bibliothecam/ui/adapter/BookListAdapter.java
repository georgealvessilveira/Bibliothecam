package com.georgeasilveira.bibliothecam.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.folioreader.FolioReader;
import com.georgeasilveira.bibliothecam.R;
import com.georgeasilveira.bibliothecam.dao.BookDAO;
import com.georgeasilveira.bibliothecam.model.Book;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private final List<Book> books;
    private final boolean read;
    private final FolioReader folioReader;

    public BookListAdapter(List<Book> books, boolean read) {
        this.books = books;
        this.read = read;
        this.folioReader = FolioReader.get();
    }

    private void openEPubReader(Book book) {
        folioReader.openBook(book.getLocation());
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);

        return new BookViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.page.setText(String.valueOf(book.getPage()));

        if (!read) holder.selection.setOnClickListener(view -> openEPubReader(book));
        else holder.selection.setOnClickListener(view -> {
            BookDAO bookDAO = new BookDAO(view.getContext());
            bookDAO.insertBook(book);
        });
    }

    @Override
    public long getItemId(int position) {
        return books.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView page;
        private ImageView cover;
        private View selection;

        BookViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.item_book_title);
            author = view.findViewById(R.id.item_book_author);
            page = view.findViewById(R.id.item_book_page);
            cover = view.findViewById(R.id.item_book_cover);
            selection = view.findViewById(R.id.item_book_selection);
        }
    }
}
