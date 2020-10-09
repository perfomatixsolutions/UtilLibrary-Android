package com.helper.utillibrary

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


public class DateUtil {
    fun getUtcTime(dateAndTime: String?): String? {
        val d: Date? = parseDate(dateAndTime)
        val format = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        val sdf = SimpleDateFormat(format, Locale.getDefault())

        // Convert Local Time to UTC
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
        return sdf.format(d)
    }

    fun parseDate(date: String?): Date? {
        if (date == null) {
            return null
        }
        val sbDate = StringBuffer()
        sbDate.append(date)
        var newDate: String? = null
        var dateDT: Date? = null
        try {
            newDate = sbDate.substring(0, 19).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val rDate = newDate!!.replace("T", " ")
        val nDate = rDate.replace("-".toRegex(), "/")
        try {
            dateDT = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).parse(nDate)
            // Log.v( TAG, "#parseDate dateDT: " + dateDT );
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dateDT
    }

    @Throws(java.lang.Exception::class)
    fun toLocalTime(utcDate: String?, sdf: SimpleDateFormat): Date? {

        // create a new Date object using
        // the timezone of the specified city
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val localDate = sdf.parse(utcDate)
        sdf.timeZone = TimeZone.getDefault()
        val dateFormateInUTC = sdf.format(localDate)
        return sdf.parse(dateFormateInUTC)
    }

    fun getDayOfWeekAbbreviated(date: String?): String? {
        val dateDT = parseDate(date) ?: return null

        // Get current date
        val c = Calendar.getInstance()
        // it is very important to
        // set the date of
        // the calendar.
        c.time = dateDT
        val day = c[Calendar.DAY_OF_WEEK]
        var dayStr: String? = null
        when (day) {
            Calendar.SUNDAY -> dayStr = "Sun"
            Calendar.MONDAY -> dayStr = "Mon"
            Calendar.TUESDAY -> dayStr = "Tue"
            Calendar.WEDNESDAY -> dayStr = "Wed"
            Calendar.THURSDAY -> dayStr = "Thu"
            Calendar.FRIDAY -> dayStr = "Fri"
            Calendar.SATURDAY -> dayStr = "Sat"
        }
        return dayStr
    }

    fun getMonth(date: String?): String? {
        val dateDT = parseDate(date) ?: return null

        // Get current date
        val c = Calendar.getInstance()
        // it is very important to
        // set the date of
        // the calendar.
        c.time = dateDT
        val day = c[Calendar.MONTH]
        var dayStr: String? = null
        when (day) {
            Calendar.JANUARY -> dayStr = "January"
            Calendar.FEBRUARY -> dayStr = "February"
            Calendar.MARCH -> dayStr = "March"
            Calendar.APRIL -> dayStr = "April"
            Calendar.MAY -> dayStr = "May"
            Calendar.JUNE -> dayStr = "June"
            Calendar.JULY -> dayStr = "July"
            Calendar.AUGUST -> dayStr = "August"
            Calendar.SEPTEMBER -> dayStr = "September"
            Calendar.OCTOBER -> dayStr = "October"
            Calendar.NOVEMBER -> dayStr = "November"
            Calendar.DECEMBER -> dayStr = "December"
        }
        return dayStr
    }


    fun getMonthAbbreviated(date: String?): String? {
        val dateDT = parseDate(date) ?: return null

        // Get current date
        val c = Calendar.getInstance()
        // it is very important to
        // set the date of
        // the calendar.
        c.time = dateDT
        val day = c[Calendar.MONTH]
        var dayStr: String? = null
        when (day) {
            Calendar.JANUARY -> dayStr = "Jan"
            Calendar.FEBRUARY -> dayStr = "Feb"
            Calendar.MARCH -> dayStr = "Mar"
            Calendar.APRIL -> dayStr = "Apr"
            Calendar.MAY -> dayStr = "May"
            Calendar.JUNE -> dayStr = "Jun"
            Calendar.JULY -> dayStr = "Jul"
            Calendar.AUGUST -> dayStr = "Aug"
            Calendar.SEPTEMBER -> dayStr = "Sep"
            Calendar.OCTOBER -> dayStr = "Oct"
            Calendar.NOVEMBER -> dayStr = "Nov"
            Calendar.DECEMBER -> dayStr = "Dec"
        }
        return dayStr
    }

    fun parseDateToDDMMMYYYY(dateString: String?): String? {
        var date: Date? = null
        try {
            val fmt =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA)
            date = fmt.parse(dateString)
        } catch (e: ParseException) {
            //2018-07-04 04:14:14
            val fmt =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE)
            try {
                date = fmt.parse(dateString)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }
        }
        val fmtOut: SimpleDateFormat
        fmtOut = SimpleDateFormat("dd MMM yyyy", Locale("en"))
        return fmtOut.format(date)
    }

    fun parseDateToDDMMMYYYYandTIME(
        dateString: String?
    ): String? {
        var date: Date? = null
        try {
            val fmt =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA)
            date = fmt.parse(dateString)
        } catch (e: ParseException) {
            //2018-07-04 04:14:14
            val fmt =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE)
            try {
                date = fmt.parse(dateString)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }
        }
        val fmtOut: SimpleDateFormat
        fmtOut = SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale("en"))
        return fmtOut.format(date)
    }

    fun parseDateToTime(dateString: String?): String? {
        var date: Date? = null
        try {
            val fmt =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            fmt.timeZone = TimeZone.getTimeZone("GMT")
            date = fmt.parse(dateString)
        } catch (e: ParseException) {
            //2018-07-04 04:14:14
            val fmt =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            try {
                date = fmt.parse(dateString)
            } catch (e1: ParseException) {
                e1.printStackTrace()
            }
        }
        val fmtOut =
            SimpleDateFormat("HH:mm ", Locale.ENGLISH)
        return fmtOut.format(date)
    }

 
    fun dateTimeFormatTo12Hr(dateString: String?): String? {
        var time = "";
        try {
            val sdf = SimpleDateFormat("H:mm")
            val dateObj = sdf.parse(dateString)
            System.out.println(dateObj)
            println(SimpleDateFormat("K:mm a").format(dateObj))
            time = SimpleDateFormat("K:mm a").format(dateObj);
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return time;
    }

}
