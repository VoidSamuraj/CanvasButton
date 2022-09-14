@Composable
fun canvasButton(text:String="",width:Float,height:Float,onClick:()->Unit={}){

    IconButton(
        modifier = Modifier.padding(10.dp).apply {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){
                clip(
                    CutCornerShape(20.dp, 10.dp, 20.dp, 10.dp)
                )
            }
        },
        onClick = {onClick()},
    ) {
        // drawText(text = "Play",color=Color.Transparent)

        val bitmap = Bitmap.createBitmap(
            width.toInt(),
            height.toInt(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = android.graphics.Canvas(bitmap)
        val paint = android.graphics.Paint()
        paint.color= colorResource(id = R.color.menuFrontColor).hashCode()
        canvas.drawRect(
            0f,
            0f,
            width,
            height,
            paint
        )
        paint.style = android.graphics.Paint.Style.FILL_AND_STROKE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            paint.blendMode = BlendMode.XOR


            var path = Path()
            path.moveTo(0f, 0f)
            path.lineTo(0f, 10f)
            path.lineTo(10f, 0f)
            path.lineTo(0f, 0f)
            path.close()
            canvas.drawPath(path, paint)

            path = Path()
            path.moveTo(width, 0f)
            path.lineTo(width, 5f)
            path.lineTo(width - 5f, 0f)
            path.lineTo(width, 0f)
            path.close()
            canvas.drawPath(path, paint)

            path = Path()
            path.moveTo(width, height)
            path.lineTo(width, height - 10f)
            path.lineTo(width - 10f, height)
            path.lineTo(width, height)
            path.close()
            canvas.drawPath(path, paint)

            path = Path()
            path.moveTo(0f, height)
            path.lineTo(0f, height - 5f)
            path.lineTo(5f, height)
            path.lineTo(0f, height)
            path.close()
            canvas.drawPath(path, paint)

            paint.typeface = ResourcesCompat.getFont(
                LocalContext.current,
                R.font.oleo_script_swash_caps_regular
            )
            paint.textSize = MaterialTheme.typography.h4.fontSize.value
            paint.textAlign = android.graphics.Paint.Align.CENTER

        }
        val size = paint.fontMetrics
        paint.color = colorResource(id = R.color.trunkText).hashCode()

        canvas.drawText(
            text,
            width / 2f,
            (height + (size.bottom - size.top) / 2) / 2f,
            paint
        )
        Image(modifier = Modifier
            .width(width.dp)
            .height(height.dp) , bitmap = bitmap.asImageBitmap(), contentDescription = "BMP")

    }
}
