package sk.kasper.space.utils

import android.graphics.*

import com.squareup.picasso.Transformation
import kotlin.math.min

class CircleTransformation : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val size = min(source.width, source.height)

        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }

        val config = source.config
        val circleBitmap = Bitmap.createBitmap(size, size, config)

        val canvas = Canvas(circleBitmap)
        val paint = Paint()

        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)

        squaredBitmap.recycle()

        return circleBitmap
    }

    override fun key(): String {
        return "circle"
    }

}
