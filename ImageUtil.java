
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;


import com.example.imageutilities.BuildConfig;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by RJ Chakraborty on 29-05-2019.
 */

public class ImageUtil {

    /**
     * This method returns Gif File in byte array
     * @param gifFile
     * @return
     */
    public static byte[] getGifinBytes(File gifFile) {
        byte[] outputGifBaos = null;
        if (gifFile != null && gifFile.exists()) {
            try {
                outputGifBaos = readBytes(gifFile);
            } catch (Exception ignored) {
            }
        }
        return outputGifBaos;
    }


    /**
     * Decode base64Binary String to bytes
     * @param encodedString
     * @return
     */
    public static Bitmap decodeBase64BinaryToBytes(String encodedString) {
        String pureBase64Encoded = encodedString.trim().substring(encodedString.trim().indexOf(",") + 1);
        byte[] imageBytes = Base64.decode(pureBase64Encoded.trim(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }


    /**
     * Encode Bytes to Base64 Binary String
     * @param image
     * @return
     */
    public static String encodeBytesToBase64Binary(byte[] image) {
        byte[] encoded = Base64.encode(image, Base64.NO_WRAP);
        return new String(encoded);
    }


    /**
     * To get a Bitmap image from the URL received
     * @param imageUrl
     * @return
     */
    public static Bitmap getBitmapfromUrl(Uri imageUrl) {
        try {
            URL url = new URL(imageUrl.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Circular bitmap Image like you see in Whatsapp live location
     * @param bitmap
     * @param borderWidth
     * @param borderColor
     * @return
     */
    public static Bitmap getCircularBitmapWithWhiteBorder(Bitmap bitmap, int borderWidth, int borderColor) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        final int width = bitmap.getWidth() + borderWidth;
        final int height = bitmap.getHeight() + borderWidth;

        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setShader(shader);
        Canvas canvas = new Canvas(canvasBitmap);
        float radius = width > height ? ((float) height) / 2f : ((float) width) / 2f;
        canvas.drawCircle(width / 2.0f, height / 2.0f, radius, paint);
        if (borderWidth > 0) {
            paint.setShader(null);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            paint.setStrokeWidth(borderWidth);
            //paint.setShadowLayer(4.0f, 0, 0, Color.BLACK);
            canvas.drawCircle(width / 2.0f, height / 2.0f, radius - borderWidth / 2.0f, paint);
        }
        return canvasBitmap;
    }



    private static byte[] readBytes(File file) {
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }

    private static void writeBytes(File file, byte[] bytes) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
        bos.write(bytes);
        bos.flush();
        bos.close();
    }

    /**
     * Get a Blurred Thumbnail from a bitmap as placeholder before downloading the original image
     * @param context
     * @param inputBitmap
     * @return
     */
    public static byte[] getBlurredThumbnail(Context context, Bitmap inputBitmap) {
        if (inputBitmap != null) {
            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
            RenderScript rs = RenderScript.create(context);
            ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
            theIntrinsic.setRadius(7.5f);
            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (outputBitmap != null) {
                outputBitmap.compress(Bitmap.CompressFormat.WEBP, 40, baos);
                return baos.toByteArray();
            }
        }
        return null;
    }

    /**
     * it will create thumbnail from a video path
     * @param context
     * @param filePath
     * @return
     */
    public static String createThumbnailFromPath(Context context, String filePath) {
        String returnPath = null;
        try {
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Images.Thumbnails.MINI_KIND);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.WEBP, 30, baos);
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
            Cursor cursor = context.getContentResolver().query(Uri.parse(path), null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                returnPath = cursor.getString(idx);
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnPath;
    }


    /**
     * Save the bitmap into phone's local storage
     * @param context
     * @param bitmap
     * @return
     */
    public static File saveDrawingBitmap(Context context, Bitmap bitmap) {
        if(context == null)
            return null;
        File photo = new File(getAppFolder(context), "Drawing_" + SystemClock.currentThreadTimeMillis() + ".png");
        try {
            FileOutputStream fos = null;
            if (photo != null) {
                fos = new FileOutputStream(photo.getPath());

                if (photo.getName().endsWith("png")) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 70, fos);
                } else if (photo.getName().endsWith("webp")) {
                    bitmap.compress(Bitmap.CompressFormat.WEBP, 70, fos);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fos);
                }

                fos.close();
                if (photo.exists()) {
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                    if (photo.getName().endsWith("png")) {
                        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
                    } else {
                        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    }

                    values.put(MediaStore.MediaColumns.DATA, photo.getAbsolutePath());
                    context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                }
            }

        } catch (Exception e) {
            Log.e("Picture", "Exception in photoCallback", e);
        }

        if (photo != null && photo.exists()) {
            return photo;
        } else {
            Uri fileUri = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + android.R.drawable.stat_notify_error);
            photo = new File(fileUri.getPath());
            if (photo != null && photo.exists()) {
                return photo;
            }
        }
        return null;
    }

    /**
     * Load Image from local storage using filePath
     * @param context
     * @param filePath
     * @return
     */
    public static Bitmap loadImageFromStorage(Context context, String filePath) {
        if(context == null)
            return null;
        try {
            File file = context.getFileStreamPath(filePath);
            FileInputStream fi = new FileInputStream(file);
            return BitmapFactory.decodeStream(fi);
        } catch (Exception ex) {
            return null;
        }
    }


    private static String getAppName(Context context) {
        final PackageManager pm = context.getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(context.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        return (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
    }

    private static File getWorkingDirectory() {
        File directory =
                new File(Environment.getExternalStorageDirectory(), BuildConfig.APPLICATION_ID);
        if (!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }

    public static File getAppFolder(Context context) {
        File photoDirectory = new File(getWorkingDirectory().getAbsolutePath(),
                getAppName(context.getApplicationContext()));
        if (!photoDirectory.exists()) {
            photoDirectory.mkdir();
        }
        return photoDirectory;

    }

    /**
     * Check if a Drawable is Empty or Null
     * @param iv
     * @return
     */
    public static boolean hasNullOrEmptyDrawable(AppCompatImageView iv) {
        Drawable drawable = iv.getDrawable();
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;

        return bitmapDrawable == null || bitmapDrawable.getBitmap() == null;
    }

   
}
