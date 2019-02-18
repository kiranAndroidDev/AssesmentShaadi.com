package com.example.demoshaadi.custom_views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet


class CircleImageView(ctx: Context, attrs: AttributeSet) : android.support.v7.widget.AppCompatImageView(ctx, attrs) {

    override fun onDraw(canvas: Canvas) {

        val drawable = drawable ?: return

        if (width == 0 || height == 0) {
            return
        }
        // Bitmap b = ((BitmapDrawable) drawable).getBitmap();

        val bitmap = ConvertDrawableToBitmap(drawable).copy(Bitmap.Config.ARGB_8888, true)

        val w = width

        val roundBitmap = getRoundedCroppedBitmap(bitmap, w)
        canvas.drawBitmap(roundBitmap, 0f, 0f, null)

    }

    fun ConvertDrawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable)
            return drawable.bitmap
        // We ask for the bounds if they have been set as they would be most
        // correct, then we check we are  > 0
        val bounds = drawable.bounds
        val width = if (!bounds.isEmpty) bounds.width() else drawable.intrinsicWidth
        val height = if (!bounds.isEmpty) bounds.height() else drawable.intrinsicHeight
        // Now we check we are > 0
        val bitmap = Bitmap.createBitmap(if (width <= 0) 1 else width, if (height <= 0) 1 else height,
                Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    companion object {

        fun getRoundedCroppedBitmap(bitmap: Bitmap, radius: Int): Bitmap {
            val finalBitmap: Bitmap
            if (bitmap.width != radius || bitmap.height != radius)
                finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                        false)
            else
                finalBitmap = bitmap
            val output = Bitmap.createBitmap(finalBitmap.width,
                    finalBitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(output)

            val paint = Paint()
            val rect = Rect(0, 0, finalBitmap.width,
                    finalBitmap.height)

            paint.isAntiAlias = true
            paint.isFilterBitmap = true
            paint.isDither = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = Color.parseColor("#BAB399")
            canvas.drawCircle(finalBitmap.width / 2 + 0.7f,
                    finalBitmap.height / 2 + 0.7f,
                    finalBitmap.width / 2 + 0.1f, paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(finalBitmap, rect, rect, paint)

            return output
        }
    }
}
