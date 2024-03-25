package com.example.anispark;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Next_Activity extends AppCompatActivity {
    private final String[] quotes = {
            "If you don’t take risks, you can’t create a future. — Monkey D. Luffy, One Piece",
            "No matter how hard or how impossible it is, never lose sight of your goal. — Monkey D. Luffy, One Piece",
            "I devoted my entire life to becoming the World's Greatest Swordsman. I made my choice, so I'm the only one who gets to call me stupid. — Zoro, One Piece",
            "We're born free. All of us. Free. Some don't believe it; others try to take it away... to Hell with them! — Eren Yeager, Attack on titan",
            "If you win, you live. If you lose, you die. If you don't fight, you can't win. — Eren Yeager, Attack on titan",
            "No One Knows What The Outcome Will Be. So, Choose Whatever You'll Regret The Least. — Levi, Attack on titan",
            "Push through the pain. Giving up hurts more.—Vegeta, Dragon Ball Z",
            "If you can do one thing, hone it to perfection. Hone it to the utmost limit. — Jigoro, Demon Slayer",
            "People become stronger because they have memories they can't forget. — Tsunade, Naruto",
            "The strong should aid and protect the weak. Then, the weak will become strong, and they in turn will aid and protect those weaker than them. That is the law of nature. — Tanjiro Kamado, Demon Slayer",
            "People become stronger because they have memories they can't forget. — Tsunade, Naruto",
            "If you don’t like your destiny, don’t accept it. Instead, have the courage to change it the way you want it to be. — Naruto Uzumaki, Naruto",
            "Hard work is worthless for those that don’t believe in themselves.—Naruto Uzumaki, Naruto",
            "In this world, wherever there is light, there are always shadows. As long as there is a concept of victors, the vanquished will also exist. The selfish desire for peace gives rise to war. And hatred is born in order to protect love. — Madara Uchiha, Naruto",
            "Wake up to reality. Nothing ever goes as planned in this world. The longer you live, the more you realize that only pain, suffering, and futility exist in this reality. — Madara Uchiha, Naruto",
            "The important thing is not how long you live. It’s what you accomplish with your life. — rovyle, Pokémon",
            "Sometimes you need a little wishful thinking to keep on living. — Misato Katsuragi, Neon Genesis Evangelion",
            "I guess, as long as I have life, all I can do is fight with all my might. — Subaru Natsuki, Re:Zero",
            "When you give up, that’s when the game ends. — Mitsuyoshi Anzai, Slam Dunk",
            "Sometimes, we have to look beyond what we want and do what’s best. — Piccolo, Dragon Ball Z",
            "It’s not dying that frightens us. It’s living without ever having done our best. — The Elder, Castlevania",
            "You will never be able to love anybody else until you love yourself. — Lelouch Lamperouge, Code Geass",
            "Dead people receive more flowers than living ones because regret is stronger than gratitude. — Violet Evergarden, Violet Evergarden",
            "Searching for someone to blame is such a pain.—Satoru Gojo, Jujutsu Kaisen",
            "Mistakes are not shackles that halt one from stepping forward. Rather, they are that which sustain and grow one's heart. – Mavis Vermillon, Fairy Tail",
            "You need to accept the fact that you’re not the best and have all the will to strive to be better than anyone you face.– Zoro, One Piece",
            "People’s lives don’t end when they die, it ends when they lose faith. – Itachi Uchiha, Naruto",
            "You should enjoy the little detours to the fullest. Because that’s where you’ll find the things more important than what you want. – Ging Freecss, Hunter x Hunter",
            "There’s no such thing as a painless lesson. They just don’t exist. Sacrifices are necessary. You can’t gain anything without losing something first. – Edward Elric, Fullmetal Alchemist"

            // Your quotes here
    };

    private String currentQuote = "";
    private SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        sharedPreferences = getSharedPreferences("Likedquotes", MODE_PRIVATE);

        TextView quoteTextView = findViewById(R.id.quoteTextView);
        Spinner animeSpinner = findViewById(R.id.animeSpinner);
        Button generateQuoteButton = findViewById(R.id.generateQuoteButton);
        Button likeButton = findViewById(R.id.likeButton);

        // Populate spinner with anime titles extracted from quotes
        Set<String> animeTitles = new HashSet<>();
        for (String quote : quotes) {
            String[] parts = quote.split("—");
            if (parts.length > 1) {
                String animeTitle = parts[1].substring(parts[1].lastIndexOf(',') + 1).trim();
                animeTitles.add(animeTitle);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(animeTitles));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animeSpinner.setAdapter(adapter);

        generateQuoteButton.setOnClickListener(view -> {
            String selectedAnime = animeSpinner.getSelectedItem().toString();
            List<String> filteredQuotes = new ArrayList<>();
            for (String quote : quotes) {
                if (quote.endsWith(selectedAnime)) {
                    filteredQuotes.add(quote);
                }
            }

            if (!filteredQuotes.isEmpty()) {
                int index = new Random().nextInt(filteredQuotes.size());
                currentQuote = filteredQuotes.get(index);
                Toast.makeText(Next_Activity.this, "Quote generated!", Toast.LENGTH_SHORT).show();
                quoteTextView.setText(currentQuote);
            } else {
                Toast.makeText(Next_Activity.this, "No quotes available for selected anime.", Toast.LENGTH_SHORT).show();
            }
        });

        likeButton.setOnClickListener(view -> {
            if (!currentQuote.isEmpty()) {
                saveLikedQuote(currentQuote);
                Toast.makeText(Next_Activity.this, "Quote liked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveLikedQuote(String quote) {
        Set<String> likedQuotes = new HashSet<>(sharedPreferences.getStringSet("quotes", new HashSet<>()));
        likedQuotes.add(quote);
        sharedPreferences.edit().putStringSet("quotes", likedQuotes).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_next_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.view_liked_quotes) {
            startActivity(new Intent(this, Likedquotes.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

