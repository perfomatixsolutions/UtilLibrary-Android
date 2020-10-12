package com.helper.utillibrary

import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.Video
import android.text.TextUtils
import android.util.Log


class MediaUtil {
    fun getDuration(ctx: Context, mediaUri: Uri?): Long {
        val cur = ctx.contentResolver.query(
            mediaUri!!,
            arrayOf(Video.Media.DURATION),
            null,
            null,
            null
        )
        var duration: Long = -1
        try {
            if (cur != null && cur.count > 0) {
                while (cur.moveToNext()) {
                    duration = cur.getLong(cur.getColumnIndex(Video.Media.DURATION))
                    if (duration == 0L) Log.w(
                        TAG,
                        "#getMediaDuration The image size was found to be 0. Reason: UNKNOWN"
                    )
                } // end while
            } else if (cur!!.count == 0) {
                Log.e(TAG, "#getMediaDuration cur size is 0. File may not exist")
            } else {
                Log.e(TAG, "#getMediaDuration cur is null")
            }
        } finally {
            if (cur != null && !cur.isClosed) {
                cur.close()
            }
        }
        return duration
    }
    /**
     * Checks if the parameter provided is a Media content uri.
     ****/
    fun isMediaContentUri(uri: Uri): Boolean {
        return if (!uri.toString().contains("content://media/")) {
            Log.w(TAG, "#isContentUri The uri is not a media content uri")
            false
        } else {
            true
        }
    }

    fun getMediaType(uri: Uri?): String? {
        if (uri == null) {
            return null
        }
        val uriStr = uri.toString()
        return if (uriStr.contains("video")) {
            "video"
        } else if (uriStr.contains("audio")) {
            "audio"
        } else if (uriStr.contains("image")) {
            "image"
        } else {
            null
        }
    }

    fun getMediaFileName(ctx: Context, mediaUri: Uri?): String? {
        val colName = MediaStore.MediaColumns.DISPLAY_NAME
        val cur: Cursor? = ctx.contentResolver.query(mediaUri!!, arrayOf(colName), null, null, null)
        var dispName: String? = null
        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    dispName = cur.getString(cur.getColumnIndex(colName))

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getMediaFileName byte.size: " + size );
                    if (TextUtils.isEmpty(colName)) {
                        Log.w(
                            TAG,
                            "#getMediaFileName The file name is blank or null. Reason: UNKNOWN"
                        )
                    }
                } // end while
            } else if (cur != null && cur.getCount() === 0) {
                Log.e(TAG, "#getMediaFileName File may not exist")
            } else {
                Log.e(TAG, "#getMediaFileName cur is null")
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close()
            }
        }
        return dispName
    }

    fun getMediaSize(context: Context, mediaUri: Uri?): Long {
        val cur = context.contentResolver.query(
            mediaUri!!,
            arrayOf(MediaStore.Images.Media.SIZE),
            null,
            null,
            null
        )
        var size: Long = -1
        try {
            if (cur != null && cur.count > 0) {
                while (cur.moveToNext()) {
                    size = cur.getLong(cur.getColumnIndex(MediaStore.Images.Media.SIZE))

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getSize byte.size: " + size );
                    if (size == 0L) Log.w(
                        TAG,
                        "#getSize The media size was found to be 0. Reason: UNKNOWN"
                    )
                } // end while
            } else if (cur!!.count == 0) {
                Log.e(TAG, "#getMediaSize cur size is 0. File may not exist")
            } else {
                Log.e(TAG, "#getMediaSize cur is null")
            }
        } finally {
            if (cur != null && !cur.isClosed) {
                cur.close()
            }
        }
        return size
    }
}