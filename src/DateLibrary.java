/*
 * Homework 2 Edward Lee edl6zpg
 */
public class DateLibrary {

    /**
     * The function takes a string and returns true if its in the YYYY-MM-DD format
     * 
     * @param date the date to be checked
     * @return boolean true or false
     */
    public static boolean isValidDateFormat(String date) {
        // checking the first four digits
        try {
            if (date.length() != 10) { // length of date must be 10
                return false;
            }
            Integer.parseInt(date.substring(0, 4)); // year must be parseable to int
            Integer.parseInt(date.substring(5, 7)); // month must be parseable to int
            Integer.parseInt(date.substring(8, 10)); // day must be parseable to int
            if (date.charAt(4) == '-' && date.charAt(7) == '-') { // check if the dashes are in the right spots.
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) { // catch the error if the integers are not parseable
            return false;
        }

    }

    /**
     * get year from the string date
     * 
     * @param date to get year from
     * @return integer value of year
     */
    public static int getYear(String date) {
        if (!isValidDateFormat(date)) { // check if the date is valid format
            return -1;
        } else if (Integer.parseInt(date.substring(0, 4)) < 1) { // check if the year is less than one
            return -1;
        }

        else {
            return Integer.parseInt(date.substring(0, 4)); // if not return the integer
        }
    }

    /**
     * get the month from string date
     * 
     * @param date to get month from
     * @return integer value of month
     */
    public static int getMonth(String date) {
        if (!isValidDateFormat(date)) { // check if its valid date format
            return -1;
        } else if (Integer.parseInt(date.substring(5, 7)) > 12 || Integer.parseInt(date.substring(5, 7)) < 1) { // check if it is
                                                                                                                // a valid month
            return -1;
        } else {
            return Integer.parseInt(date.substring(5, 7)); // if not return integer
        }
    }

    /**
     * get the day from string date
     * 
     * @param date to get day from
     * @return integer value of day
     */
    public static int getDay(String date) {
        if (!isValidDateFormat(date)) { // check if its valid date format
            return -1;
        } else if (Integer.parseInt(date.substring(8, 10)) > 31 || Integer.parseInt(date.substring(8, 10)) < 1) { // check if the
                                                                                                                  // day is valid
            return -1;
        } else {
            return Integer.parseInt(date.substring(8, 10)); // if not return the int
        }
    }

    /**
     * checks whether the year is a leap year
     * 
     * @param year to check
     * @return boolean of whether its a leap year
     */

    public static boolean isLeapYear(int year) {
        if (((year % 4) == 0 && !((year % 100) == 0)) || (year % 400) == 0) { // definition of leap year
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks whether the string is a valid date
     * 
     * @param date to be checked
     * @return boolean value
     */
    public static boolean isValidDate(String date) {
        if (!isValidDateFormat(date)) {
            return false;
        } else if (getMonth(date) == -1 || getDay(date) == -1 || getYear(date) == -1) { // check if the month, year, days are
                                                                                        // valid
            return false;
        } else if (getMonth(date) == 1 || getMonth(date) == 3 || getMonth(date) == 5 || getMonth(date) == 7 || getMonth(date) == 8
                || getMonth(date) == 10 || getMonth(date) == 12) { // check months that have 31 days
            return true;
        } else if (getMonth(date) == 2) { // check february
            if (getDay(date) > 30) {// february does not have a 30th day
                return false;
            } else if (getDay(date) == 29) { // check 29 only on leap years
                if (isLeapYear(getYear(date))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (getDay(date) > 30) { // checking months that only have 30 days
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * compares the two dates lexicographically
     * 
     * @param date1 one of the dates to be compared
     * @param date2 the other date to be compared
     * @return the integer of difference between dates
     */

    public static int compare(String date1, String date2) {
        if (!DateLibrary.isValidDate(date1) || !DateLibrary.isValidDate(date2)) { // checking if the dates are valid.
            return 0;
        }
        return date1.compareTo(date2);
    }

    // main method testing along with JUnit
    public static void main(String[] args) {
        String a = "2001-06-15";
        System.out.println(DateLibrary.isValidDateFormat(a)); // should return true
        a = "0000-aa0-00";
        System.out.println(DateLibrary.isValidDateFormat(a)); // should return false
        a = "2001-06-15";
        System.out.println(DateLibrary.getYear(a));// should return 2001
        System.out.println(DateLibrary.getMonth(a)); // should return 6
        System.out.println(DateLibrary.getDay(a)); // should return 15
        a = "0000-00-00";
        System.out.println(DateLibrary.getYear(a)); // should return -1
        System.out.println(DateLibrary.getMonth(a)); // should return -1
        System.out.println(DateLibrary.getDay(a)); // should return -1
        int b = 2020;
        System.out.println(DateLibrary.isLeapYear(b)); // should return true
        b = 2001;
        System.out.println(DateLibrary.isLeapYear(b)); // should return false
        a = "2001-06-15";
        System.out.println(DateLibrary.isValidDate(a)); // should return true
        a = "2001-02-29";
        System.out.println(DateLibrary.isValidDate(a)); // should return false
        a = "2001-02-27";
        String c = "2001-02-18";
        System.out.println(DateLibrary.compare(a, c)); // should return 1
        a = "2001-02-18";
        c = "2001-02-27";
        System.out.println(DateLibrary.compare(a, c)); // should return -1

    }

}
