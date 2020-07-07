package com.ianarbuckle.conferencesfinder

import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

open class BaseFragment : Fragment() {

    fun toolbar(@DrawableRes drawableResId: Int, toolbarTitle: String? = null) {
        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.toolbar)
        if (!toolbarTitle.isNullOrEmpty()) {
            toolbar.title = toolbarTitle
        }
        toolbar.navigationIcon = if (drawableResId != 0) {
            ContextCompat.getDrawable(requireContext(), drawableResId)
        } else {
            null
        }
    }
}