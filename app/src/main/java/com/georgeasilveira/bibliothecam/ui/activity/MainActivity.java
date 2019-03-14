package com.georgeasilveira.bibliothecam.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.folioreader.ui.activity.FolioActivity;
import com.georgeasilveira.bibliothecam.R;
import com.georgeasilveira.bibliothecam.dao.BookDAO;
import com.georgeasilveira.bibliothecam.model.Book;
import com.georgeasilveira.bibliothecam.ui.adapter.BookListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BookDAO bookDAO;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bookDAO = new BookDAO(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        setupNavigation(toolbar);
        setupRecycler();

        FloatingActionButton floatingButton = findViewById(R.id.main_floating_action_button);
        floatingButton.setOnClickListener(v -> {
            Intent openEPubFileIntent = new Intent(this, EPubManagerActivity.class);
            startActivity(openEPubFileIntent);
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_recent_books) {

        } else if (id == R.id.nav_library_books) {
            setBookList(bookDAO.getAllBooks());
        } else if (id == R.id.nav_favorite) {

        }

        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupNavigation(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
        drawer.setDrawerShadow(R.drawable.shadow, GravityCompat.START);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupRecycler() {
        recyclerView = findViewById(R.id.main_list_books);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setBookList(List<Book> books) {
        BookListAdapter adapter = new BookListAdapter(books, false);
        recyclerView.setAdapter(adapter);
    }
}
