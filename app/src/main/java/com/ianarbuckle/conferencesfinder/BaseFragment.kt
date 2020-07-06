package com.ianarbuckle.conferencesfinder

import android.widget.Toolbar
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun toolbar(@DrawableRes drawableResId: Int) {
        requireActivity().findViewById<Toolbar>(R.id.toolbar).logo = if (drawableResId != 0) {
            ContextCompat.getDrawable(requireContext(), drawableResId)
        } else {
            null
        }
    }
}