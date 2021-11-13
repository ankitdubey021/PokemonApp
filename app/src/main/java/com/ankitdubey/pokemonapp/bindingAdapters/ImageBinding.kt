package com.ankitdubey.pokemonapp.bindingAdapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView

/**
 * Created by Ankit Dubey on 13,November,2021
 */
object ImageBinding {

    @JvmStatic
    @BindingAdapter("paletteImage", "paletteView")
    fun bindLoadImagePaletteView(view: ImageView, url: String?, paletteView: MaterialCardView) {
        if(url == null) return

        val context = view.context
        Glide.with(context)
            .load(url)
            .listener(
                GlidePalette.with(url)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val light = palette?.lightVibrantSwatch?.rgb
                        //val domain = palette?.dominantSwatch?.rgb
                        if (light != null) {
                            paletteView.setCardBackgroundColor(light)
                        }
                    }.crossfade(true)
            ).into(view)
    }

}