package com.rjchakraborty.withu.util;

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
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rjchakraborty.withu.BuildConfig;
import com.rjchakraborty.withu.R;
import com.rjchakraborty.withu.application.WITHU;
import com.rjchakraborty.withu.listeners.AppConstants;
import com.rjchakraborty.withu.listeners.MoodConstants;
import com.rjchakraborty.withu.model.Content;
import com.rjchakraborty.withu.modules.customviews.CustomTextView;

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
 * Created by RJ Chakraborty on 12-02-2018.
 */

public class ImageUtil {


    public static int getMoodState(Context context, long state, CustomTextView tv_mood, boolean isMe) {
        switch ((int) state) {
            case MoodConstants.HAPPY:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_happy));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.happy)));
                    }
                }
                return R.drawable.happy;
            case MoodConstants.ANGRY:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_angry));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.angry)));
                    }
                }
                return R.drawable.angry;
            case MoodConstants.CONFUSE:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_confuse));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.confuse)));
                    }
                }
                return R.drawable.confuse;
            case MoodConstants.LOVE:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_love));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string._love)));
                    }
                }
                return R.drawable.love;
            case MoodConstants.NERVOUS:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_nervous));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.nervous)));
                    }
                }
                return R.drawable.nervous;
            case MoodConstants.SAD:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_sad));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.sad)));
                    }
                }
                return R.drawable.sad;
            case MoodConstants.CALM:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_calm));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.calm)));
                    }
                }
                return R.drawable.calm;
            case MoodConstants.COOL:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_cool));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.cool)));
                    }
                }
                return R.drawable.cool;
            case MoodConstants.CRY:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_cry));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.cry)));
                    }
                }
                return R.drawable.cry;
            case MoodConstants.SURPRISE:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_surprise));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.surprise)));
                    }
                }
                return R.drawable.surprise;
            case MoodConstants.KISS:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_kiss_string));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.kiss_string)));
                    }
                }
                return R.drawable.kiss;
            case MoodConstants.LISTEN_SONG:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_listen_song));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.listen_song)));
                    }
                }
                return R.drawable.listensong;
            case MoodConstants.SICK:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_sick));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.sick)));
                    }
                }
                return R.drawable.sick;
            case MoodConstants.SLEEP:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_sleep));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string.sleep)));
                    }
                }
                return R.drawable.sleep;
            default:
                if(tv_mood != null){
                    if(isMe) {
                        tv_mood.setText(context.getString(R.string.me_love));
                    }else{
                        tv_mood.setText(String.format("%s %s", tv_mood.getText(), context.getString(R.string._love)));
                    }
                }
                return R.drawable.love;

        }
    }

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


    public static Bitmap decodeBase64BinaryToBytes(String encodedString) {
        String pureBase64Encoded = encodedString.trim().substring(encodedString.trim().indexOf(",") + 1);
        byte[] imageBytes = Base64.decode(pureBase64Encoded.trim(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public static String encodeFileToBase64Binary(String filePath) {
        byte[] bytes = FileUtil.readFile2BytesByStream(filePath);
        byte[] encoded = Base64.encode(bytes, Base64.NO_WRAP);
        return new String(encoded);
    }

    public static String encodeBytesToBase64Binary(byte[] image) {
        byte[] encoded = Base64.encode(image, Base64.NO_WRAP);
        return new String(encoded);
    }

    /*
     *To get a Bitmap image from the URL received
     * */
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


    public static Bitmap getCircularBitmapWithWhiteBorder(Bitmap bitmap, int borderWidth) {
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
        canvas.drawCircle(width / 2, height / 2, radius, paint);
        if (borderWidth > 0) {
            paint.setShader(null);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(ContextCompat.getColor(WITHU.getAppContext(), R.color.colorPrimaryLight));
            paint.setStrokeWidth(borderWidth);
            //paint.setShadowLayer(4.0f, 0, 0, Color.BLACK);
            canvas.drawCircle(width / 2, height / 2, radius - borderWidth / 2, paint);
        }
        return canvasBitmap;
    }

    public static double mapValueFromRangeToRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        double progress = ((value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow));

        return toLow + progress;
    }

    public static double clamp(double value, double low, double high) {
        return Math.min(Math.max(value, low), high);
    }

    public static int getDotSize(int maximumuValue) {
        int a = 100;
        return ((maximumuValue / a) + 1) * 5;
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

    public static byte[] getBlurredThumbnail(Bitmap inputBitmap) {
        if (inputBitmap != null) {
            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);
            RenderScript rs = RenderScript.create(WITHU.getAppContext());
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

    public static Bitmap getBitmap(String filePath) {
        return ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Images.Thumbnails.MINI_KIND);
    }


    public static String createThumbnailFromPath(String filePath) {
        String returnPath = null;
        try {
            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Images.Thumbnails.MINI_KIND);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.WEBP, 30, baos);
            String path = MediaStore.Images.Media.insertImage(WITHU.getAppContext().getContentResolver(), bitmap, "Title", null);
            Cursor cursor = WITHU.getAppContext().getContentResolver().query(Uri.parse(path), null, null, null, null);
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


    public static File saveDrawingBitmap(Context context, Bitmap bitmap) {
        File photo = new File(getAppFolder(context), "WITHU_Drawing_" + SystemClock.currentThreadTimeMillis() + ".png");
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


    public static void saveToInternalStorage(final Bitmap bitmap, final String name) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream faos;
                try {
                    faos = WITHU.getAppContext().openFileOutput(name, Context.MODE_PRIVATE);
                    if (name.endsWith("png")) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 80, faos);
                    } else if (name.endsWith("webp")) {
                        bitmap.compress(Bitmap.CompressFormat.WEBP, 80, faos);
                    } else {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, faos);
                    }
                    faos.close();
                } catch (Exception e) {
                    Log.e("saveToInternalStorage()", e.getMessage());
                }
            }
        });
    }

    public static Bitmap loadImageFromStorage(String name) {
        try {
            File filePath = WITHU.getAppContext().getFileStreamPath(name);
            FileInputStream fi = new FileInputStream(filePath);
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

    public static String getLocalPathFromBitmap(Content content, Context context, Bitmap bitmap) {
        File photo = new File(getAppFolder(context), SystemClock.currentThreadTimeMillis() + ".jpg");
        String image_name = FileUtil.getFireStorageMediaName(content);
        if (image_name != null) {
            if (image_name.equalsIgnoreCase("local_upload")) {
                photo = new File(content.getContent());
            } else {
                switch (content.getCType()) {
                    case AppConstants.RIGHT_PHOTO:
                        photo = FileUtil.getOutputMediaFile(FileUtil.MEDIA_TYPE_IMAGE_SENT, image_name);
                        break;
                    case AppConstants.RIGHT_VIDEO:
                        photo = FileUtil.getOutputMediaFile(FileUtil.MEDIA_TYPE_VIDEO_SENT, image_name);
                        break;
                    case AppConstants.LEFT_PHOTO:
                        photo = FileUtil.getOutputMediaFile(FileUtil.MEDIA_TYPE_IMAGE, image_name);
                        break;
                    case AppConstants.LEFT_VIDEO:
                        photo = FileUtil.getOutputMediaFile(FileUtil.MEDIA_TYPE_VIDEO, image_name);
                        break;
                    case AppConstants.RIGHT_STICKER:
                    case AppConstants.LEFT_STICKER:
                        photo = FileUtil.getOutputMediaFile(FileUtil.MEDIA_TYPE_STICKER, image_name);
                        break;
                }
            }

            try {

                FileOutputStream fos = null;
                if (photo != null) {
                    fos = new FileOutputStream(photo.getPath());

                    if (image_name.endsWith("png")) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    } else if (image_name.endsWith("webp")) {
                        bitmap.compress(Bitmap.CompressFormat.WEBP, 100, fos);
                    } else {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    }

                    fos.close();
                    if (photo.exists()) {
                        ContentValues values = new ContentValues();

                        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                        if (image_name.endsWith("png")) {
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
        }

        if (photo != null && photo.exists()) {
            return photo.getAbsolutePath();
        } else {
            Uri fileUri = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.error_icon_padding_large);
            photo = new File(fileUri.getPath());
            if (photo != null && photo.exists()) {
                return photo.getAbsolutePath();
            } else {
                return "https://firebasestorage.googleapis.com/v0/b/withu-e9868.appspot.com/o/admin_feed%2Ferror.png?alt=media&token=82dc562b-f5b8-4e6e-a5c0-9dda763e00b6";
            }
        }
    }


    public static String getLocalPathFromGif(Content content, Context context, GifDrawable gifDrawable) {
        File photo = new File(getAppFolder(context), SystemClock.currentThreadTimeMillis() + ".gif");
        String image_name = FileUtil.getFireStorageMediaName(content);
        if (image_name != null) {
            if (image_name.equalsIgnoreCase("local_upload")) {
                photo = new File(content.getContent());
            } else {
                switch (content.getCType()) {
                    case AppConstants.RIGHT_PHOTO:
                    case AppConstants.RIGHT_VIDEO:
                        photo = FileUtil.getOutputMediaFile(FileUtil.MEDIA_TYPE_GIF_SENT, image_name);
                        break;
                    case AppConstants.LEFT_PHOTO:
                    case AppConstants.LEFT_VIDEO:
                        photo = FileUtil.getOutputMediaFile(FileUtil.MEDIA_TYPE_GIF, image_name);
                        break;
                }
            }

            try {

                if (photo != null) {
                    GifUtil.Companion.gifDrawableToFile(gifDrawable, photo);
                    if (photo.exists()) {
                        ContentValues values = new ContentValues();

                        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                        if (image_name.endsWith(".gif")) {
                            values.put(MediaStore.Images.Media.MIME_TYPE, "image/gif");
                        }

                        values.put(MediaStore.MediaColumns.DATA, photo.getAbsolutePath());
                        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    }
                }

            } catch (Exception e) {
                Log.e("Picture", "Exception in photoCallback", e);
            }
        }

        if (photo != null && photo.exists()) {
            return photo.getAbsolutePath();
        } else {
            Uri fileUri = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.error_icon_padding_large);
            photo = new File(fileUri.getPath());
            if (photo != null && photo.exists()) {
                return photo.getAbsolutePath();
            } else {
                return "https://firebasestorage.googleapis.com/v0/b/withu-e9868.appspot.com/o/admin_feed%2Ferror.png?alt=media&token=82dc562b-f5b8-4e6e-a5c0-9dda763e00b6";
            }
        }
    }

    public static boolean hasNullOrEmptyDrawable(AppCompatImageView iv) {
        Drawable drawable = iv.getDrawable();
        BitmapDrawable bitmapDrawable = drawable instanceof BitmapDrawable ? (BitmapDrawable) drawable : null;

        return bitmapDrawable == null || bitmapDrawable.getBitmap() == null;
    }

   
}
