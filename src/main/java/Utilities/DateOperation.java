package Utilities;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateOperation {
	

		
		/*
		 * Formats= "MM-dd-yyyy" or MM-dd-YYYY both are valid and will return in correct form. Here 0 is auto-appended for <10.
		 * Formats= "M-d-yyyy" or "M-d-YYYYY"  is correct. Here 0 will not be appended <10 but >10 it will auto add 0 value to it.
		 * "yyyy-M-d== always use this format if you want to save your uneccesary time while working with time.
		 * Formats= "MMM-d-YYYY" on above lines this will print Month by taking first 3 digits
		 * */
		
		public String getCurrentDate(String format){
			{
				LocalDate dt= LocalDate.now();
				DateTimeFormatter dtf= DateTimeFormatter.ofPattern(format);
				return dt.format(dtf);
			}
		}
		
		public String convertDateToStandardFormat(String giveFormat,String Date,String destinationformat)
	    {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(giveFormat);
	        LocalDate localDate = LocalDate.parse(Date, formatter);
	        return localDate.format(DateTimeFormatter.ofPattern(destinationformat));

	    }


		public String addDatetoStandardFormat(String date, String format, String type, long qty) {
		    try {
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		        LocalDate localDate = LocalDate.parse(date, formatter);
		        
		        switch (type.toLowerCase()) {
		            case "days":
		                return localDate.plusDays(qty).format(DateTimeFormatter.ofPattern(format));
		            case "months":
		                return localDate.plusMonths(qty).format(DateTimeFormatter.ofPattern(format));
		            case "years":
		                return localDate.plusYears(qty).format(DateTimeFormatter.ofPattern(format));
		            default:
		                throw new IllegalArgumentException("Invalid type: " + type + ". Expected 'days', 'months', or 'years'.");
		        }
		    } catch (DateTimeParseException e) {
		        throw new IllegalArgumentException("Invalid date or format: " + date + ", " + format, e);
		    }
		}
		
		
	    public String subtractDatetoStandardFormat(String date,String format, String type,long qty)
	    {
	    	try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	            LocalDate localDate = LocalDate.parse(date, formatter);
	            
	            switch (type.toLowerCase()) {
	                case "days":
	                    return localDate.minusDays(qty).toString();
	                case "months":
	                    return localDate.minusMonths(qty).toString();
	                case "years":
	                    return localDate.minusYears(qty).toString();
	                default:
	                    throw new IllegalArgumentException("Invalid type: " + type + ". Expected 'days', 'months', or 'years'.");
	            }
	        } catch (DateTimeParseException e) {
	            throw new IllegalArgumentException("Invalid date or format: " + date + ", " + format, e);
	        }

	    }


}
