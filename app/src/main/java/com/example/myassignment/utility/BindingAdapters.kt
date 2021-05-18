package com.example.myassignment.utility

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.myassignment.R

object BindingAdapters {


    @JvmStatic
    @BindingAdapter("visibleGoneList")
    fun showHideList(view: View, size: Int?) {
        view.visibility = if (size != null && size > 0) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("goneVisibleList")
    fun goneVisibleList(view: View, size: Int?) {
        view.visibility = if ((size != null && size > 0) ) View.GONE else View.VISIBLE
    }
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("aqiValue")
    fun aqiValue(view: TextView, value: Double) {

        view.text =""+ Math.round(value*100.0)/100.0
        if(value>401){
            view.setTextColor(view.resources.getColor(R.color.color8F2721))
            return
        }else if(value>301){
            view.setTextColor(view.resources.getColor(R.color.colorCD3630))
            return
        }else if(value>201){
            view.setTextColor(view.resources.getColor(R.color.colorDE8E3B))
            return
        }else if(value>101){
            view.setTextColor(view.resources.getColor(R.color.colorFFF84D))
            return
        }else if(value>51){
            view.setTextColor(view.resources.getColor(R.color.color9BBC51))
            return
        }else {
            view.setTextColor(view.resources.getColor(R.color.color599748))
            return
        }


    }
}