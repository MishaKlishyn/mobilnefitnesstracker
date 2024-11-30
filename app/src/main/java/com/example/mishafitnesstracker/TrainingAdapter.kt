package com.example.mishafitnesstracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TrainingAdapter(
    private val trainingList: List<Training>,
    private val onItemClick: (Training) -> Unit
) : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val distanceTextView: TextView = itemView.findViewById(R.id.distanceTextView)
        val caloriesTextView: TextView = itemView.findViewById(R.id.caloriesTextView)
        val activityTextView: TextView = itemView.findViewById(R.id.activityTextView)
        val intensityTextView: TextView = itemView.findViewById(R.id.intensityTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_training, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val training = trainingList[position]
        holder.timeTextView.text = "Time: ${training.time}"
        holder.distanceTextView.text = "Distance: ${training.distance}"
        holder.caloriesTextView.text = "Calories: ${training.calories}"
        holder.activityTextView.text = "Activity: ${training.activityType}"
        holder.intensityTextView.text = "Intensity: ${training.intensity}"


        holder.itemView.setOnClickListener {
            onItemClick(training)
        }
    }


    override fun getItemCount(): Int {
        return trainingList.size
    }
}
