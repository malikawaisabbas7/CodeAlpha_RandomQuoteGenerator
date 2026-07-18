package com.codealpha.randomquote

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var tvQuote       : TextView
    private lateinit var tvAuthor      : TextView
    private lateinit var tvQuoteNumber : TextView
    private lateinit var cardQuote     : CardView
    private lateinit var btnNext       : MaterialButton
    private lateinit var btnPrevious   : MaterialButton
    private lateinit var btnAddQuote   : ExtendedFloatingActionButton
    private lateinit var btnShare      : MaterialButton

    private var currentIndex = 0

    private val quotes = mutableListOf(
        Pair("The only way to do great work is to love what you do.", "Steve Jobs"),
        Pair("In the middle of every difficulty lies opportunity.", "Albert Einstein"),
        Pair("It does not matter how slowly you go as long as you do not stop.", "Confucius"),
        Pair("Life is what happens when you're busy making other plans.", "John Lennon"),
        Pair("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
        Pair("Success is not final, failure is not fatal: it is the courage to continue that counts.", "Winston Churchill"),
        Pair("You miss 100% of the shots you don't take.", "Wayne Gretzky"),
        Pair("Whether you think you can or you think you can't, you're right.", "Henry Ford"),
        Pair("The only impossible journey is the one you never begin.", "Tony Robbins"),
        Pair("Strive not to be a success, but rather to be of value.", "Albert Einstein"),
        Pair("Two roads diverged in a wood, and I took the one less traveled by.", "Robert Frost"),
        Pair("I am not a product of my circumstances. I am a product of my decisions.", "Stephen Covey"),
        Pair("The mind is everything. What you think, you become.", "Buddha"),
        Pair("An unexamined life is not worth living.", "Socrates"),
        Pair("Spread love everywhere you go. Let no one ever come to you without leaving happier.", "Mother Teresa"),
        Pair("When you reach the end of your rope, tie a knot in it and hang on.", "Franklin D. Roosevelt"),
        Pair("Always remember that you are absolutely unique, just like everyone else.", "Margaret Mead"),
        Pair("Don't judge each day by the harvest you reap but by the seeds that you plant.", "Robert Louis Stevenson"),
        Pair("The best time to plant a tree was 20 years ago. The second best time is now.", "Chinese Proverb"),
        Pair("An eye for an eye only ends up making the whole world blind.", "Mahatma Gandhi"),
        Pair("I've learned that people will forget what you said, but never how you made them feel.", "Maya Angelou"),
        Pair("Either you run the day, or the day runs you.", "Jim Rohn"),
        Pair("Education is the most powerful weapon which you can use to change the world.", "Nelson Mandela"),
        Pair("It always seems impossible until it's done.", "Nelson Mandela"),
        Pair("Do not go where the path may lead, go instead where there is no path and leave a trail.", "Ralph Waldo Emerson"),
        Pair("You will face many defeats in life, but never let yourself be defeated.", "Maya Angelou"),
        Pair("The greatest glory in living lies not in never falling, but in rising every time we fall.", "Nelson Mandela"),
        Pair("In the end, it's not the years in your life that count. It's the life in your years.", "Abraham Lincoln"),
        Pair("Never let the fear of striking out keep you from playing the game.", "Babe Ruth"),
        Pair("Believe you can and you're halfway there.", "Theodore Roosevelt")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setupClickListeners()
        displayQuote(animate = false)
    }

    private fun initViews() {
        tvQuote       = findViewById(R.id.tv_quote)
        tvAuthor      = findViewById(R.id.tv_author)
        tvQuoteNumber = findViewById(R.id.tv_quote_number)
        cardQuote     = findViewById(R.id.card_quote)
        btnNext       = findViewById(R.id.btn_next)
        btnPrevious   = findViewById(R.id.btn_previous)
        btnAddQuote   = findViewById(R.id.btn_add_quote)
        btnShare      = findViewById(R.id.btn_share)
    }

    private fun setupClickListeners() {
        btnNext.setOnClickListener {
            currentIndex = if (currentIndex < quotes.size - 1) currentIndex + 1 else 0
            displayQuote(animate = true, direction = Direction.NEXT)
        }

        btnPrevious.setOnClickListener {
            currentIndex = if (currentIndex > 0) currentIndex - 1 else quotes.size - 1
            displayQuote(animate = true, direction = Direction.PREV)
        }

        btnAddQuote.setOnClickListener { showAddQuoteDialog() }
        btnShare.setOnClickListener   { shareCurrentQuote()   }
    }

    enum class Direction { NEXT, PREV }

    private fun displayQuote(animate: Boolean, direction: Direction = Direction.NEXT) {
        val (quoteText, authorName) = quotes[currentIndex]

        if (animate) {
            val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            val fadeIn  = AnimationUtils.loadAnimation(this,
                if (direction == Direction.NEXT) R.anim.fade_in else R.anim.fade_in_left)

            cardQuote.startAnimation(fadeOut)
            fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(a: android.view.animation.Animation?) {}
                override fun onAnimationRepeat(a: android.view.animation.Animation?) {}
                override fun onAnimationEnd(a: android.view.animation.Animation?) {
                    setQuoteText(quoteText, authorName)
                    cardQuote.startAnimation(fadeIn)
                }
            })
        } else {
            setQuoteText(quoteText, authorName)
        }
    }

    private fun setQuoteText(quoteText: String, authorName: String) {
        tvQuote.text       = "\u201C$quoteText\u201D"
        tvAuthor.text      = "— $authorName"
        tvQuoteNumber.text = "${currentIndex + 1} / ${quotes.size}"
    }

    private fun showAddQuoteDialog() {

        // ── Navy background color (matches app theme) ──────────────────────
        val navyMid    = Color.parseColor("#1B2B3B")
        val white      = Color.parseColor("#F0F4F8")
        val mutedWhite = Color.parseColor("#90A4AE")
        val accentBlue = Color.parseColor("#4FC3F7")

        // ── Root container with navy background ────────────────────────────
        val layout = LinearLayout(this).apply {
            orientation      = LinearLayout.VERTICAL
            setPadding(60, 20, 60, 20)
            setBackgroundColor(navyMid)   // ← Force navy background here
        }

        // ── "Quote" label ──────────────────────────────────────────────────
        val labelQuote = TextView(this).apply {
            text      = "Quote"
            textSize  = 12f
            setPadding(0, 0, 0, 8)
            setTextColor(mutedWhite)
        }

        // ── Quote input ────────────────────────────────────────────────────
        val etQuote = EditText(this).apply {
            hint      = "Enter your quote here..."
            minLines  = 3
            maxLines  = 5
            setTextColor(white)
            setHintTextColor(mutedWhite)
            background = null             // Remove default white underline box
            setBackgroundColor(Color.parseColor("#243447"))  // Slightly lighter navy
            setPadding(20, 16, 20, 16)
        }

        // ── "Author" label ─────────────────────────────────────────────────
        val labelAuthor = TextView(this).apply {
            text      = "Author"
            textSize  = 12f
            setPadding(0, 24, 0, 8)
            setTextColor(mutedWhite)
        }

        // ── Author input ───────────────────────────────────────────────────
        val etAuthor = EditText(this).apply {
            hint      = "Author name (or leave blank for Anonymous)"
            setTextColor(white)
            setHintTextColor(mutedWhite)
            background = null
            setBackgroundColor(Color.parseColor("#243447"))
            setPadding(20, 16, 20, 16)
        }

        layout.addView(labelQuote)
        layout.addView(etQuote)
        layout.addView(labelAuthor)
        layout.addView(etAuthor)

        // ── Build dialog ───────────────────────────────────────────────────
        val dialog = AlertDialog.Builder(this, R.style.AddQuoteDialogTheme)
            .setTitle("Add Your Quote")
            .setView(layout)
            .setPositiveButton("Add Quote") { _, _ ->
                val quoteText  = etQuote.text.toString().trim()
                val authorName = etAuthor.text.toString().trim().ifEmpty { "Anonymous" }

                if (quoteText.isEmpty()) {
                    Toast.makeText(this, "Please enter a quote!", Toast.LENGTH_SHORT).show()
                } else {
                    quotes.add(Pair(quoteText, authorName))
                    currentIndex = quotes.size - 1
                    displayQuote(animate = true, direction = Direction.NEXT)
                    Toast.makeText(this, "Quote added! ✨", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        // ── Force navy background on the dialog window itself ──────────────
        // This overrides Android's default white dialog background
        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(navyMid))

        // ── Fix button colors after dialog is shown ────────────────────────
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(accentBlue)
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(mutedWhite)
    }

    private fun shareCurrentQuote() {
        val shareText = "${tvQuote.text}\n\n${tvAuthor.text}\n\nShared via Inspire Me — Quote App"
        val intent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(android.content.Intent.EXTRA_TEXT, shareText)
        }
        startActivity(android.content.Intent.createChooser(intent, "Share Quote via"))
    }
}