#  Util Library 
> A basic util class for android library

## Basic utils
* show toast in short duration
* Show toast in long duration
* check network availablity
* get the Connection type
* hide keyboard
* check email is valid email

## Date Util
* get the time in utc
* parseDate
* time to LocalTime
* getDayOfWeekAbbreviated
* getMonth
* getMonthAbbreviated
* parseDateToDDMMMYYYY
* parseDateToDDMMMYYYYandTIME
* parseDateToTime
* dateTimeFormatTo12Hr
* dateTimeFormatTo12Hr




## Set up
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.nithindaskc67:UtilLibrary:Tag'
	}
Step 3. Initialize the object for calling the functions
        val basicUtil = BasicUtil()
        val dateUtil = DateUtil()
        
       eg: basicUtil.showToastShort(this, dateUtil.dateTimeFormatTo12Hr("15:24").toString())


