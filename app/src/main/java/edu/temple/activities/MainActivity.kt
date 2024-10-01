package edu.temple.activities

//import android.content.Intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create array of integers that are multiples of 5
        // Verify correctness by examining array values.
        val textSizes = Array(20){(it + 1) * 5}

        Log.d("Array values", textSizes.contentToString())

        with (findViewById<RecyclerView>(R.id.textSizeSelectorRecyclerView)) {

            // TODO Step 2: Implement lambda body to launch new activity and pass value

            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = TextSizeAdapter(textSizes){ selectedSize ->

                val launchIntent = Intent(this@MainActivity,DisplayActivity::class.java)
                launchIntent.putExtra("TEXT_SIZE",selectedSize)
                startActivity(launchIntent)

                }
            }
        }
    }

/* Convert to RecyclerView.Adapter */
class TextSizeAdapter (private val textSizes: Array<Int>, private val callback: (Int)->Unit) : RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    // TODO Step 1: Complete onClickListener to return selected number
    inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder (textView) {
        init {
            textView.setOnClickListener {
                //val selectedTextSize = textSizes[position]
                //val intent = Intent(this, DisplayActivity::class.java)
                //intent.putExtra("TEXT_SIZE",selectedTextSize)
                callback(textSizes[adapterPosition])
                //startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5, 20, 0, 20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        holder.textView.apply {
            text = textSizes[position].toString()
            textSize = textSizes[position].toFloat()
        }
    }

    override fun getItemCount(): Int {
        return textSizes.size
    }

}








