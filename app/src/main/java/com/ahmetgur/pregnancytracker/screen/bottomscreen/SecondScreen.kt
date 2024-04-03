package com.ahmetgur.pregnancytracker.screen.bottomscreen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.ahmetgur.pregnancytracker.R

@Composable
fun Browse(){
    val categories = listOf("Baby Names", "Kick Counter", "Contraction Counter", "Trends", "Pragnancy By Week", "Daily Self-Care Checklist", "Pregnancy Tracker", "Pregnancy Calendar", "Pregnancy Weight Gain Tracker", "Pregnancy Symptoms Tracker", "Pregnancy Food Safety Guide", "Pregnancy Exercise Tracker", "Pregnancy Medication Safety Guide", "Pregnancy Sleep Tracker", "Pregnancy Doctor Visit Planner", "Pregnancy Hospital Bag Checklist", "Pregnancy Baby Registry Checklist", "Pregnancy Baby Shower Planner", "Pregnancy Baby Name Finder", "Pregnancy", "In the Womb", "Pregnancy", "Baby Shower",)
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(categories) { cat ->
            BrowserItem(cat = cat, drawable = R.drawable.baseline_pregnant_woman_24)
        }
    }
}