package com.rivaldofez.moviers.utils

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.core.widget.TextViewCompat
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rivaldofez.moviers.R
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun formatRuntime(duration: Int): String{
    val hours = duration / 60
    val minutes = duration % 60
    return ("Duration: ${hours}h ${minutes}m")
}

fun formatCurrency(value: Int): String{
    return (NumberFormat.getCurrencyInstance(Locale.US).format(value).toString())
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun formatDate(value: String): String{
    return try {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(value)
        SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(date)

    } catch (e: ParseException) {
        print("Error while parse date" + e.message.toString())
        "-"
    }
}

fun Context.generateButtonTextView(text: String, layout: LinearLayout){
    val tvItem = TextView(this)
    val scale = resources.displayMetrics.density
    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

    params.setMargins(0,0,(4 * scale + 0.5F).toInt(),0)
    tvItem.layoutParams = params
    tvItem.text = text
    tvItem.background = ContextCompat.getDrawable(this, R.drawable.bg_toggle_genre)
    TextViewCompat.setTextAppearance(tvItem, R.style.ToggleGenre)

    tvItem.setPadding((4 * scale + 0.5F).toInt())
    layout.addView(tvItem)
}

fun Context.setStateFavoriteIcon(button: FloatingActionButton, isFavorite: Boolean){
    if(isFavorite){
        button.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.ic_favorite)
        )
    }else{
        button.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.ic_favorite_unfilled)
        )
    }
}

fun Context.showToastFavorite(isFavorite: Boolean){
    if(!isFavorite)
        Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
    else
        Toast.makeText(this, R.string.remove_favorite, Toast.LENGTH_SHORT).show()
}

fun showLoading(status: Boolean, loading: SpinKitView){
    if(status){
        val sprite = DoubleBounce()
        loading.visibility = View.VISIBLE
        loading.setIndeterminateDrawable(sprite)
    }else{
        loading.visibility = View.GONE
    }
}